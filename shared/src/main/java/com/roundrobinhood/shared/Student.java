package com.roundrobinhood.shared;

import java.util.ArrayList;
import java.util.List;

public class Student {
  public int student_number;
  public String name;
  public String surname;
  public String email;
  public String phone;
  public String password; // Hashed with bcrypt
  public String role;

  public Student(int student_number, String name, String surname, String email, String phone, String password, String role) {
    this.student_number = student_number;
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.phone = phone;
    this.password = password;
    this.role = role;
  }
}
