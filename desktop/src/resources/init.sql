CREATE TABLE students (
  student_number INTEGER PRIMARY KEY,
  name           VARCHAR(70) NOT NULL,
  surname        VARCHAR(70) NOT NULL,
  email          VARCHAR(100) NOT NULL UNIQUE,
  phone          VARCHAR(20) NOT NULL,
  password       CHAR(60),
  role           VARCHAR(7) NOT NULL,
  join_date      TIMESTAMP,
  local_modified BOOLEAN NOT NULL DEFAULT false,
  local_deleted  BOOLEAN NOT NULL DEFAULT false,
  CONSTRAINT chk_role CHECK (role IN ('student', 'admin'))
);

CREATE TABLE counselors (
  counselor_id   INTEGER PRIMARY KEY,
  name           VARCHAR(70) NOT NULL,
  specialization VARCHAR(10) NOT NULL,
  availability   VARCHAR(7) NOT NULL,
  local_modified BOOLEAN NOT NULL,
  local_deleted  BOOLEAN NOT NULL,
  CONSTRAINT chk_specialization CHECK (specialization IN ('tutoring', 'mental health'))
);

CREATE TABLE appointments (
  appointment_id INTEGER PRIMARY KEY,
  student_number VARCHAR(10) NOT NULL,
  counselor_id   VARCHAR(10) NOT NULL,
  scheduled_for  TIMESTAMP NOT NULL,
  status         VARCHAR(10) NOT NULL,
  local_modified BOOLEAN NOT NULL,
  local_deleted  BOOLEAN NOT NULL,
  CONSTRAINT chk_status CHECK (status IN ('scheduled', 'completed', 'cancelled'),
  FOREIGN KEY(student_number) REFERENCES students(student_number),
  FOREIGN KEY(counselor_id)   REFERENCES counselors(counselor_id)
);

CREATE TABLE feedback (
  feedback_id    INTEGER PRIMARY KEY,
  appointment_id VARCHAR(10) NOT NULL,
  rating         SMALLINT NOT NULL,
  comments       VARCHAR(400) NOT NULL,
  local_modified BOOLEAN NOT NULL,
  local_deleted  BOOLEAN NOT NULL,
  CONSTRAINT chk_rating CHECK (rating BETWEEN 1 AND 5),
  FOREIGN KEY(appointment_id) REFERENCES appointments(appointment_id)
);

CREATE TABLE login (
  student_number
);
