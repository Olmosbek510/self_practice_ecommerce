<%--
  Created by IntelliJ IDEA.
  User: orazboyevolmosbek
  Date: 06/04/24
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up page</title>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
    <style>
        body {
            background: linear-gradient(to right, #4b6cb7, #182848);
            color: #fff;
            font-family: Arial, sans-serif;
        }
        .container {
            max-width: 400px;
            margin: 100px auto;
            background-color: rgba(255, 255, 255, 0.1);
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            font-weight: bold;
        }
        .form-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: rgba(255, 255, 255, 0.8);
            color: #000;
        }
        .btn-signin, .btn-cancel {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            font-weight: bold;
            transition: all 0.3s ease;
        }
        .btn-signin {
            background-color: #18d26e;
            color: #fff;
        }
        .btn-signin:hover {
            background-color: #13a955;
        }
        .btn-cancel {
            background-color: #ff5252;
            color: #fff;
        }
        .btn-cancel:hover {
            background-color: #e03d3d;
        }
        .form-footer {
            text-align: center;
            margin-top: 20px;
        }
        .form-footer a {
            color: #fff;
            text-decoration: none;
        }
        .form-footer a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h2 class="mb-4 text-center">Sign Up</h2>
    <form action="${pageContext.request.contextPath}/user/register" method="post">
        <div class="form-group">
            <label for="firstname">First Name</label>
            <input name="firstname" type="text" id="firstname" class="form-control" placeholder="Enter your first name">
        </div>
        <div class="form-group">
            <label for="lastname">Last Name</label>
            <input name="lastname" type="text" id="lastname" class="form-control" placeholder="Enter your last name">
        </div>
        <div class="form-group">
            <label for="username">Username</label>
            <input name="username" type="text" id="username" class="form-control" placeholder="Choose a username">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input name="password" type="password" id="password" class="form-control" placeholder="Enter a password">
        </div>
        <button type="submit" class="btn btn-success text-white">Sign Up</button>
        <a href="../index.jsp" type="button" class="btn btn-cancel mt-3 btn-danger">Cancel</a>
    </form>
</div>
</body>
</html>
