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

  public static Session LoadSession(String sessid) throws SQLException {
    try(Connection con = dataSource.getConnection()) {
      try(PreparedStatement stmt = con.prepareStatement("SELECT * FROM user_session WHERE session_id = ?")) {
        stmt.setString(1, sessid);
        try(ResultSet rs = stmt.executeQuery()) {
          if(!rs.next()) {
            return null;
          } else {
            Integer student_number = rs.getInt("student_number");
            if(rs.wasNull()) student_number = null;
            Timestamp expiry_date = rs.getTimestamp("expiry_date");
            Session ret = new Session(sessid, student_number, expiry_date);
            ret.flash_msg = rs.getString("flash_msg");
            return ret;
          }
        }
      }
    }
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
          student_number INT,
          flash_msg      VARCHAR,
          expiry_date    TIMESTAMP NOT NULL,
          PRIMARY KEY(session_id),
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
