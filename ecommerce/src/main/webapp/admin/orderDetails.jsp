<%@ page import="uz.pdp.ecommerce.repo.OrderRepo" %>
<%@ page import="uz.pdp.ecommerce.entity.BasketProduct" %>
<%@ page import="uz.pdp.ecommerce.records.OrderDetails" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.function.Function" %>
<%@ page import="java.util.stream.Stream" %>
<%@ page import="java.time.format.DateTimeFormatter" %><%--
  Created by IntelliJ IDEA.
  User: orazboyevolmosbek
  Date: 01/04/24
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        int orderId = Integer.parseInt(request.getParameter("id"));
        List<OrderDetails> orderDetails = OrderRepo.getOrderDetails(orderId);
        double totalPrice = orderDetails.stream().mapToDouble(OrderDetails::totalPrice).sum();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm");
    %>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
    <title>Order#<%=orderId%> details</title>
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
<div class="container mt-5">
    <h2 class="mb-4">
        Order#<%=orderId%> details
    </h2>
    <small>Time: <%=dateTimeFormatter.format(orderDetails.get(0).dateTime().toLocalDateTime())%>
    </small>
    <div><small>Status: <%=orderDetails.get(0).status()%>
    </small></div>
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
            for (OrderDetails orderDetail : orderDetails) {%>
        <tr>
            <td><%=orderDetail.productName()%>
            </td>
            <td><%=orderDetail.price()%> * <%=orderDetail.count()%>
            </td>
            <td><%=orderDetail.totalPrice()%>
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
                    <td>$<%=totalPrice%>
                    </td>
                    <td><strong>Tax:</strong></td>
                    <td>$<%=totalPrice * 10 / 100%>
                    </td>
                    <td><strong>Total:</strong></td>
                    <td>$<%=totalPrice + totalPrice * 10 / 100%>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <a href="orders.jsp" class="btn btn-primary btn-back">Back to orders</a>
</div>

</body>
</html>
