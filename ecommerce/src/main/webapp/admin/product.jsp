<%@ page import="uz.pdp.ecommerce.repo.ProductRepo" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.ecommerce.entity.Product" %>
<%@ page import="java.util.SplittableRandom" %>
<%@ page import="uz.pdp.ecommerce.entity.Category" %>
<%@ page import="uz.pdp.ecommerce.repo.CategoryRepo" %><%--
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
                <div class="col-12">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Category</th>
                            <th>#</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%for (Product product : products) {%>
                        <tr>
                            <td><%=product.getId()%>
                            </td>
                            <td><%=product.getName()%>
                            </td>
                            <td><%=product.getPrice()%>
                            </td>
                            <td><%=product.getCategory()%>
                            </td>
                            <td>
                                <a href="editProduct.jsp?id=<%=product.getId()%>"
                                   class="btn btn-info text-white">edit</a>
                                <a href="http://localhost:8080/ecommerce/product/delete?id=<%=product.getId()%>"
                                   class="btn btn-danger">delete</a>
                            </td>
                        </tr>
                        <%}%>
                        </tbody>
                    </table>
                    <a href="${pageContext.request.contextPath}/" class="btn btn-info text-white">Back to home</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
