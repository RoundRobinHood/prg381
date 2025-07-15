package com.roundrobinhood.webapp.db;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.Duration;
import java.time.Instant;
import java.util.HexFormat;

import com.roundrobinhood.shared.Session;
import com.roundrobinhood.webapp.Config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SessionStorage {
  public static Session GetSession(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
    String sessid = null;
    if(req.getCookies() != null)
    for(Cookie cookie: req.getCookies()) {
      if(cookie.getName().equals("sessid")) {
        sessid = cookie.getValue();
        break;
      }
    }
    if(sessid == null) {
      Session session = NewSession((Integer)null);
      resp.addCookie(CreateCookie(session));
      return session;
    } else {
      Session session = LoadSession(sessid);
      if(session == null) {
        session = NewSession((Integer)null);
        resp.addCookie(CreateCookie(session));
      }
      return session;
    }
  }
  public static Session LoadSession(String sessid) throws SQLException {
    if(sessid == null)
      throw new NullPointerException();
    
    try(Connection con = DBConnection.getConnection()) {
      try(PreparedStatement stmt = con.prepareStatement("SELECT * FROM user_session WHERE session_id = ?;")) {
        stmt.setString(1, sessid);
        try(ResultSet rs = stmt.executeQuery()) {
          if(!rs.next()) {
            return null;
          } else {
            Integer student_number = rs.getInt("student_number");
            if(rs.wasNull()) student_number = null;
            String flash_msg = rs.getString("flash_msg");
            Timestamp expiry_date = rs.getTimestamp("expiry_date");

            return new Session(sessid, student_number, expiry_date, flash_msg);
          }
        }
      }
    }
  }

  public static Cookie DeleteCookie(String sessid) {
    Cookie cookie = new Cookie("sessid", sessid);
    cookie.setHttpOnly(true);
    cookie.setSecure(Config.getHTTPSecure());
    cookie.setPath("/");
    cookie.setMaxAge(0);
    return cookie;
  }
  public static Cookie CreateCookie(Session session) {
    int MaxAge = (int) Math.max(0, Duration.between(Instant.now(), session.expiry_date.toInstant()).getSeconds());
    Cookie cookie = new Cookie("sessid", session.session_id);
    cookie.setHttpOnly(true);
    cookie.setSecure(Config.getHTTPSecure());
    cookie.setPath("/");
    cookie.setMaxAge(MaxAge);
    return cookie;
  }

  private static final SecureRandom randomizer = new SecureRandom();
  private static String newSessID() {
    byte[] bytes = new byte[16];
    randomizer.nextBytes(bytes);
    return HexFormat.of().formatHex(bytes);
  }

  private static Timestamp default_expiry() {
    return Timestamp.from(Instant.now().plus(Duration.ofHours(10)));
  }
  public static Session NewSession(String flash_msg) throws SQLException {
    Timestamp expiry_date = default_expiry();
    return NewSession(null, expiry_date, flash_msg);
  }
  public static Session NewSession(Integer student_number) throws SQLException {
    Timestamp expiry_date = default_expiry();
    return NewSession(student_number, expiry_date);
  }
  public static Session NewSession(Integer student_number, Timestamp expiry_date) throws SQLException {
    return NewSession(student_number, expiry_date, null);
  }
  public static Session NewSession(Integer student_number, Timestamp expiry_date, String flash_msg) throws SQLException {
    String sessid = newSessID();
    Session result = new Session(sessid, student_number, expiry_date, flash_msg);

    try(Connection con = DBConnection.getConnection()) {
      try(PreparedStatement stmt = con.prepareStatement("INSERT INTO user_session (session_id, student_number, flash_msg, expiry_date) VALUES (?,?,?,?);")) {
        stmt.setString(1, sessid);
        if(student_number != null)
          stmt.setInt(2, student_number);
        else
          stmt.setNull(2, Types.INTEGER);
        stmt.setString(3, flash_msg);
        stmt.setTimestamp(4, expiry_date);
        stmt.executeUpdate();
      }
    }

    return result;
  }

  public static void UpdateSessionFlashMsg(String sessid, String flash_msg) throws SQLException {
    try(Connection con = DBConnection.getConnection()) {
      try(PreparedStatement stmt = con.prepareStatement("UPDATE user_session SET flash_msg = ? WHERE session_id = ?;")) {
        stmt.setString(1, flash_msg);
        stmt.setString(2, sessid);

        if(stmt.executeUpdate() == 0) {
          throw new SQLException("Session not found");
        }
      }
    }
  }
}
