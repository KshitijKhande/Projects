<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, com.beans.Product"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your Cart</title>
    <style>
        body {
    font-family: 'Segoe UI', Arial, sans-serif;
    background: linear-gradient(135deg, #f9fafb, #eef2ff);
    margin: 0;
    padding: 20px;
    color: #1e293b;
}

#cartContainer {
    width: 85%;
    margin: 40px auto;
    background: #ffffff;
    padding: 30px;
    border-radius: 12px;
    box-shadow: 0 8px 20px rgba(0,0,0,0.12);
}

h2 {
    text-align: center;
    margin-bottom: 25px;
    font-size: 24px;
    font-weight: 600;
    color: #111827;
}

table {
    width: 100%;
    border-collapse: collapse;
    border-radius: 10px;
    overflow: hidden;
    box-shadow: 0 6px 15px rgba(0,0,0,0.1);
    margin-bottom: 20px;
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

.btn {
    padding: 8px 14px;
    margin: 2px;
    border-radius: 6px;
    border: none;
    font-size: 13px;
    font-weight: 500;
    cursor: pointer;
    transition: background 0.3s ease, transform 0.2s ease;
}

.btn:hover {
    transform: translateY(-2px);
}

.updateBtn {
    background: linear-gradient(135deg, #6366f1, #3b82f6);
    color: #fff;
}
.updateBtn:hover {
    background: linear-gradient(135deg, #4f46e5, #2563eb);
}

.removeBtn {
    background: linear-gradient(135deg, #ef4444, #dc2626);
    color: #fff;
}
.removeBtn:hover {
    background: linear-gradient(135deg, #b91c1c, #991b1b);
}

.checkoutBtn {
    background: linear-gradient(135deg, #10b981, #059669);
    color: #fff;
    font-size: 15px;
    font-weight: 600;
    width: 100%;
    margin-top: 20px;
    padding: 14px;
}
.checkoutBtn:hover {
    background: linear-gradient(135deg, #047857, #065f46);
}

a {
    display: inline-block;
    margin-top: 15px;
    color: #6366f1;
    text-decoration: none;
    font-weight: 500;
    transition: color 0.3s ease;
}
a:hover {
    text-decoration: underline;
    color: #4f46e5;
}

h3 {
    text-align: right;
    margin-top: 15px;
    font-size: 18px;
    font-weight: 600;
    color: #111827;
}

    </style>
</head>
<body>
    <div id="cartContainer">
        <h2>Your Shopping Cart</h2>
        <%
            List<Product> cart = (List<Product>) session.getAttribute("cart");
            if (cart != null && !cart.isEmpty()) {
        %>
        <table>
            <tr>
                <th>Product</th>
                <th>Description</th>
                <th>Price (₹)</th>
                <th>Quantity</th>
                <th>Subtotal (₹)</th>
                <th>Action</th>
            </tr>
            <%
                double total = 0;
                for (Product p : cart) {
                    double subtotal = p.getPrice() * p.getQuantity();
                    total += subtotal;
            %>
            <tr>
                <td><%= p.getName() %></td>
                <td><%= p.getDescription() %></td>
                <td>₹ <%= p.getPrice() %></td>
                <td><%= p.getQuantity() %></td>
                <td>₹ <%= subtotal %></td>
                <td>
                    
                    
                    <form action="CartServlet" method="post" style="display:inline;">
					    <input type="hidden" name="action" value="update">
					    <input type="hidden" name="productId" value="<%= p.getId() %>">
					    <input type="number" name="quantity" value="<%= p.getQuantity() %>" min="1" style="width:50px;">
					    <button type="submit" class="btn updateBtn">Update</button>
					</form>
					
					<form action="CartServlet" method="post" style="display:inline;">
					    <input type="hidden" name="action" value="remove">
					    <input type="hidden" name="productId" value="<%= p.getId() %>">
					    <button type="submit" class="btn removeBtn">Remove</button>
					</form>
					
                    
                </td>
            </tr>
            <% } %>
        </table>
        <h3>Total: ₹ <%= total %></h3>
        <form action="checkout.jsp" method="get">
            <button type="submit" class="btn checkoutBtn">Proceed to Checkout</button>
        </form>
        <a href="productservlet?action=viewAll">Continue Shopping</a>
        <%
            } else {
        %>
        <p>Your cart is empty.</p>
        <a href="ProductsServlet">Continue Shopping</a>
        <%
            }
        %>
    </div>
</body>
</html>
