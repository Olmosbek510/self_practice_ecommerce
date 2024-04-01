<%@ page import="java.util.UUID" %>
<%@ page import="uz.pdp.ecommerce.repo.ProductRepo" %>
<%@ page import="uz.pdp.ecommerce.entity.Product" %>
<%@ page import="uz.pdp.ecommerce.repo.CategoryRepo" %>
<%@ page import="uz.pdp.ecommerce.entity.Category" %>
<%@ page import="java.util.List" %><%--
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
    <link rel="stylesheet", href="../static/bootstrap.min.css">
</head>
<body>
<%
    UUID uuid = UUID.fromString(request.getParameter("id"));
    Product product = ProductRepo.findById(uuid);
    List<Category> categories = CategoryRepo.findAll();
%>
<div class="row py-3">
    <div class="col-4 offset-4">
        <div class="card p-2">
            <form action="${pageContext.request.contextPath}/product/edit" method="post">
                <input name="id" type="hidden" value="<%=product.getId()%>">
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Product name</label>
                    <input value="<%=product.getName()%>" name ="name" type="text" class="form-control" id="exampleInputEmail1">
                </div>
                <div class="mb-3">
                    <label for="exampleInputEmail2" class="form-label">Product price</label>
                    <input value="<%=product.getPrice()%>" name ="price" type="number" class="form-control" id="exampleInputEmail2">
                </div>
                <div class="mb-3">
                    <label for="exampleInputEmail3" class="form-label">Category</label>
                    <select class="form-control" name="categoryId" id="exampleInputEmail3">
                        <%for (Category category : categories) {%>
                        <option value="<%=category.getId()%>"><%=category.getName()%></option>
                        <%}%>
                    </select>
                </div>
                <button class="btn btn-primary">Edit</button>
            </form>
        </div>
    </div>
</div>


</body>
</html>
