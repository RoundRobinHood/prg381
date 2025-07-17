package com.belgiumcampus.wellness.controller;

import com.belgiumcampus.wellness.classes.Appointment;
import com.belgiumcampus.wellness.classes.Appointment.AppointmentStatus;
import com.belgiumcampus.wellness.util.DataManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService {

    /**
     * Adds a new appointment via the API (placeholder for now).
     * @param studentNumber The student's ID (FK).
     * @param counselorId The counsellor's ID (FK).
     * @param date The appointment date.
     * @param time The appointment time.
     * @return The created Appointment object.
     */
    public Appointment addAppointment(String studentNumber, String counselorId, LocalDate date, LocalTime time) throws SQLException {
      int student_number = Integer.parseInt(studentNumber);
      if(counselorId.startsWith("C"))
        counselorId = counselorId.substring(1);
      int counselor_id = Integer.parseInt(counselorId);
      Timestamp timestamp = Timestamp.valueOf(LocalDateTime.of(date, time));
      try(Connection connection = DataManager.getConnection()) {
        int newId = -1;
        try(Statement stmt = connection.createStatement()) {
          try(ResultSet rs = stmt.executeQuery("SELECT MIN(appointment_id) FROM appointments;")) {
            if(rs.next())
              newId = Math.min(-1, rs.getInt(1) - 1);
          }
        }
        try(PreparedStatement stmt = connection.prepareStatement("INSERT INTO appointments (appointment_id, student_number, counselor_id, scheduled_for, status) VALUES (?, ?, ?, ?, 'scheduled');")) {
          stmt.setInt(1, newId);
          stmt.setInt(2, student_number);
          stmt.setInt(3, counselor_id);
          stmt.setTimestamp(4, timestamp);
          stmt.executeUpdate();
        }
        return new Appointment("A" + String.format("%04d", newId), studentNumber, counselorId, date, time, AppointmentStatus.SCHEDULED);
      }
    }

    /**
     * Retrieves an appointment by its ID via the API (placeholder for now).
     * @param appointmentId The unique appointment ID.
     * @return The Appointment object, or null if not found.
     */
    public Appointment getAppointmentById(String appointmentId) throws SQLException {
      if(appointmentId.startsWith("A"))
        appointmentId = appointmentId.substring(1);
      int appointment_id = Integer.parseInt(appointmentId);
      try(Connection connection = DataManager.getConnection()) {
        try(PreparedStatement stmt = connection.prepareStatement("SELECT * FROM appointments WHERE appointment_id = ? AND NOT local_deleted;")) {
          stmt.setInt(1, appointment_id);
          try(ResultSet rs = stmt.executeQuery()) {
            if(!rs.next())
              return null;
            else {
              String studentNumber = "" + rs.getInt("student_number");
              String counselorId = "C" + String.format("%04d", rs.getInt("counselor_id"));
              Timestamp scheduled_for = rs.getTimestamp("scheduled_for");
              LocalDateTime datetime = scheduled_for.toLocalDateTime();
              LocalDate date = datetime.toLocalDate();
              LocalTime time = datetime.toLocalTime();
              AppointmentStatus status = Appointment.statusFromString(rs.getString("status"));
              
              return new Appointment(appointmentId, studentNumber, counselorId, date, time, status);
            }
          }
        }
      }
    }

    /**
     * Retrieves all appointments via the API (placeholder for now).
     * @return A list of all Appointment objects.
     */
    public List<Appointment> getAllAppointments() throws SQLException {
      List<Appointment> appointments = new ArrayList<>();
      try(Connection connection = DataManager.getConnection()) {
        try(Statement stmt = connection.createStatement()) {
          try(ResultSet rs = stmt.executeQuery("SELECT * FROM appointments WHERE NOT local_deleted;")) {
            while(rs.next()) {
              String appointmentId = "A" + String.format("%04d", rs.getInt("appointment_id"));
              String studentNumber = "" + rs.getInt("student_number");
              String counselorId = "C" + String.format("%04d", rs.getInt("counselor_id"));
              Timestamp scheduled_for = rs.getTimestamp("scheduled_for");
              LocalDateTime datetime = scheduled_for.toLocalDateTime();
              LocalDate date = datetime.toLocalDate();
              LocalTime time = datetime.toLocalTime();
              AppointmentStatus status = Appointment.statusFromString(rs.getString("status"));

              appointments.add(new Appointment(appointmentId, studentNumber, counselorId, date, time, status));
            }
          }
        }
      }
      return appointments;
    }

    /**
     * Updates an existing appointment's date, time, and status via the API (placeholder for now).
     * @param appointmentId The ID of the appointment to update.
     * @param newDate New date.
     * @param newTime New time.
     * @param newStatus New status.
     * @return true if the appointment was found and updated, false otherwise.
     */
    public boolean updateAppointment(String appointmentId, LocalDate newDate, LocalTime newTime, AppointmentStatus newStatus) throws SQLException {
      if(appointmentId.startsWith("A"))
        appointmentId = appointmentId.substring(1);
      int appointment_id = Integer.parseInt(appointmentId);
      try(Connection connection = DataManager.getConnection()) {
        try(PreparedStatement stmt = connection.prepareStatement("UPDATE appointments SET scheduled_for = ?, status = ?, local_modified = true WHERE appointment_id = ? AND NOT local_deleted;")) {
          stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.of(newDate, newTime)));
          stmt.setString(2, Appointment.statusToString(newStatus));
          stmt.setInt(3, appointment_id);         
          
          return stmt.executeUpdate() != 0;
        }
      }
    }

    /**
     * Deletes an appointment via the API (placeholder for now).
     * @param appointmentId The ID of the appointment to delete.
     * @return true if the appointment was found and deleted, false otherwise.
     */
    public boolean deleteAppointment(String appointmentId) throws SQLException {
      if(appointmentId.startsWith("A"))
        appointmentId = appointmentId.substring(1);
      int appointment_id = Integer.parseInt(appointmentId);
      try(Connection connection = DataManager.getConnection()) {
        try(PreparedStatement stmt = connection.prepareStatement("UPDATE appointments SET local_deleted = true WHERE appointment_id = ? AND NOT local_deleted;")) {
          stmt.setInt(1, appointment_id);         
          return stmt.executeUpdate() != 0;
        }
      }
    }
}
