<%--
  Created by IntelliJ IDEA.
  User: orazboyevolmosbek
  Date: 30/03/24
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add category</title>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
</head>
<body>
<div class="row py-3">
    <div class="col-4 offset-4">
        <div class="card p-2">
            <form action="${pageContext.request.contextPath}/category/add" method="post">
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Category name</label>
                    <input name ="name" type="text" class="form-control" id="exampleInputEmail1">
                </div>
                <button class="btn btn-primary">Save</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
