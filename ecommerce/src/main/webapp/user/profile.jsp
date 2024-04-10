<%@ page import="uz.pdp.ecommerce.entity.WebUser" %><%--
  Created by IntelliJ IDEA.
  User: orazboyevolmosbek
  Date: 06/04/24
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
    <style>
        body {
            background: linear-gradient(to bottom, #4b6cb7, #182848); /* Positive gradient background */
            font-family: Arial, sans-serif;
        }
        .container {
            max-width: 800px;
            margin: 50px auto;
            background-color: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .profile-card {
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            background-color: #f8f9fa;
        }
        .profile-card h2 {
            color: #007bff;
        }
        .profile-details {
            margin-bottom: 20px;
        }
        .btn-logout, .btn-edit-profile, .btn-my-orders {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            font-weight: bold;
            transition: all 0.3s ease;
            margin-bottom: 10px;
        }
        .btn-logout {
            background-color: #dc3545;
            color: #fff;
        }
        .btn-logout:hover {
            background-color: #c82333;
        }
        .btn-edit-profile {
            background-color: #28a745;
            color: #fff;
        }
        .btn-edit-profile:hover {
            background-color: #218838;
        }
        .btn-my-orders {
            background-color: #007bff;
            color: #fff;
        }
        .btn-my-orders:hover {
            background-color: #0056b3;
        }
    </style>
    <title>Profile</title>
</head>
<body>
<%
    WebUser user = null;
    Object o = session.getAttribute("currentUser");
    if(o!=null){
        user = (WebUser)o;
    }
    assert user != null;%>
<div class="container">
    <div class="profile-card">
        <h2 class="mb-4 text-center">User Profile</h2>
        <div class="profile-details">
            <p><strong>First Name: </strong><%=user.getFirstname()%></p>
            <p><strong>Last Name: </strong><%=user.getLastname()%></p>
            <p><strong>Username: </strong><%=user.getUsername()%></p>
        </div>
        <a href="ordersByUser.jsp" class="btn btn-my-orders">My Orders</a>
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-logout">Logout</a>
        <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-info text-white">Back</a>
    </div>
</div>
</body>
</html>
