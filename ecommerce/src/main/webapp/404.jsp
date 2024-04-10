<%--
  Created by IntelliJ IDEA.
  User: orazboyevolmosbek
  Date: 05/04/24
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="static/bootstrap.min.css">
    <title>Error</title>
    <style>
        body {
            background-color: #f8f9fa;
        }
        .error-container {
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
        }
        .error-code {
            font-size: 10rem;
            font-weight: bold;
            color: #dc3545;
            margin-bottom: 20px;
        }
        .error-message {
            font-size: 2rem;
            color: #6c757d;
            margin-bottom: 20px;
        }
        .error-back-button {
            font-size: 1.2rem;
        }
    </style>
</head>
<body>
<div class="container error-container">
    <div class="text-center">
        <div class="error-code">404</div>
        <div class="error-message">Oops! Page Not Found</div>
        <p class="error-back-button">Looks like you've hit a dead end. Let's get you back on track.</p>
        <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-primary">Go to Homepage</a>
    </div>
</div>
</body>
</html>
