package com.roundrobinhood.shared;

import java.sql.Timestamp;

public class Session {
  public String session_id;
  public int student_number;
  public Timestamp expiry_date;

  public Session(String session_id, int student_number, Timestamp expiry_date) {
    this.session_id = session_id;
    this.student_number = student_number;
    this.expiry_date = expiry_date;
  }
}
