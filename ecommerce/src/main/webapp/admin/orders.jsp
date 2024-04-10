<%@ page import="uz.pdp.ecommerce.repo.OrderRepo" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.ecommerce.entity.Order" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.time.temporal.TemporalAccessor" %><%--
  Created by IntelliJ IDEA.
  User: orazboyevolmosbek
  Date: 01/04/24
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .order-details {
            display: none;
        }
    </style>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
</head>
<body>
<%
    List<Order> orders = OrderRepo.findAll();
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
            <a href="orderDetails.jsp?id=<%=order.getId()%>" class="btn btn-primary float-right show-details-btn text-white">Show details</a>
        </li>
    </ul>
    <%}%>
    <a href="admin.jsp" class="btn btn-info text-white">Back to admin page</a>
</div>

</body>
</html>
