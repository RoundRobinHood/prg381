package com.belgiumcampus.wellness.controller;

import com.belgiumcampus.wellness.classes.Feedback;
import com.belgiumcampus.wellness.util.DataManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

public class FeedbackService {

    /**
     * Adds new feedback via the API (placeholder for now).
     * @param appointmentId The appointment ID (FK).
     * @param studentNumber The student ID (FK).
     * @param rating The rating (1-5).
     * @param comments The comments.
     * @return The created Feedback object.
     */
    public Feedback addFeedback(String appointmentId, String studentNumber, int rating, String comments) throws SQLException {
      if(appointmentId.startsWith("A"))
        appointmentId = appointmentId.substring(1);
      int appointment_id = Integer.parseInt(appointmentId);
      try(Connection connection = DataManager.getConnection()) {
        int newId = -1;
        try(Statement stmt = connection.createStatement()) {
          try(ResultSet rs = stmt.executeQuery("SELECT MIN(feedback_id) FROM feedback;")) {
            if(rs.next())
              newId = Math.min(-1, rs.getInt(1) - 1);
          }
        }
        try(PreparedStatement stmt = connection.prepareStatement("INSERT INTO feedback (feedback_id, appointment_id, rating, comments) VALUES (?, ?, ?, ?);")) {
          stmt.setInt(1, newId);
          stmt.setInt(2, appointment_id);
          stmt.setInt(3, rating);
          stmt.setString(4, comments);
          stmt.executeUpdate();

          return new Feedback("F" + String.format("%04d", newId), appointmentId, studentNumber, rating, comments);
        }
      }
    }

    /**
     * Retrieves feedback by its ID via the API (placeholder for now).
     * @param feedbackId The unique feedback ID.
     * @return The Feedback object, or null if not found.
     */
    public Feedback getFeedbackById(String feedbackId) throws SQLException {
      if(feedbackId.startsWith("F"))
        feedbackId = feedbackId.substring(1);
      int feedback_id = Integer.parseInt(feedbackId);
      try(Connection connection = DataManager.getConnection()) {
        try(PreparedStatement stmt = connection.prepareStatement("SELECT feedback.*, appointments.student_number FROM feedback INNER JOIN appointments ON feedback.appointment_id = appointments.appointment_id WHERE feedback_id = ? AND NOT feedback.local_deleted;")) {
          stmt.setInt(1, feedback_id);
          try(ResultSet rs = stmt.executeQuery()) {
            if(!rs.next())
              return null;
            String appointmentId = "A" + String.format("%04d", rs.getInt("appointment_id"));
            int rating = rs.getInt("rating");
            String comments = rs.getString("comments");
            String studentNumber = "" + rs.getInt("student_number");

            return new Feedback(feedbackId, appointmentId, studentNumber, rating, comments);
          }
        }
      }
    }

    /**
     * Retrieves all feedback entries via the API (placeholder for now).
     * @return A list of all Feedback objects.
     */
    public List<Feedback> getAllFeedback() throws SQLException {
      List<Feedback> feedback = new ArrayList<>();
      try(Connection connection = DataManager.getConnection()) {
        try(Statement stmt = connection.createStatement()) {
          try(ResultSet rs = stmt.executeQuery("SELECT feedback.*, appointments.student_number FROM feedback INNER JOIN appointments ON feedback.appointment_id = appointments.appointment_id WHERE NOT feedback.local_deleted;")) {
            while(rs.next()) {
              String feedbackId = "F" + String.format("%04d", rs.getInt("feedback_id"));
              String appointmentId = "A" + String.format("%04d", rs.getInt("appointment_id"));
              int rating = rs.getInt("rating");
              String comments = rs.getString("comments");
              String studentNumber = "" + rs.getInt("student_number");

              feedback.add(new Feedback(feedbackId, appointmentId, studentNumber, rating, comments));
            }
          }
        }
      }
      return feedback;
    }

    /**
     * Updates an existing feedback's rating and comments via the API (placeholder for now).
     * @param feedbackId The ID of the feedback to update.
     * @param newRating New rating.
     * @param newComments New comments.
     * @return true if the feedback was found and updated, false otherwise.
     */
    public boolean updateFeedback(String feedbackId, int newRating, String newComments) throws SQLException {
      if(feedbackId.startsWith("F"))
        feedbackId = feedbackId.substring(1);
      int feedback_id = Integer.parseInt(feedbackId);
      try(Connection connection = DataManager.getConnection()) {
        try(PreparedStatement stmt = connection.prepareStatement("UPDATE feedback SET rating = ?, comments = ?, local_modified = true WHERE feedback_id = ? AND NOT local_deleted;")) {
          stmt.setInt(1, newRating);
          stmt.setString(2, newComments);
          stmt.setInt(3, feedback_id);

          return stmt.executeUpdate() != 0;
        }
      }
    }

    /**
     * Deletes feedback via the API (placeholder for now).
     * @param feedbackId The ID of the feedback to delete.
     * @return true if the feedback was found and deleted, false otherwise.
     */
    public boolean deleteFeedback(String feedbackId) throws SQLException {
      if(feedbackId.startsWith("F"))
        feedbackId = feedbackId.substring(1);
      int feedback_id = Integer.parseInt(feedbackId);
      try(Connection connection = DataManager.getConnection()) {
        try(PreparedStatement stmt = connection.prepareStatement("UPDATE feedback SET local_deleted = true WHERE feedback_id = ? AND NOT local_deleted;")) {
          stmt.setInt(1, feedback_id);
          return stmt.executeUpdate() != 0;
        }
      }
    }
}
