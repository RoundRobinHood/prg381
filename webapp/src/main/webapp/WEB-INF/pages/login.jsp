<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Log in</title>
  <meta name="color-scheme" content="dark light" />
  <link rel="stylesheet" href="/styling/common.css" />
  <style>
    .box {
      display: flex;
      width: 100vw;
      height: 100vh;
      align-items: center;
      justify-content: center;
    }
    .container {
      border: 3px solid ButtonFace;
      border-radius: 10px;
      padding: 1rem 2rem;
    }
    .container h1 {
      border-bottom: 2px solid ButtonFace;
      text-align: center;
      margin: 0 -2rem;
      padding-bottom: 1rem;
    }
    .container form {
      display: grid;
      grid-template-columns: 1fr 1fr;
      padding-top: 1rem;
      row-gap: 0.5rem;
    }
    form input[type=submit] {
      grid-column-start: 1;
      grid-column-end: 3;
    }
    .error {
      margin: 0 -2rem;
      background-color: red;
      text-align: center;
      display: block;
      padding: 0.1rem 0;
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
      <form method="POST">
        <label for="email">Email:  </label>
        <input name="email" id="email" type="email"/>
        <label for="password">Password  </label>   
        <input name="password" id="password" type="password"/>
        <input type="submit" value="Log in"/>
      </form>
    </div>
  </div>
</body>
</html>
