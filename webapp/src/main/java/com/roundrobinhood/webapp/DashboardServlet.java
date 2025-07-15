package com.roundrobinhood.webapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.roundrobinhood.shared.Session;
import com.roundrobinhood.webapp.db.DBConnection;
import com.roundrobinhood.webapp.db.SessionStorage;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DashboardServlet extends HttpServlet {
  public final String jspPath = "/WEB-INF/pages/dashboard.jsp";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    RequestDispatcher dispatcher = req.getRequestDispatcher(jspPath);
    try {
      Session session = SessionStorage.GetSession(req, resp);
      if(session.student_number == null) {
        SessionStorage.UpdateSessionFlashMsg(session.session_id, "Must be logged in to access dashboard.");
        resp.sendRedirect("/login");
        return;
      }
      try(Connection con = DBConnection.getConnection()) {
        try(PreparedStatement stmt = con.prepareStatement("SELECT * FROM student WHERE student_number = ?;")) {
          stmt.setInt(1, session.student_number);
          try(ResultSet rs = stmt.executeQuery()) {
            rs.next();
            req.setAttribute("name", rs.getString("name"));
            req.setAttribute("student_number", session.student_number);
          }
        }
      }
      dispatcher.forward(req, resp);
      return;
    } catch(SQLException ex) {
      System.out.println("SQLException: " + ex);
      System.out.flush();
      req.setAttribute("error", "Internal error occurred.");
      dispatcher.forward(req, resp);
      return;
    }
  }
}
