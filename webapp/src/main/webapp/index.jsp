<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html>
<head>
  <title>Welcome</title>
  <link rel="stylesheet" href="/styling/common.css" />
  <style>
    /* Specific styles for Index Page */
    .container {
      max-width: 500px;
    }

    .container h1 {
      font-size: 2.8rem;
      color: var(--accent-color-primary);
      border-bottom: 2px solid var(--border-color);
      padding-bottom: 1rem;
      margin: 0 0 2rem 0;
      font-weight: 600;
    }

    .container h2 {
      font-size: 1.8rem;
      color: var(--text-color-primary);
      padding-top: 1.5rem;
      margin-bottom: 1rem;
      text-align: center;
      font-weight: 500;
    }

    .container ul {
      list-style: none;
      padding: 0;
      margin-bottom: 1rem;
    }

    .container li {
      margin-bottom: 1rem;
    }

    .container a {
      display: inline-block;
      padding: 0.8rem 2rem;
      font-size: 1.1rem;
      text-decoration: none;
      color: white;
      background-color: var(--accent-color-primary);
      border-radius: 12px;
      transition: background-color 0.3s ease, transform 0.1s ease, box-shadow 0.3s ease;
      box-shadow: 0 4px 8px var(--shadow-color);
      text-transform: uppercase;
      font-weight: bold;
    }

  </style>
</head>
<body>
<div class="box">
  <div class="container">
    <h1>Welcome to Belgium Campus Wellness</h1>
    <h2>Get Started:</h2>
    <ul>
      <li><a href="/register"> Register </a></li>
      <li><a href="/login"> Log in </a></li>
    </ul>
  </div>
</div>
</body>
</html>