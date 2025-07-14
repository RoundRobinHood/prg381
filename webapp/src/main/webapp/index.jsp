<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Index</title>
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
    .container h2 {
      padding-top: 1rem;
    }
  </style>
</head>
<body>
  <div class="box">
    <div class="container">
      <h1>Index</h1>
      <h2>Useful links:</h2>
      <ul>
        <li><a href="/register"> Register </a></li>
        <li><a href="/login"> Log in </a></li>
      </ul>
    </div>
  </div>
</body>
</html>
