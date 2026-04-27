<%@ page import="java.util.List, com.beans.Product" %>
<%
    List<Product> purchasedItems = (List<Product>) request.getAttribute("purchasedItems");
    String message = (String) request.getAttribute("message");
%>
<html>
<head>
    <title>Order Confirmation</title>
    <style>
        body {
    font-family: 'Segoe UI', Arial, sans-serif;
    background: linear-gradient(135deg, #f9fafb, #eef2ff);
    margin: 0;
    padding: 40px;
    color: #1e293b;
}

h2 {
    text-align: center;
    font-size: 26px;
    font-weight: 700;
    color: #10b981; /* green success color */
    margin-bottom: 20px;
}

h3 {
    margin-top: 20px;
    font-size: 18px;
    font-weight: 600;
    color: #374151;
}

table {
    width: 70%;
    margin: 20px auto;
    border-collapse: collapse;
    border-radius: 10px;
    overflow: hidden;
    box-shadow: 0 6px 15px rgba(0,0,0,0.1);
    background: #fff;
}

th, td {
    padding: 12px 15px;
    text-align: center;
    font-size: 14px;
    border-bottom: 1px solid #e5e7eb;
}

th {
    background: #f3f4f6;
    font-weight: 600;
    color: #374151;
}

tr:nth-child(even) {
    background: #f9fafb;
}

tr:hover {
    background: #eef2ff;
    transition: background 0.3s ease;
}

a {
    display: inline-block;
    margin: 25px auto;
    text-align: center;
    color: #6366f1;
    text-decoration: none;
    font-weight: 500;
    transition: color 0.3s ease;
}
a:hover {
    text-decoration: underline;
    color: #4f46e5;
}

h3:last-of-type {
    text-align: right;
    margin-right: 15%;
    font-size: 18px;
    font-weight: 700;
    color: #111827;
}

    </style>
</head>
<body>
    <h2><%= message %></h2>

    <% if (purchasedItems != null && !purchasedItems.isEmpty()) { %>
        <h3>Purchased Items:</h3>
        <table>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total</th>
            </tr>
            <% double grandTotal = 0;
               for (Product p : purchasedItems) {
                   double total = p.getPrice() * p.getQuantity();
                   grandTotal += total;
            %>
            <tr>
                <td><%= p.getName() %></td>
                <td><%= p.getDescription() %></td>
                <td><%= p.getPrice() %></td>
                <td><%= p.getQuantity() %></td>
                <td><%= total %></td>
            </tr>
            <% } %>
        </table>
        <h3>Grand Total:  <%= grandTotal %></h3>
    <% } %>

    <a href="productservlet?action=viewAll">Continue Shopping</a>
</body>
</html>