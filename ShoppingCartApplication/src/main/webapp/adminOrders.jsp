<%@ page import="java.util.*, com.beans.Order" %>

<%
List<Order> orders = (List<Order>) request.getAttribute("orders");

// Group orders by order_id
Map<Integer, List<Order>> groupedOrders = new LinkedHashMap<>();

if (orders != null) {
    for (Order o : orders) {
        groupedOrders.computeIfAbsent(o.getOrderId(), k -> new ArrayList<>()).add(o);
    }
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin - Orders</title>

<style>
body {
    font-family: 'Segoe UI', Arial;
    background: #f3f4f6;
}

.order-box {
    width: 90%;
    margin: 20px auto;
    background: white;
    border-radius: 10px;
    padding: 15px;
    box-shadow: 0 4px 10px rgba(0,0,0,0.1);
}

.order-header {
    display: flex;
    justify-content: space-between;
    border-bottom: 1px solid #ddd;
    padding-bottom: 10px;
    margin-bottom: 10px;
}

.product-row {
    display: flex;
    justify-content: space-between;
    padding: 8px 0;
}

.navBtn {
    padding: 6px 12px;
    background: #6366f1;
    color: white;
    border: none;
    border-radius: 5px;
}

.statusDropdown {
    padding: 6px;
    border-radius: 5px;
}
.navBtn {
    display: inline-block;
    margin: 15px;
    padding: 10px 18px;
    border-radius: 8px;
    background: linear-gradient(135deg, #6366f1, #3b82f6);
    color: #fff;
    font-weight: 500;
    text-decoration: none;
    transition: background 0.3s ease, transform 0.2s ease;
}
.navBtn:hover {
    background: linear-gradient(135deg, #4f46e5, #2563eb);
    transform: translateY(-2px);
}
</style>

</head>
<body>
<a class="navBtn" href="adminDashboard.jsp">Home</a>
<h2 style="text-align:center;">All Orders</h2>

<%
for (Map.Entry<Integer, List<Order>> entry : groupedOrders.entrySet()) {

    List<Order> orderItems = entry.getValue();
    Order first = orderItems.get(0);
%>

<div class="order-box">

    <!-- ORDER HEADER -->
    <div class="order-header">
        <div>
            <b>Order ID:</b> <%= first.getOrderId() %> |
            <b>User:</b> <%= first.getUserId() %> |
            <b>Payment:</b> <%= first.getPaymentMethod() %>
        </div>

        <!-- ✅ ONLY ONE STATUS FORM -->
        <form action="adminservlet" method="post">
            <input type="hidden" name="action" value="updateOrderStatus">
            <input type="hidden" name="orderId" value="<%= first.getOrderId() %>">

            <select name="status" class="statusDropdown">
                <option value="Placed" <%= "Placed".equals(first.getStatus()) ? "selected" : "" %>>Placed</option>
                <option value="Confirmed" <%= "Confirmed".equals(first.getStatus()) ? "selected" : "" %>>Confirmed</option>
                <option value="Shipped" <%= "Shipped".equals(first.getStatus()) ? "selected" : "" %>>Shipped</option>
                <option value="Delivered" <%= "Delivered".equals(first.getStatus()) ? "selected" : "" %>>Delivered</option>
                <option value="Cancelled" <%= "Cancelled".equals(first.getStatus()) ? "selected" : "" %>>Cancelled</option>
            </select>

            <button type="submit" class="navBtn">Update</button>
        </form>
    </div>

    <%
double orderTotal = 0;
for (Order o : orderItems) {
    orderTotal += o.getPrice() * o.getQuantity();
%>

<div class="product-row">
    <span>Product: <%= o.getProductName() %></span>
    <span>Qty: <%= o.getQuantity() %></span>
    <span>&#8377; <%= o.getPrice() %></span>
    <span>&#8377; <%= o.getPrice() * o.getQuantity() %></span>
</div>

<% } %>

<!-- ✅ ORDER TOTAL -->
<div style="text-align:right; font-weight:bold; margin-top:10px;">
    Total: &#8377; <%= orderTotal %>
</div>


</div>

<%
}
%>

</body>
</html>
