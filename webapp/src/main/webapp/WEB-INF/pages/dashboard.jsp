<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html>
<head>
  <title>Dashboard</title>
  <link rel="stylesheet" href="/styling/common.css" />
  <style>
    /* Specific styles for Dashboard Page */
    .container {
      max-width: 900px;
    }

    .container h1 {
      font-size: 3rem;
      color: var(--accent-color-primary);
      border-bottom: 2px solid var(--border-color);
      padding-bottom: 1.2rem;
      margin: 0 0 2rem 0;
      font-weight: 600;
      word-wrap: break-word;
    }

    .container h2 {
      font-size: 2rem;
      color: var(--text-color-primary);
      margin-top: 2.5rem;
      margin-bottom: 1rem;
      border-bottom: 1px solid var(--border-color);
      padding-bottom: 0.8rem;
      text-align: left;
      font-weight: 500;
    }

    .container p, .container ul {
      color: var(--text-color-secondary);
      text-align: left;
      line-height: 1.8;
      margin-bottom: 1.2rem;
      font-size: 1.05rem;
    }
    .container ul {
      list-style-position: inside;
      padding-left: 1.5rem;
      margin-bottom: 2rem;
    }
    .container li {
      margin-bottom: 0.6rem;
    }

    .logout-form {
      padding-top: 2.5rem;
      text-align: center;
    }
    .logout-form input[type="submit"] {
      padding: 1rem 3rem;
      font-size: 1.2rem;
      cursor: pointer;
      border-radius: 12px;
      border: none;
      background-color: var(--accent-color-primary);
      color: white;
      transition: background-color 0.3s ease, transform 0.1s ease, box-shadow 0.3s ease;
      text-transform: uppercase;
      font-weight: bold;
      box-shadow: 0 4px 8px var(--shadow-color);
    }
    .logout-form input[type="submit"]:hover {
      background-color: var(--accent-color-hover);
      transform: translateY(-3px);
      box-shadow: 0 6px 12px rgba(0,0,0,0.15);
    }
    .logout-form input[type="submit"]:active {
      transform: translateY(0);
      box-shadow: 0 2px 4px var(--shadow-color);
    }

    hr {
      margin: 2.5rem -3.5rem;
      border: 0;
      height: 1px;
      background-color: var(--border-color);
    }

  </style>
</head>
<body>
<div class="box">
  <div class="container">
    <h1>Hello, ${name}(Nr: ${student_number})! Welcome to the dashboard.</h1>

    <hr/>

    <div class="project-overview-section">
      <h2>Team Members</h2>
      <ul>
        <li>Ruan le Roux (600194)</li>
        <li>Neil Nothnagel (578121)</li>
        <li>Brian Felgate (600504)</li>
        <li>Natasja Nell (600316)</li>
      </ul>

      <h2>Application Description</h2>
      <p>
        The BC Student Wellness Management System is a comprehensive application tasked by Belgium Campus.
        Its purpose is to facilitate the management of wellness services for students. The system is
        composed of two main parts:
      </p>
      <p>
        1.  <strong>Web-based JSP Login and Registration System:</strong> This component allows students to register
        for new accounts and log in to access the wellness services. It handles user authentication
        and secure data storage.
      </p>
      <p>
        2.  <strong>Swing Desktop Application - Wellness Management System:</strong> This is a desktop application
        developed using Java Swing. It provides robust management capabilities for key wellness services,
        including:
      </p>
      <ul>
        <li><strong>Appointment Management:</strong> Users can book new appointments, selecting a counsellor, date, and time.
          It also allows for viewing all upcoming appointments, updating appointment details (such as rescheduling),
          and canceling appointments.</li>
        <li><strong>Counsellor Management:</strong> The system facilitates adding new counsellors with their specializations and
          availability. It also enables viewing a list of existing counsellors, updating their details, and
          removing them.</li>
        <li><strong>Student Feedback:</strong> Students can submit feedback on services, including a rating from 1 to 5 and comments.
          The system also supports viewing the feedback history and the ability to edit or delete feedback entries.</li>
      </ul>
      <p>
        The entire system is designed with an MVC (Model-View-Controller) architecture and leverages Java's core OOP principles,
        collections for data management, and exception handling. Data persistence for the web application is handled via PostgreSQL,
        while the desktop application will manage its data with JavaDB. The project emphasizes collaborative development using
        GitHub for version control.
      </p>
    </div>

    <hr/>

    <form action="/logout" method="POST" class="logout-form">
      <input type="submit" value="Log Out"/>
    </form>
  </div>

</div>
</body>
</html>
