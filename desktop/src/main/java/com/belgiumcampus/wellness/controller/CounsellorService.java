package com.belgiumcampus.wellness.controller;

import com.belgiumcampus.wellness.classes.Counsellor;
import com.belgiumcampus.wellness.util.DataManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

public class CounsellorService {

    /**
     * Adds a new counsellor via the API (placeholder for now).
     * @param name Counsellor's name.
     * @param specialization Counsellor's specialization.
     * @param availability Counsellor's availability string.
     * @return The created Counsellor object.
     */
    public Counsellor addCounsellor(String name, String specialization, String availability) throws SQLException {
      try(Connection connection = DataManager.getConnection()) {
        int newId = -1;
        try(Statement stmt = connection.createStatement()) {
          try(ResultSet rs = stmt.executeQuery("SELECT MIN(counselor_id) FROM counselors;")) {
            if(rs.next())
              newId = Math.min(-1, rs.getInt(1) - 1);
          }
        }
        try(PreparedStatement stmt = connection.prepareStatement("INSERT INTO counselors (counselor_id, name, specialization, availability) VALUES (?, ?, ?, ?);")) {
          stmt.setInt(1, newId);
          stmt.setString(2, name);
          stmt.setString(3, specialization);
          stmt.setString(4, availability);
          stmt.executeUpdate();

          return new Counsellor("C" + String.format("%04d", newId), name, specialization, availability);
        }
      }
    }

    /**
     * Retrieves a counsellor by their ID via the API (placeholder for now).
     * @param counselorId The unique counsellor ID.
     * @return The Counsellor object, or null if not found.
     */
    public Counsellor getCounsellorById(String counselorId) throws SQLException {
      if(counselorId.startsWith("C"))
        counselorId = counselorId.substring(1);
      int counselor_id = Integer.parseInt(counselorId);
      try(Connection connection = DataManager.getConnection()) {
        try(PreparedStatement stmt = connection.prepareStatement("SELECT * FROM counselors WHERE counselor_id = ? AND NOT local_deleted;")) {
          stmt.setInt(1, counselor_id);
          try(ResultSet rs = stmt.executeQuery()) {
            if(!rs.next())
              return null;
            String name = rs.getString("name");
            String specialization = rs.getString("specialization");
            String availability = rs.getString("availability");

            return new Counsellor(counselorId, name, specialization, availability);
          }
        }
      }
    }

    /**
     * Retrieves all counsellors via the API (placeholder for now).
     * @return A list of all Counsellor objects.
     */
    public List<Counsellor> getAllCounsellors() throws SQLException {
      List<Counsellor> counselors = new ArrayList<>();
      try(Connection connection = DataManager.getConnection()) {
        try(Statement stmt = connection.createStatement()) {
          try(ResultSet rs = stmt.executeQuery("SELECT * FROM counselors WHERE NOT local_deleted;")) {
            while(rs.next()) {
              String counselorId = "C" + String.format("%04d", rs.getInt("counselor_id"));
              String name = rs.getString("name");
              String specialization = rs.getString("specialization");
              String availability = rs.getString("availability");

              counselors.add(new Counsellor(counselorId, name, specialization, availability));
            }
          }
        }
      }
      return counselors;
    }

    /**
     * Updates an existing counsellor's information via the API (placeholder for now).
     * @param counsellorId The ID of the counsellor to update.
     * @param newName New name.
     * @param newSpecialization New specialization.
     * @param newAvailability New availability string.
     * @return true if the counsellor was found and updated, false otherwise.
     */
    public boolean updateCounsellor(String counselorId, String newName, String newSpecialization, String newAvailability) throws SQLException {
      if(counselorId.startsWith("C"))
        counselorId = counselorId.substring(1);
      int counselor_id = Integer.parseInt(counselorId);
      try(Connection connection = DataManager.getConnection()) {
        try(PreparedStatement stmt = connection.prepareStatement("UPDATE counselors SET name = ?, specialization = ?, availability = ?, local_modified = true WHERE counselor_id = ? AND NOT local_deleted;")) {
          stmt.setString(1, newName);
          stmt.setString(2, newSpecialization);
          stmt.setString(3, newAvailability);
          stmt.setInt(4, counselor_id);         

          return stmt.executeUpdate() != 0;
        }
      }
    }

    /**
     * Deletes a counsellor via the API (placeholder for now).
     * @param counselorId The ID of the counsellor to delete.
     * @return true if the counsellor was found and deleted, false otherwise.
     */
    public boolean deleteCounsellor(String counselorId) throws SQLException {
      if(counselorId.startsWith("C"))
        counselorId = counselorId.substring(1);
      int counselor_id = Integer.parseInt(counselorId);
      try(Connection connection = DataManager.getConnection()) {
        try(PreparedStatement stmt = connection.prepareStatement("UPDATE counselors SET local_deleted = true WHERE counselor_id = ? AND NOT local_deleted;")) {
          stmt.setInt(1, counselor_id);
          return stmt.executeUpdate() != 0;
        }
      }
    }
}
