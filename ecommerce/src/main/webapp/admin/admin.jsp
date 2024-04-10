<%--
  Created by IntelliJ IDEA.
  User: orazboyevolmosbek
  Date: 30/03/24
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
</head>
<body>
<div class="row">
    <div class="col-3 border-right">
        <ul class="list-group py-3">
            <a href="category.jsp"><li class="list-group-item mt-1">Category</li></a>
            <a href="product.jsp"><li class="list-group-item mt-1">Product</li></a>
            <a href="orders.jsp"><li class="list-group-item mt-1">Orders</li></a>
        </ul>
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger text-white">Logout</a>
    </div>
    <div class="col-9">

    </div>
</div>
</body>
</html>
