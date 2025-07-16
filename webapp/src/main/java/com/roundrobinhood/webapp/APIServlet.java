package com.roundrobinhood.webapp;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import com.roundrobinhood.shared.Session;
import com.roundrobinhood.shared.Student;
import com.roundrobinhood.webapp.db.SessionStorage;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class APIServlet extends HttpServlet {
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    Session session;
    Student student;
    try {
      session = SessionStorage.GetSession(req, resp);
      if(session.student_number == null) {
        resp.setStatus(401);
        return;
      }
      student = SessionStorage.getStudent(session.session_id);
    } catch(SQLException ex) {
      System.err.println("SQLException: " + ex);
      resp.setStatus(500);
      return;
    }

    String path = req.getPathInfo();
    String query = req.getQueryString();
    if(query != null) path += "?" + query;

    byte[] body = req.getInputStream().readAllBytes();

    HttpURLConnection connection = (HttpURLConnection) new URL(Config.getPostGrestURL() + path).openConnection();

    connection.setRequestMethod(req.getMethod());
    System.out.println(req.getMethod());
    connection.setDoInput(true);
    if (body.length != 0 && !req.getMethod().equalsIgnoreCase("GET")) {
        connection.setDoOutput(true);
        connection.getOutputStream().write(body);
    }
    
    if(req.getContentType() != null) {
      connection.setRequestProperty("Content-Type", req.getContentType());
    }
    
    connection.setRequestProperty("X-Request-Student-Number", session.student_number.toString());
    connection.setRequestProperty("X-Request-Role", student.role);

    resp.setStatus(connection.getResponseCode());

    connection.getHeaderFields().forEach((key, values) -> {
      if(key != null) {
        for(String value : values) {
          resp.addHeader(key, value);
        }
      }
    });

    InputStream responseStream = connection.getErrorStream() != null
      ? connection.getErrorStream()
      : connection.getInputStream();

    byte[] responseBody = responseStream.readAllBytes();
    resp.getOutputStream().write(responseBody);

  }
}
