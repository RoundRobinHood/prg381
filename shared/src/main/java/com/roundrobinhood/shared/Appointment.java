package com.roundrobinhood.shared;

import java.sql.Timestamp;
import java.time.Instant;

public class Appointment extends DataAccessObject {
  public int appointment_id;
  public int student_number;
  public int counselor_id;
  public Instant timestamp;
  public String status;
  
  public Appointment(int appointment_id, int student_number, int counselor_id, Instant timestamp, String status) {
    this.appointment_id = appointment_id;
    this.student_number = student_number;
    this.counselor_id = counselor_id;
    this.timestamp = timestamp;
    this.status = status;
  }

  public Appointment() {}
}
