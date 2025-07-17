package com.roundrobinhood.webapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.roundrobinhood.shared.Session;
import com.roundrobinhood.webapp.db.DBConnection;
import com.roundrobinhood.webapp.db.SessionStorage;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    Session session;
    try {
      session = SessionStorage.GetSession(req, resp);
      try(Connection connection = DBConnection.getConnection()) {
        try(PreparedStatement stmt = connection.prepareStatement("DELETE FROM user_sessions WHERE session_id = ?;")) {
          stmt.setString(1, session.session_id);
          stmt.executeUpdate();
        }
      }
    } catch(SQLException ex) {
      System.err.println("SQLException: " + ex);
      resp.setStatus(500);
      return;
    }

    Cookie cookie = SessionStorage.DeleteCookie(session.session_id);
    resp.addCookie(cookie);
    resp.sendRedirect("/");
  }
}
