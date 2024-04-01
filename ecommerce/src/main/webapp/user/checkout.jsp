<%@ page import="uz.pdp.ecommerce.entity.BasketProduct" %>
<%@ page import="uz.pdp.ecommerce.repo.BasketProductRepo" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.ecommerce.entity.Product" %>
<%@ page import="uz.pdp.ecommerce.repo.ProductRepo" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="uz.pdp.ecommerce.repo.BasketRepo" %>
<%@ page import="uz.pdp.ecommerce.services.UserService" %>
<%@ page import="uz.pdp.ecommerce.temp.TemporaryVariables" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: orazboyevolmosbek
  Date: 31/03/24
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Checkout</title>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
    <style>
        .container {
            max-width: 800px;
            margin: 0 auto;
        }

        .table th, .table td {
            vertical-align: middle;
        }

        .total {
            font-size: 1.2em;
        }

        .btn-back {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<%
    List<BasketProduct> basketProducts = new ArrayList<>();
    if(TemporaryVariables.CURRENT_BASKET!=null){
        basketProducts = BasketProductRepo.findByBasketId(TemporaryVariables.CURRENT_BASKET.getId());
    }
    int totalPrice = BasketRepo.getTotalPrice();
    UserService.placeOrder();
%>
<div class="container mt-5">
    <h2 class="mb-4">Checkout</h2>
    <table class="table">
        <thead>
        <tr>
            <th>Product</th>
            <th>Count</th>
            <th>Price</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (BasketProduct basketProduct : basketProducts) {
                Product product;
                try {
                    product = ProductRepo.getProduct(basketProduct.getProductId());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        %>
        <tr>
            <td><%=product.getName()%>
            </td>
            <td><%=product.getPrice()%> * <%=basketProduct.getAmount()%>
            </td>
            <td><%=product.getPrice() * basketProduct.getAmount()%>
            </td>
            <%}%>
        </tr>
        </tbody>
    </table>
    <div class="row total">
        <div class="col-md-12">
            <table class="table">
                <tbody>
                <tr>
                    <td><strong>Subtotal:</strong></td>
                    <td>$<%=totalPrice%></td>
                    <td><strong>Tax:</strong></td>
                    <td>$<%=totalPrice*10/100%></td>
                    <td><strong>Total:</strong></td>
                    <td>$<%=totalPrice+totalPrice*10/100%></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <a href="../index.jsp" class="btn btn-primary btn-back">Back to Home</a>
</div>

</body>
</html>
