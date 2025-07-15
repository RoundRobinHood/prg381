package com.roundrobinhood.shared;

import java.sql.Timestamp;

public class Session {
  public String session_id;
  public Integer student_number;
  public Timestamp expiry_date;
  public String flash_msg;

  public Session(String session_id, Integer student_number, Timestamp expiry_date) {
    this.session_id = session_id;
    this.student_number = student_number;
    this.expiry_date = expiry_date;
  }

  public Session(String session_id, Integer student_number, Timestamp expiry_date, String flash_msg) {
    this(session_id, student_number, expiry_date);
    this.flash_msg = flash_msg;
  }
}
