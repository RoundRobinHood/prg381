package com.roundrobinhood.webapp;

import java.io.IOException;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.Instant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.roundrobinhood.shared.Session;
import com.roundrobinhood.webapp.db.DBConnection;
import com.roundrobinhood.webapp.db.SessionStorage;

public class RegisterServlet extends HttpServlet {
  public final String jspPath = "/WEB-INF/pages/register.jsp";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    req.getRequestDispatcher(jspPath).forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    String name = req.getParameter("name");
    String surname = req.getParameter("surname");
    String email = req.getParameter("email");
    String phone = req.getParameter("phone");
    String password = req.getParameter("password");

    RequestDispatcher jspDispatch = req.getRequestDispatcher(jspPath);

    if(name == null || surname == null || email == null || phone == null || password == null || name.isEmpty() || surname.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
      req.setAttribute("error", "All fields are required.");
      jspDispatch.forward(req, resp);
    } else {
      password = BCrypt.hashpw(password, BCrypt.gensalt());
      int student_number;

      try(Connection con = DBConnection.getConnection()) {
        try(PreparedStatement stmt = con.prepareStatement("INSERT INTO student (name, surname, email, phone, password) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
          stmt.setString(1, name);
          stmt.setString(2, surname);
          stmt.setString(3, email);
          stmt.setString(4, phone);
          stmt.setString(5, password);
          stmt.executeUpdate();
          try(ResultSet keys = stmt.getGeneratedKeys()) {
            if(!keys.next()) {
              throw new SQLException("No keys");
            }
            student_number = keys.getInt(1);
          }
        }
        Session sess = SessionStorage.NewSession(student_number);
        Cookie cookie = SessionStorage.CreateCookie(sess);

        resp.addCookie(cookie);
        resp.sendRedirect("/dashboard");
      } catch(SQLException ex) {
        System.out.println("SQLException: " + ex);
        resp.setStatus(500);
        jspDispatch.forward(req, resp);       
        return;
      }
    }
  }
}
