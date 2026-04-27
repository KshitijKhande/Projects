<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.beans.User" %>
<%
    User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
    <style>
        body {
    font-family: 'Segoe UI', Arial, sans-serif;
    background: linear-gradient(135deg, #f9fafb, #eef2ff);
    margin: 0;
    padding: 40px;
    color: #1e293b;
}

#checkoutContainer {
    width: 420px;
    margin: 50px auto;
    background: #ffffff;
    padding: 30px;
    border-radius: 12px;
    box-shadow: 0 8px 20px rgba(0,0,0,0.12);
    transition: transform 0.3s ease;
}
#checkoutContainer:hover {
    transform: translateY(-3px);
}

h2 {
    text-align: center;
    margin-bottom: 25px;
    font-size: 24px;
    font-weight: 600;
    color: #111827;
}

h3 {
    margin-top: 20px;
    font-size: 18px;
    font-weight: 600;
    color: #374151;
}

label {
    display: block;
    margin-top: 12px;
    font-weight: 500;
    color: #374151;
}

input, select {
    width: 100%;
    padding: 12px;
    margin-top: 6px;
    border-radius: 8px;
    border: 1px solid #d1d5db;
    font-size: 14px;
    transition: border-color 0.3s ease, box-shadow 0.3s ease;
}

input:focus, select:focus {
    border-color: #10b981;
    box-shadow: 0 0 0 3px rgba(16,185,129,0.3);
    outline: none;
}

button {
    margin-top: 22px;
    width: 100%;
    padding: 14px;
    border-radius: 8px;
    border: none;
    background: linear-gradient(135deg, #10b981, #059669);
    color: #ffffff;
    font-weight: 600;
    font-size: 15px;
    cursor: pointer;
    transition: background 0.3s ease, transform 0.2s ease;
}
button:hover {
    background: linear-gradient(135deg, #047857, #065f46);
    transform: translateY(-2px);
}
button:active {
    transform: scale(0.98);
}

    </style>
</head>
<body>
    <div id="checkoutContainer">
        <h2>Checkout</h2>
        <form action="OrderServlet" method="post">
            <h3>Shipping Details</h3>
            Full Name <input type="text" name="fullname" value="<%= user.getUsername() %>" readonly>
            Email <input type="text" name="email" value="<%= user.getEmail() %>" readonly>
            Mobile No <input type="text" name="mobileno" value="<%= user.getMobileno() %>">
            Address <input type="text" name="address" value="<%= user.getAddress() %>">
            <h3>Payment Details</h3>
            Payment Method
            <select name="paymentMethod" required>
                <option value="">--Select--</option>
                <option value="cod">Cash on Delivery</option>
                <option value="card">Credit/Debit Card</option>
                <option value="upi">UPI</option>
            </select>

            <button type="submit">Place Order</button>
        </form>
    </div>
</body>
</html>
