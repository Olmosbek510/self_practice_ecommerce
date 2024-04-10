<%@ page import="uz.pdp.ecommerce.repo.CategoryRepo" %>
<%@ page import="uz.pdp.ecommerce.repo.ProductRepo" %>
<%@ page import="uz.pdp.ecommerce.repo.BasketProductRepo" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.*" %>
<%@ page import="uz.pdp.ecommerce.repo.UserRepo" %>
<%@ page import="uz.pdp.ecommerce.entity.*" %>
<%@ page import="uz.pdp.ecommerce.entity.enums.Role" %>
<%@ page import="uz.pdp.ecommerce.services.AuthService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ecommerce</title>
    <link rel="stylesheet" href="static/bootstrap.min.css">
    <style>
        .card {
            transition: all 0.3s ease;
            border: 1px solid rgba(0, 0, 0, 0.125);
        }

        .card:hover {
            box-shadow: 0px 0px 15px 0px rgba(0, 0, 0, 0.1);
        }

        .card-img-top {
            height: 200px;
            object-fit: cover;
        }
    </style>
</head>
<body>
<%
    List<BasketProduct> basketProducts = new ArrayList<>();
    Object object = session.getAttribute("basket");
    if (object != null) {
        Basket basket = (Basket) object;
        basketProducts.addAll(basket.getProducts());
    }
    List<Category> categories;
    List<Product> products;
    UUID categoryId;
    String id = request.getParameter("categoryId");
    if (id != null) {
        categoryId = UUID.fromString(id);
        products = ProductRepo.findByCategory(categoryId);
    } else {
        products = ProductRepo.findAll();
    }
    try {
        categories = CategoryRepo.findAll();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    Object o = session.getAttribute("currentUser");
    WebUser currentUser = null;
    if (o != null) {
        currentUser = (WebUser) o;
    }else{
        for (Cookie cookie : request.getCookies()) {
            if(cookie.getName().equals("userId")){
                UUID userId = UUID.fromString(cookie.getValue());
                Optional<WebUser> byId = UserRepo.findById(userId);
                if(byId.isPresent()){
                    currentUser = byId.get();
                    session.setAttribute("currentUser",currentUser);
                }
            }
        }
    }
%>
<nav class="navbar bg-body-tertiary bg-dark text-white">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">G36Ecommerce</a>
        <form class="d-flex" role="search">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
        <%if (currentUser == null) {%>
        <div class="ms-auto">
            <a href="user/basket.jsp" class="btn btn-info">Basket(<%=basketProducts.size()%>)</a>
            <a href="user/signIn.jsp" class="btn btn-success">Sign in</a>
            <a href="user/signUp.jsp" class="btn btn-success">Sign up</a>
        </div>
        <%} else if (currentUser.getRole().equals(Role.USER)) {%>
        <a href="user/basket.jsp" class="btn btn-info text-white">Basket(<%=basketProducts.size()%>)</a>
        <span class="navbar-text">
            Welcome, <a href="${pageContext.request.contextPath}/user/profile.jsp" id="currentUsername"><%=currentUser.getUsername()%></a>
        </span>
        <%
        } else {%>
            <%response.sendRedirect("/ecommerce/admin.jsp");%>
        <%}%>
    </div>
</nav>


<div class="row">
    <div class="col-3 border-right mt-1">
        <ul class="list-group py-3">
            <a href="?">
                <li class="list-group-item">All</li>
            </a>
            <%for (Category category : categories) {%>
            <a type="hidden" href="index.jsp?categoryId=<%=category.getId()%>">
                <li class="list-group-item mt-1"><%=category.getName()%>
                </li>
            </a>
            <%}%>
        </ul>
    </div>
    <div class="col-9">
        <div class="container">
            <div class="row">
                <%for (Product product : products) {%>
                <div class="col-md-4">
                    <div class="card mb-3">
                        <%
                            String photo = "";
                            if (product.getPhoto() != null) {
                                photo = Base64.getEncoder().encodeToString(product.getPhoto());
                            }
                        %>
                        <%if (product.getPhoto() != null) {%>
                        <img width="500" src="data:image/png;base64,<%=photo%>" class="card-img-top"
                             alt="Product Image">
                        <%}%>
                        <div class="card-body">
                            <h5 class="card-title"><%=product.getName()%>
                            </h5>
                            <p class="card-text">Price: <%=product.getPrice()%>
                            </p>
                            <p class="card-text">Category: <%=product.getCategory()%>
                            </p>
                            <%
                                Optional<BasketProduct> opt = basketProducts.stream().filter(item -> item.getProductId().equals(product.getId())).findFirst();
                                if (opt.isPresent()) {%>
                            <div class="btn btn-info text-white">Added to basket</div>
                            <%} else {%>
                            <form action="${pageContext.request.contextPath}/basket/add" method="post">
                                <input name="productId" type="hidden" value="<%=product.getId()%>">
                                <button class="btn btn-info text-white"> Add to basket</button>
                            </form>
                            <%}%>
                        </div>
                    </div>
                </div>
                <%}%>
            </div>
        </div>
    </div>
</div>


</body>
</html>