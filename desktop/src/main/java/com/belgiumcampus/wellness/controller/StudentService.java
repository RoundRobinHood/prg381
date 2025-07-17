package com.belgiumcampus.wellness.controller;

import com.belgiumcampus.wellness.classes.Student;
import com.belgiumcampus.wellness.util.DataManager;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

public class StudentService {

    /**
     * Adds a new student via the API (placeholder for now).
     * @param studentNumber The unique student number.
     * @param name Student's name.
     * @param surname Student's surname.
     * @param email Student's email.
     * @param phone Student's phone number.
     * @param password Student's password.
     * @return The created Student object, or null if creation failed.
     */
    public Student addStudent(String studentNumber, String name, String surname, String email, String phone, String password) throws SQLException {
      int student_number = Integer.parseInt(studentNumber);
      try(Connection connection = DataManager.getConnection()) {
        try(PreparedStatement stmt = connection.prepareStatement("INSERT INTO students (student_number, name, surname, email, phone, password, role) VALUES (?, ?, ?, ?, ?, ?, 'student');")) {
          stmt.setInt(1, student_number);
          stmt.setString(2, name);
          stmt.setString(3, surname);
          stmt.setString(4, email);
          stmt.setString(5, phone);
          stmt.setString(6, BCrypt.hashpw(password, BCrypt.gensalt()));
          stmt.executeUpdate();

          return new Student(studentNumber, name, surname, email, phone, null);
        }
      }
    }

    /**
     * Retrieves a student by their student number via the API (placeholder for now).
     * @param studentNumber The unique student number.
     * @return The Student object, or null if not found.
     */
    public Student getStudentById(String studentNumber) throws SQLException {
      int student_number = Integer.parseInt(studentNumber);
      try(Connection connection = DataManager.getConnection()) {
        try(PreparedStatement stmt = connection.prepareStatement("SELECT * FROM students WHERE student_number = ? AND role = 'student' AND NOT local_delete;")) {
          stmt.setInt(1, student_number);
          try(ResultSet rs = stmt.executeQuery()) {
            if(!rs.next())
              return null;
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String email = rs.getString("email");
            String phone = rs.getString("phone");

            return new Student(studentNumber, name, surname, email, phone, null);
          }
        }
      }
    }

    /**
     * Retrieves all students via the API (placeholder for now).
     * @return A list of all Student objects.
     */
    public List<Student> getAllStudents() throws SQLException {
      List<Student> students = new ArrayList<>();
      try(Connection connection = DataManager.getConnection()) {
        try(Statement stmt = connection.createStatement()) {
          try(ResultSet rs = stmt.executeQuery("SELECT * FROM students WHERE NOT local_delete AND role = 'student';")) {
            while(rs.next()) {
              String studentNumber = "" + rs.getInt("student_number");
              String name = rs.getString("name");
              String surname = rs.getString("surname");
              String email = rs.getString("email");
              String phone = rs.getString("phone");

              students.add(new Student(studentNumber, name, surname, email, phone, null));
            }
          }
        }
      }

      return students;
    }

    /**
     * Updates an existing student's information via the API (placeholder for now).
     * @param studentNumber The student number of the student to update.
     * @param newName New name.
     * @param newSurname New surname.
     * @param newEmail New email.
     * @param newPhone New phone number.
     * @param newPassword New password.
     * @return true if the student was found and updated, false otherwise.
     */
    public boolean updateStudent(String studentNumber, String newName, String newSurname, String newEmail, String newPhone, String newPassword) throws SQLException {
      int student_number = Integer.parseInt(studentNumber);
        List<String> fields = new ArrayList<>();
        List<String> values = new ArrayList<>();
        if(newName != null) {
          fields.add("name = ?");
          values.add(newName);
        }
        if(newSurname != null) {
          fields.add("surname = ?");
          values.add(newSurname);
        }
        if(newPhone != null) {
          fields.add("phone = ?");
          values.add(newPhone);
        }
        if(newPassword != null) {
          fields.add("password = ?");
          values.add(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
        }
        if(newEmail != null) {
          fields.add("email = ?");
          values.add(newEmail);
        }
        if(fields.isEmpty())
          return false;
        
        try(Connection connection = DataManager.getConnection()) {
          String sql = "UPDATE students SET " + String.join(",", fields) + ", local_modified = true WHERE student_number = ? AND NOT local_deleted AND role = 'student'";
          try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            for(int i = 0;i < fields.size(); i++) {
              stmt.setString(i+1, values.get(i));
            }
            stmt.setInt(fields.size()+1, student_number);
            return stmt.executeUpdate() != 0;
          }
        }
    }

    /**
     * Deletes a student via the API (placeholder for now).
     * @param studentNumber The student number of the student to delete.
     * @return true if the student was found and deleted, false otherwise.
     */
    public boolean deleteStudent(String studentNumber) throws SQLException {
      int student_number = Integer.parseInt(studentNumber);
      try(Connection connection = DataManager.getConnection()) {
        try(PreparedStatement stmt = connection.prepareStatement("UPDATE students SET local_deleted = true WHERE student_number = ? AND role = 'student' AND NOT local_deleted;")) {
          stmt.setInt(1, student_number);
          return stmt.executeUpdate() != 0;
        }
      }
    }
}
