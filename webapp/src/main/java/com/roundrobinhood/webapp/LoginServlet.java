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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

public class LoginServlet extends HttpServlet {
  public final String jspPath = "/WEB-INF/pages/login.jsp";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    req.getRequestDispatcher(jspPath).forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    String email = req.getParameter("email");
    String password = req.getParameter("password");

    RequestDispatcher jspDispatch = req.getRequestDispatcher(jspPath);

    if(email == null || password == null || email.isEmpty() || password.isEmpty()) {
      req.setAttribute("error", "Email and password are required.");
      jspDispatch.forward(req, resp);
    } else {
      try(Connection con = DBConnection.getConnection()) {
        try(PreparedStatement stmt = con.prepareStatement("SELECT * FROM student WHERE email = ?;")) {
          stmt.setString(1, email);

          try(ResultSet rs = stmt.executeQuery()) {
            if(!rs.next()) {
              resp.setStatus(401);
              req.setAttribute("error", "Incorrect email/password.");
              jspDispatch.forward(req, resp);
              return;
            } else {
              if(BCrypt.checkpw(password, rs.getString("password"))) {
                Session sess = SessionStorage.NewSession((Integer)rs.getInt("student_number"));
                Cookie cookie = SessionStorage.CreateCookie(sess);
                resp.addCookie(cookie);
                resp.sendRedirect("/dashboard");
                return;
              } else {
                resp.setStatus(401);
                req.setAttribute("error", "Incorrect email/password.");
                jspDispatch.forward(req, resp);
                return;
              }
            }
          }
        }
      } catch(SQLException ex) {
        System.out.println("SQLException: " + ex);
        req.setAttribute("error", "Internal error occurred.");
        resp.setStatus(500);
        jspDispatch.forward(req, resp);
        return;
      }
    }
  }
}
