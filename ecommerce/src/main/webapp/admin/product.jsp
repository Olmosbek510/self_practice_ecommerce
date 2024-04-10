<%@ page import="uz.pdp.ecommerce.repo.ProductRepo" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.ecommerce.entity.Product" %>
<%@ page import="java.util.SplittableRandom" %>
<%@ page import="uz.pdp.ecommerce.entity.Category" %>
<%@ page import="uz.pdp.ecommerce.repo.CategoryRepo" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.util.Arrays" %><%--
  Created by IntelliJ IDEA.
  User: orazboyevolmosbek
  Date: 30/03/24
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
</head>
<body>
<%
    List<Product> products = ProductRepo.findAll();
%>

<div class="row">
    <div class="col-3 border-right">
        <ul class="list-group py-3">
            <a href="category.jsp">
                <li class="list-group-item mt-1">Category</li>
            </a>
            <a href="product.jsp">
                <li class="list-group-item mt-1">Product</li>
            </a>
            <a href="orders.jsp"><li class="list-group-item mt-1">Orders</li></a>
        </ul>
    </div>
    <div class="col-9">
        <div class="container">
            <div class="row">
                <div class="col-2 offset-10">
                    <a href="addProduct.jsp" class="btn btn-dark my-3 text-white">Add product</a>
                </div>
            </div>
            <div class="row">
                <% for (Product product : products) { %>
                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card">
                        <%
                            String s = "";
                            if (product.getPhoto() != null) {
                                s = Base64.getEncoder().encodeToString(product.getPhoto());
                            }
                        %>
                        <%if(product.getPhoto() != null){%>
                            <img width="300" src="data:image/png;base64,<%=s%>" alt="Product Image">
                            <%System.out.println("Entering here");%>
                        <%} else {%>
                            <img src="https://example.com/default-image.jpg" alt="Product Image">
                        <%}%>
                        <div class="card-body">
                            <h5 class="card-title"><%= product.getName() %>
                            </h5>
                            <p class="card-text">Price: <%= product.getPrice() %>
                            </p>
                            <p class="card-text">Category: <%= product.getCategory() %>
                            </p>
                            <a href="editProduct.jsp?id=<%= product.getId() %>" class="btn btn-info text-white">Edit</a>
                            <a href="http://localhost:8080/ecommerce/product/delete?id=<%= product.getId() %>"
                               class="btn btn-danger">Delete</a>
                        </div>
                    </div>
                </div>
                <% } %>
            </div>
            <a href="admin.jsp" class="btn btn-info text-white">Back to admin page  </a>
        </div>
    </div>
</div>
</body>
</html>
