package com.belgiumcampus.wellness.controller;

import com.belgiumcampus.wellness.classes.Admin;
import com.belgiumcampus.wellness.util.DataManager;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

public class AdminService {

    /**
     * Adds a new admin via the API (placeholder for now).
     * @param name Admin's name.
     * @param surname Admin's surname.
     * @param email Admin's email.
     * @param phone Admin's phone number.
     * @param password Admin's password.
     * @return The created Admin object, or null if creation failed.
     */
    public Admin addAdmin(String name, String surname, String email, String phone, String password) throws SQLException {
      System.out.println("AdminService: Attempting to add admin (API call placeholder)");
      // In the future, this will make an HTTP POST request to your API
      try(Connection connection = DataManager.getConnection()) {
        int newId = -1;
        try(Statement stmt = connection.createStatement()) {
          try(ResultSet rs = stmt.executeQuery("SELECT MIN(student_number) FROM students WHERE NOT local_deleted;")) {
            if(rs.next())
              newId = Math.min(-1, rs.getInt(1) - 1);
          }
        }
        try(PreparedStatement stmt = connection.prepareStatement("INSERT INTO students (student_number, name, surname, email, phone, password, role, join_date) VALUES (?, ?, ?, ?, ?, ?, 'admin', now());")) {
          stmt.setInt(1, newId);
          stmt.setString(2, name);
          stmt.setString(3, surname);
          stmt.setString(4, email);
          stmt.setString(5, phone);
          stmt.setString(6, BCrypt.hashpw(password, BCrypt.gensalt()));
          stmt.executeUpdate();
        }
      }
      Admin newAdmin = Admin.addAdmin(name, surname, email, phone, password);
      return newAdmin;
    }

    /**
     * Retrieves an admin by their email via the API (placeholder for now).
     * @param email The unique email of the admin.
     * @return The Admin object, or null if not found.
     */
    public Admin getAdminByEmail(String email) throws SQLException {
      if(email == null) return null;
    
      System.out.println("AdminService: Attempting to retrieve admin by email");
      try(Connection connection = DataManager.getConnection()) {
        try(PreparedStatement stmt = connection.prepareStatement("SELECT * FROM students WHERE role = 'admin' and not local_deleted and email = ?;")) {
          stmt.setString(1, email);
          try(ResultSet rs = stmt.executeQuery()) {
            if(!rs.next())
              return null;
            else {
              String name = rs.getString("name");
              String surname = rs.getString("surname");
              String phone = rs.getString("phone");
              return new Admin(name, surname, email, phone, null);
            }
          }
        }
      }
    }

    /**
     * Retrieves all admins via the API (placeholder for now).
     * @return A list of all Admin objects.
     */
    public List<Admin> getAllAdmins() throws SQLException {
        List<Admin> admins = new ArrayList<>();
        try(Connection connection = DataManager.getConnection()) {
          try(Statement stmt = connection.createStatement()) {
            try(ResultSet rs = stmt.executeQuery("SELECT * FROM students WHERE role = 'admin' AND NOT local_deleted;")) {
              while(rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                admins.add(new Admin(name, surname, email, phone, null));
              }
            }
          }
        }
        return admins;
    }

    /**
     * Updates an existing admin's information via the API (placeholder for now).
     * @param email The email of the admin to update.
     * @param newName New name.
     * @param newSurname New surname.
     * @param newPhone New phone number.
     * @param newPassword New password.
     * @return true if the admin was found and updated, false otherwise.
     */
    public boolean updateAdmin(String email, String newName, String newSurname, String newPhone, String newPassword) throws SQLException {
        System.out.println("AdminService: Attempting to update admin (API call placeholder)");
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
        if(fields.isEmpty())
          return false;
        
        try(Connection connection = DataManager.getConnection()) {
          String sql = "UPDATE students SET " + String.join(",", fields) + ", local_modified = true WHERE email = ? AND NOT local_deleted AND role = 'admin'";
          try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            for(int i = 0;i < fields.size(); i++) {
              stmt.setString(i+1, values.get(i));
            }
            stmt.setString(fields.size() + 1, email);
            return stmt.executeUpdate() != 0;
          }
        }
    }

    /**
     * Deletes an admin via the API (placeholder for now).
     * @param email The email of the admin to delete.
     * @return true if the admin was found and deleted, false otherwise.
     */
    public boolean deleteAdmin(String email) throws SQLException {
        try(Connection connection = DataManager.getConnection()) {
          try(PreparedStatement stmt = connection.prepareStatement("UPDATE students SET local_deleted = true WHERE email = ? and role = 'admin' AND NOT local_deleted")) {
            stmt.setString(1, email);
            return stmt.executeUpdate() != 0;
          }
        }
    }
}
