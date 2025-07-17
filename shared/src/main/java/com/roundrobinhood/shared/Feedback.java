package com.roundrobinhood.shared;

public class Feedback extends DataAccessObject {
  public int feedback_id;
  public int appointment_id;
  public int rating;
  public String comments;
  
  public Feedback(int feedback_id, int appointment_id, int rating, String comments) {
    this.feedback_id = feedback_id;
    this.appointment_id = appointment_id;
    this.rating = rating;
    this.comments = comments;
  }

  public Feedback() {}
}
