<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html>
<head>
  <title>Log in</title>
  <link rel="stylesheet" href="/styling/common.css" />
  <style>
    /* Specific styles for Login Page */
    .container {
      max-width: 500px;
    }

    .container h1 {
      font-size: 2.5rem;
      color: var(--accent-color-primary);
      border-bottom: 2px solid var(--border-color);
      padding-bottom: 1rem;
      margin: 0 0 2rem 0;
      font-weight: 600;
    }

    .container form {
      display: grid;
      grid-template-columns: 1fr 1.5fr;
      padding-top: 1rem;
      row-gap: 1rem;
      column-gap: 1rem;
      text-align: left;
    }

    form label {
      font-size: 1.05rem;
      color: var(--text-color-primary);
      align-self: center;
    }

    form input[type=submit] {
      grid-column-start: 1;
      grid-column-end: 3;
      margin-top: 1.5rem;
    }

    .error {
      margin: 1rem -3.5rem 0;
    }

  </style>
</head>
<body>
<div class="box">
  <div class="container">
    <h1> Log in </h1>
    <% if(request.getAttribute("error") != null) { %>
    <span class="error"> ${error} </span>
    <% } %>
    <form method="POST" action="/LoginServlet">
      <label for="email">Email:</label>
      <input name="email" id="email" type="email" required/>
      <label for="password">Password:</label>
      <input name="password" id="password" type="password" required/>
      <input type="submit" value="Log in"/>
    </form>
  </div>
</div>
</body>
</html>