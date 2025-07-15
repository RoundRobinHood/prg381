package com.roundrobinhood.shared;

import java.util.ArrayList;
import java.util.List;

public class Student {
  public String studentNumber;
  public String name;
  public String surname;
  public String email;
  public String phone;
  public String password; // Hashed with bcrypt

  public Student(String studentNumber, String name, String surname, String email, String phone, String password) {
    this.studentNumber = studentNumber;
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.phone = phone;
    this.password = password;
  }

  public static List<Student> students = new ArrayList<>();
}
