package com.roundrobinhood.shared;

public class Counselor extends DataAccessObject {
  public int counselor_id;
  public String name;
  public String specialization;
  public String availability;

  public Counselor(int counselor_id, String name, String specialization, String availability) {
    this.counselor_id = counselor_id;
    this.name = name;
    this.specialization = specialization;
    this.availability  = availability;
  }

  public Counselor() {}
}
