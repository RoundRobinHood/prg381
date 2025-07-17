create type user_role as enum ('student', 'admin');
create table students (
  student_number serial primary key,
  name           varchar(70) not null,
  surname        varchar(70) not null,
  email          varchar(100) not null unique,
  phone          varchar(20) not null,
  password       char(60) not null,
  role           user_role not null,
  join_date      timestamp not null default current_timestamp
);

create table user_sessions (
  session_id     char(32) unique not null,
  student_number int,
  flash_msg      varchar,
  expiry_date    timestamp not null,
  primary key(session_id),
  foreign key(student_number) references students(student_number)
);

create type counselor_availability as enum ('available', 'busy', 'unavailable');
create type counselor_specialization as enum ('tutoring', 'mental health');
create table counselors (
  counselor_id     serial primary key,
  name             varchar(70) not null,
  specialization   counselor_specialization not null,
  availability     counselor_availability not null
);

create type appointment_status as enum ('scheduled', 'completed', 'cancelled');
create table appointments (
  appointment_id serial primary key,
  student_number int not null,
  counselor_id   int not null,
  scheduled_for  timestamp not null,
  status         appointment_status not null,
  foreign key(student_number) references students(student_number),
  foreign key(counselor_id) references counselors(counselor_id)
);

create table feedback (
  feedback_id    serial primary key,
  appointment_id int not null,
  rating         smallint not null check(rating >= 1 and rating <= 5),
  comments       varchar not null,
  foreign key(appointment_id) references appointments(appointment_id)
);

create function set_request_context()
returns void as $$
begin
  perform set_config('request.student_number', current_setting('request.headers')::json->>'x-request-student-number', true);
  perform set_config('request.role', current_setting('request.headers')::json->>'x-request-role', true);
end;
$$ language plpgsql;

create role web_user nologin;

alter table counselors enable row level security;
grant all on counselors to web_user;
grant usage, select on sequence counselors_counselor_id_seq to web_user;

create policy counselor_any_reads on counselors 
  for select 
  using (true);
create policy counselor_admin_inserts on counselors
  for insert
  with check (current_setting('request.role') = 'admin');
create policy counselor_admin_updates on counselors
  for update
  with check (current_setting('request.role') = 'admin');
create policy counselor_admin_deletes on counselors
  for delete
  using (current_setting('request.role') = 'admin');


alter table appointments enable row level security;
grant all on appointments to web_user;
grant usage, select on sequence appointments_appointment_id_seq to web_user;

create policy appointments_owner_only on appointments
  for all
  using (current_setting('request.role') = 'admin' or student_number::text = current_setting('request.student_number'))
  with check (current_setting('request.role') = 'admin' or student_number::text = current_setting('request.student_number'));

create function appointment_student_number(appointment_id int)
returns int as $$
  select student_number from appointments where appointments.appointment_id = appointment_id limit 1;
$$ language sql stable;


alter table feedback enable row level security;
grant all on feedback to web_user;
grant usage, select on sequence feedback_feedback_id_seq to web_user;

create policy feedback_any_reads on feedback
  for select
  using (true);
create policy feedback_owner_inserts on feedback
  for insert
  with check (current_setting('request.role') = 'admin' or appointment_student_number(appointment_id)::text = current_setting('request.student_number'));
create policy feedback_owner_updates on feedback
  for update
  with check (current_setting('request.role') = 'admin' or appointment_student_number(appointment_id)::text = current_setting('request.student_number'));
create policy feedback_owner_deletes on feedback
  for delete
  using (current_setting('request.role') = 'admin' or appointment_student_number(appointment_id)::text = current_setting('request.student_number'));

create view debug_config as 
select current_setting('request.role', true) role, current_setting('request.student_number', true) student_number;
grant all on debug_config to web_user;
