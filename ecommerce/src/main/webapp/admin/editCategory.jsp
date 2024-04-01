<%@ page import="java.util.UUID" %>
<%@ page import="uz.pdp.ecommerce.repo.CategoryRepo" %>
<%@ page import="uz.pdp.ecommerce.entity.Category" %><%--
  Created by IntelliJ IDEA.
  User: orazboyevolmosbek
  Date: 30/03/24
  Time: 23:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit category</title>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
</head>
<body>
<%
    UUID uuid = UUID.fromString(request.getParameter("id"));
    Category category = CategoryRepo.findById(uuid);
%>

<div class="row py-3">
    <div class="col-4 offset-4">
        <div class="card p-2">
            <form action="${pageContext.request.contextPath}/category/edit" method="post">
                <input name="id" type="hidden" value="<%=category.getId()%>">
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Category name</label>
                    <input value="<%=category.getName()%>" name ="name" type="text" class="form-control" id="exampleInputEmail1">
                </div>
                <button class="btn btn-primary">Edit</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
