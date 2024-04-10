<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.ecommerce.entity.Order" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="uz.pdp.ecommerce.repo.OrderRepo" %>
<%@ page import="uz.pdp.ecommerce.entity.WebUser" %><%--
  Created by IntelliJ IDEA.
  User: orazboyevolmosbek
  Date: 06/04/24
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
    <title>Title</title>
    <style>
        .order-details {
            display: none;
        }
    </style>
</head>
<body>
<%
    WebUser currentUser = null;
    Object o = session.getAttribute("currentUser");
    if(o!=null){
        currentUser = (WebUser)o;
    }
    assert currentUser != null;
    List<Order> orders = OrderRepo.findByUserId(currentUser);
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm");
%>
<div class="container mt-5">
    <h1>Order List</h1>
    <%for (Order order : orders) {%>
    <ul class="list-group">
        <li class="list-group-item">
            Order #<%=order.getId()%>
            <br>
            <small>Order Time: <%=dateTimeFormatter.format(order.getDateTime().toLocalDateTime())%></small>
            <a href="usersOrderDetails.jsp?id=<%=order.getId()%>" class="btn btn-primary float-right show-details-btn text-white">Show details</a>
        </li>
    </ul>
    <%}%>
    <a href="profile.jsp" class="btn btn-info text-white">Back to profile</a>
</div>
</body>
</html>
