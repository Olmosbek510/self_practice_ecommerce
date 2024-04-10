<%@ page import="uz.pdp.ecommerce.repo.BasketProductRepo" %>
<%@ page import="uz.pdp.ecommerce.entity.BasketProduct" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.ecommerce.repo.ProductRepo" %>
<%@ page import="uz.pdp.ecommerce.entity.Product" %>
<%@ page import="java.util.UUID" %>
<%@ page import="uz.pdp.ecommerce.temp.TemporaryVariables" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="uz.pdp.ecommerce.entity.Basket" %><%--
  Created by IntelliJ IDEA.
  User: orazboyevolmosbek
  Date: 31/03/24
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Basket</title>
</head>
<body>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Basket</title>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
</head>
<body>

<%
    List<BasketProduct> basketProducts = new ArrayList<>();
    Object object = session.getAttribute("basket");
    if (object != null) {
        Basket basket = (Basket) object;
        basketProducts.addAll(basket.getProducts());
    }
    int totalPrice = basketProducts.stream()
            .mapToInt(basketProduct -> basketProduct.getAmount() * ProductRepo.findById(basketProduct.getProductId()).getPrice()).sum();
    int tax = totalPrice * 10 / 100;
%>
<div class="container mt-5">
    <h2>Shopping Basket</h2>
    <div class="row">
        <div class="col-md-8">
            <%for (BasketProduct basketProduct : basketProducts) {%>
            <div class="card mb-3">
                <div class="card-body">
                    <div class="d-flex justify-content-between align-items-center">
                        <div>
                            <a href="${pageContext.request.contextPath}/product/remove?id=<%=basketProduct.getProductId()%>"
                               class="btn btn-danger btn-sm">Remove</a>
                            <span class="ml-2"><%=ProductRepo.getProduct(basketProduct.getProductId()).getName()%></span>
                        </div>
                        <div>
                            <span class="mr-3"><%=ProductRepo.getProduct(basketProduct.getProductId()).getPrice()%></span>
                            <a href="${pageContext.request.contextPath}/product/decrease/amount?id=<%=basketProduct.getProductId()%>"
                               type="button" class="btn btn-outline-secondary btn-sm">-</a>
                            <span class="mx-2"><%=basketProduct.getAmount()%></span>
                            <a href="${pageContext.request.contextPath}/product/increase/amount?id=<%=basketProduct.getProductId()%>"
                               type="button" class="btn btn-outline-secondary btn-sm">+</a>
                        </div>
                    </div>
                </div>
            </div>
            <%}%>
            <!-- Add more products here following the same structure -->
        </div>

        <div class="col-md-4">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Total</h5>
                    <p class="card-text">Subtotal: <%=totalPrice%>
                    </p>
                    <p class="card-text">Tax: <%=tax%>
                    </p>
                    <p class="card-text font-weight-bold">Total: <%=totalPrice + tax%>
                    </p>
                    <%if (!basketProducts.isEmpty()) {%>
                    <a href="../user/checkout.jsp" type="button" class="btn btn-primary btn-block">Checkout</a>
                    <%}%>
                </div>
            </div>
            <div>
                <a href="../index.jsp" class="btn btn-info text-white">Back to home</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>


</body>
</html>
