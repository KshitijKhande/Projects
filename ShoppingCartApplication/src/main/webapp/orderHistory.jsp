<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, com.beans.Order" %>

<!DOCTYPE html>
<html>
<head>
<title>Order History</title>

<style>
body {
    font-family: 'Segoe UI', Arial;
    background: #f3f4f6;
    margin: 0;
    padding: 20px;
}

h2 {
    text-align: center;
    margin-bottom: 20px;
}

.order-box {
    width: 90%;
    margin: 20px auto;
    background: #fff;
    border-radius: 10px;
    padding: 15px;
    box-shadow: 0 4px 12px rgba(0,0,0,0.1);
}

.order-header {
    border-bottom: 1px solid #ddd;
    margin-bottom: 10px;
    padding-bottom: 10px;
    display: flex;
    justify-content: space-between;
    flex-wrap: wrap;
}

.order-header span {
    font-weight: 600;
    margin-right: 15px;
}

.product-row {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 10px 0;
    border-bottom: 1px solid #eee;
}

.product-row img {
    height: 50px;
    border-radius: 5px;
}

.product-row span {
    width: 120px;
    text-align: center;
}

.total-box {
    text-align: right;
    font-size: 16px;
    font-weight: bold;
    margin-top: 10px;
}

.status {
    padding: 5px 12px;
    border-radius: 15px;
    color: white;
}

.status.Placed { background: gold; }
.status.Pending { background: orange; }
.status.Cancelled { background: red; }
.status.Delivered { background: green; }
.status.Confirmed { background: lightgreen; }
.status.Shipped { background: orange; }

.home-btn {
    display: block;
    margin: 20px auto;
    padding: 10px 20px;
    background: black;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}
</style>

</head>
<body>

<form action="ProductsServlet" method="get">
    <button class="home-btn">Home</button>
</form>

<h2>Your Orders</h2>

<%
Map<Integer, List<Order>> groupedOrders =
    (Map<Integer, List<Order>>) request.getAttribute("groupedOrders");

if (groupedOrders != null && !groupedOrders.isEmpty()) {

for (Map.Entry<Integer, List<Order>> entry : groupedOrders.entrySet()) {

    List<Order> orderItems = entry.getValue();
    Order first = orderItems.get(0);

    // ✅ Calculate total for this order
    double totalAmount = 0;
    for (Order o : orderItems) {
        totalAmount += o.getPrice() * o.getQuantity();
    }
%>

<div class="order-box">

    <!-- Order Header -->
    <div class="order-header">
        <span>Order ID: <%= first.getOrderId() %></span>
        <span>Date: <%= first.getOrderDate() %></span>
        <span>Payment: <%= first.getPaymentMethod() %></span>
        <span class="status <%= first.getStatus() %>">
            <%= first.getStatus() %>
        </span>
    </div>

    <!-- Product Rows -->
    <% for (Order o : orderItems) { %>
    <div class="product-row">

        <img src="<%= o.getProductImage() %>" alt="product">

        <span><%= o.getProductName() %></span>

        <span>₹ <%= o.getPrice() %></span>

        <span>Qty: <%= o.getQuantity() %></span>

        <span>
            ₹ <%= o.getPrice() * o.getQuantity() %>
        </span>

    </div>
    <% } %>

    <!-- Total -->
    <div class="total-box">
        Total: ₹ <%= totalAmount %>
    </div>

</div>

<%
}
} else {
%>

<p style="text-align:center;">No orders found</p>

<% } %>

</body>
</html>
