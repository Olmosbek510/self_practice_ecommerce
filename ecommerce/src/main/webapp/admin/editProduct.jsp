<%@ page import="java.util.UUID" %>
<%@ page import="uz.pdp.ecommerce.repo.ProductRepo" %>
<%@ page import="uz.pdp.ecommerce.entity.Product" %>
<%@ page import="uz.pdp.ecommerce.repo.CategoryRepo" %>
<%@ page import="uz.pdp.ecommerce.entity.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: orazboyevolmosbek
  Date: 31/03/24
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" , href="../static/bootstrap.min.css">
</head>
<body>
<%
    UUID uuid = UUID.fromString(request.getParameter("id"));
    Product product = ProductRepo.findById(uuid);
    List<Category> categories = null;
    try {
        categories = CategoryRepo.findAll();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
%>
<div class="row py-3">
    <div class="col-4 offset-4">
        <div class="card p-4">
            <form action="${pageContext.request.contextPath}/product/edit" method="post" enctype="multipart/form-data">
                <input name="id" type="hidden" value="<%=product.getId()%>">
                <label for="exampleInputEmail1" class="form-label">Product name</label>
                <input value="<%=product.getName()%>" name="name1" type="text" class="form-control" id="exampleInputEmail1">
                <label for="exampleInputEmail2" class="form-label">Product price</label>
                <input value="<%=product.getPrice()%>" name="price" type="number" class="form-control" id="exampleInputEmail2">
                <label for="exampleInputEmail3" class="form-label">Category</label>
                <select class="form-control" name="categoryId" id="exampleInputEmail3">
                    <%for (Category category : categories) {%>
                    <option value="<%=category.getId()%>"><%=category.getName()%></option>
                    <%}%>
                </select>
                <label for="photoSubmit" class="form-label">Upload Product Photo</label>
                <input type="file" name="photo" class="form-control" id="photoSubmit">
                <button class="btn btn-primary" type="submit">Edit Product</button>
            </form>
        </div>//
    </div>
</div>


</body>
</html>
