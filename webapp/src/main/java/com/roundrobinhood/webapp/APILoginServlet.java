package com.roundrobinhood.webapp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.roundrobinhood.shared.LoginBody;
import com.roundrobinhood.shared.LoginResponseBody;
import com.roundrobinhood.shared.Session;
import com.roundrobinhood.webapp.db.DBConnection;
import com.roundrobinhood.webapp.db.SessionStorage;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

public class APILoginServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    resp.setStatus(405);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    byte[] body = req.getInputStream().readAllBytes();
    LoginBody login;
    try {
      login = LoginBody.fromJSON(new String(body, StandardCharsets.UTF_8));
    } catch(JsonProcessingException ex) {
      resp.setStatus(400);
      resp.getWriter().print(new LoginResponseBody(false, "Invalid JSON", null, null));
      return;
    }

    String email = login.email;
    String password = login.password;

    if(email == null || password == null || email.isEmpty() || password.isEmpty()) {
      resp.setStatus(400);
      resp.getWriter().print(new LoginResponseBody(false, "Email and string are required", null, null));
      return;
    } else {
      try(Connection con = DBConnection.getConnection()) {
        try(PreparedStatement stmt = con.prepareStatement("SELECT * FROM students WHERE email = ?;")) {
          stmt.setString(1, email);

          try(ResultSet rs = stmt.executeQuery()) {
            if(!rs.next()) {
              resp.setStatus(401);
              resp.getWriter().print(new LoginResponseBody(false, "Invalid credentials", null, null));
              return;
            } else {
              if(BCrypt.checkpw(password, rs.getString("password"))) {
                Session sess = SessionStorage.NewSession((Integer)rs.getInt("student_number"));
                resp.setStatus(200);
                resp.getWriter().print(new LoginResponseBody(true, "Successfully logged in", rs.getString("role"), sess.session_id));
                return;
              } else {
                resp.setStatus(401);
                resp.getWriter().print(new LoginResponseBody(false, "Invalid credentials", null, null));
                return;
              }
            }
          }
        }
      } catch(SQLException ex) {
        System.out.println("SQLException: " + ex);
        resp.setStatus(500);
        resp.getWriter().print(new LoginResponseBody(false, "Internal error occurred", null, null));
        return;
      }
    }
  }
}
