package com.roundrobinhood.webapp.db;

import com.roundrobinhood.shared.Session;
import com.roundrobinhood.webapp.Config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Statement;
import java.sql.Timestamp;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HexFormat;
import java.util.LinkedHashMap;
import java.util.Map;

public class DBConnection {
  private static final HikariDataSource dataSource;
  private static final SecureRandom randomizer = new SecureRandom();

  public static String newSessID() {
    byte[] bytes = new byte[16];
    randomizer.nextBytes(bytes);
    return HexFormat.of().formatHex(bytes);
  }

  public static Session CreateSession(int student_number, Timestamp expiry_date) throws SQLException {
    Session sess = new Session(newSessID(), student_number, expiry_date);

    try(Connection con = dataSource.getConnection()) {
      try(PreparedStatement stmt = con.prepareStatement("INSERT INTO user_session (student_number, session_id, expiryDate) VALUES (?, ?, ?);")) {
        stmt.setInt(1, sess.student_number);
        stmt.setString(2, sess.session_id);
        stmt.setTimestamp(3, sess.expiry_date);
        stmt.executeUpdate();
      }
    }

    return sess;
  }

  static {
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl(Config.getConnectionStr());
    config.setUsername(Config.getDbUser());
    config.setPassword(Config.getDbPass());
    config.setMaximumPoolSize(10); // You can tune this
    config.setPoolName("MyAppPool");

    dataSource = new HikariDataSource(config);
    try {
      schemaCheck(); // <- automatic schema verification/creation
    } catch (SQLException e) {
      System.err.println("Fatal error during schema check: " + e.getMessage());
      e.printStackTrace();
      // Rethrow as unchecked to crash hard and loud
      throw new RuntimeException("Failed to initialize DB schema", e);
    }
  }

  public static Connection getConnection() throws SQLException {
    return dataSource.getConnection();
  }

  public static void shutdown() {
    dataSource.close();
  }

  // Checks and creates schema if missing
  public static boolean schemaCheck() throws SQLException {
    Map<String, String> cmds = new LinkedHashMap<>();
    cmds.put("student", """
        CREATE TABLE student (
          student_number SERIAL PRIMARY KEY,
          name           VARCHAR(70) NOT NULL,
          surname        VARCHAR(70) NOT NULL,
          email          VARCHAR(100) NOT NULL UNIQUE,
          phone          VARCHAR(20) NOT NULL,
          password       CHAR(60) NOT NULL,
          joinDate       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
        );
      """);
    cmds.put("user_session", """
        CREATE TABLE user_session (
          session_id     CHAR(32) UNIQUE NOT NULL,
          student_number INT NOT NULL,
          expiryDate     TIMESTAMP NOT NULL,
          FOREIGN KEY(student_number) REFERENCES student(student_number)
        );
      """);
    boolean allPresent = true;
    try (Connection con = getConnection()) {
      for (Map.Entry<String, String> entry : cmds.entrySet()) {
        String table = entry.getKey().toLowerCase();
        String createSql = entry.getValue();

        boolean exists;
        try (PreparedStatement stmt = con.prepareStatement("""
          SELECT EXISTS (
            SELECT 1 FROM information_schema.tables
            WHERE table_schema = 'public' AND table_name = ?
          )
        """)) {
          stmt.setString(1, table);
          try (ResultSet rs = stmt.executeQuery()) {
            rs.next();
            exists = rs.getBoolean(1);
          }
        }

        if (!exists) {
          allPresent = false;
          try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate(createSql);
          }
        }
      }
    }

    return allPresent;
  }
}
