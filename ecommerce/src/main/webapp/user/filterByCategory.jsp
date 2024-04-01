<%@ page import="uz.pdp.ecommerce.entity.Category" %>
<%@ page import="uz.pdp.ecommerce.repo.CategoryRepo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="uz.pdp.ecommerce.repo.ProductRepo" %>
<%@ page import="uz.pdp.ecommerce.entity.Product" %>
<%@ page import="uz.pdp.ecommerce.repo.BasketProductRepo" %>
<%@ page import="java.util.UUID" %><%--
  Created by IntelliJ IDEA.
  User: orazboyevolmosbek
  Date: 31/03/24
  Time: 13:34
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
    List<Category> categories = null;
    try {
        categories = CategoryRepo.findAll();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    UUID cat_id = UUID.fromString(request.getParameter("id"));
    List<Product> products = ProductRepo.findByCategory(cat_id);
%>
<nav class="navbar bg-body-tertiary bg-dark text-white">
    <div class="container-fluid">
        <a class="navbar-brand">G36Ecommerce</a>
        <form class="d-flex" role="search">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
        <a href="../user/basket.jsp" class="btn btn-info">Basket</a>
        <a href="../admin/admin.jsp" class="btn btn-success">Admin</a>
    </div>
</nav>

<div class="row">
    <div class="col-3 border-right">
        <ul class="list-group py-3">
            <%for (Category category : categories) {%>
            <a href="filterByCategory.jsp?id=<%=category.getId()%>">
                <li class="list-group-item mt-1"><%=category.getName()%>
                </li>
            </a>
            <%}%>
        </ul>
    </div>
    <div class="col-9">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Category</th>
                            <th>#</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%for (Product product : products) {%>
                        <tr>
                            <td><%=product.getName()%>
                            </td>
                            <td><%=product.getPrice()%>
                            </td>
                            <td><%=product.getCategory()%>
                            </td>
                            <td>
                                <%if (BasketProductRepo.inBasket(product.getId())) {%>
                                <div class="btn btn-info text-white">Added to basket</div>
                                <%} else {%>
                                <a href="${pageContext.request.contextPath}/product/addToBasket?id=<%=product.getId()%>"
                                   class="btn btn-info text-white">Add to basket</a>
                                <%}%>
                            </td>
                        </tr>
                        <%}%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
