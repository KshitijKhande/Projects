<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ page import="com.beans.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>Personal Details</title>
    <style>
        body {
    font-family: 'Segoe UI', Arial, sans-serif;
    background: linear-gradient(135deg, #f9fafb, #eef2ff);
    margin: 0;
    padding: 40px;
    color: #1e293b;
}

#container {
    width: 420px;
    margin: 0px auto;
    background: #ffffff;
    padding: 30px;
    border-radius: 12px;
    box-shadow: 0 8px 20px rgba(0,0,0,0.12);
    transition: transform 0.3s ease;
}
#container:hover {
    transform: translateY(-3px);
}

h2 {
    text-align: center;
    margin-bottom: 25px;
    font-size: 22px;
    font-weight: 600;
    color: #111827;
}

p {
    text-align: center;
    font-size: 14px;
    margin-bottom: 15px;
}

input {
    width: 100%;
    padding: 12px;
    margin-top: 8px;
    border-radius: 8px;
    border: 1px solid #d1d5db;
    font-size: 14px;
    transition: border-color 0.3s ease, box-shadow 0.3s ease;
}
input:focus {
    border-color: #6366f1;
    box-shadow: 0 0 0 3px rgba(99,102,241,0.3);
    outline: none;
}

button {
    margin-top: 20px;
    width: 25%;
    padding: 14px;
    border-radius: 8px;
    border: none;
    background: linear-gradient(135deg, #f59e0b, #d97706);
    color: #ffffff;
    font-weight: 600;
    font-size: 15px;
    cursor: pointer;
    transition: background 0.3s ease, transform 0.2s ease;
}
.updatebutton {
    margin-top: 20px;
    width: 100%;
    padding: 14px;
    border-radius: 8px;
    border: none;
    background: linear-gradient(135deg, #f59e0b, #d97706);
    color: #ffffff;
    font-weight: 600;
    font-size: 15px;
    cursor: pointer;
    transition: background 0.3s ease, transform 0.2s ease;
}
button:hover {
    background: linear-gradient(135deg, #d97706, #b45309);
    transform: translateY(-2px);
}
button:active {
    transform: scale(0.98);
}

form[action*="ProductsServlet"] button {
    background: linear-gradient(135deg, #6366f1, #3b82f6);
}
form[action*="ProductsServlet"] button:hover {
    background: linear-gradient(135deg, #4f46e5, #2563eb);
}

    </style>
</head>
<body>


<div id="container">
<%
    String updateStatus = request.getParameter("update");
    if ("success".equals(updateStatus)) {
%>
    <p style="color:green;">Profile updated successfully!</p>
<%
    } else if ("fail".equals(updateStatus)) {
%>
    <p style="color:red;">Update failed. Please try again.</p>
<%
    } else if ("error".equals(updateStatus)) {
%>
    <p style="color:red;">Server error occurred while updating.</p>
<%
    }
%>

    <h2>Personal Information</h2>
    <%
        User user = (User) session.getAttribute("user");
        if (user != null) {
    %>
    <form action="userservlet" method="post">
        <input type="hidden" name="action" value="updatePersonalInfo">
        <input type="hidden" name="id" value="<%= user.getId() %>">

        Name: <input type="text" name="username" value="<%= user.getUsername() %>" required>
        Email: <input type="email" name="email" value="<%= user.getEmail() %>" required>
        Password: <input type="password" name="password" value="<%= user.getPassword() %>" required>
        Mobile No: <input type="text" name="mobileno" value="<%= user.getMobileno() %>">
        Address: <input type="text" name="address" value="<%= user.getAddress() %>">

        <button class="updatebutton" type="submit">Update Info</button>
    </form>
    <% } else { %>
        <p>No user logged in.</p>
    <% } %>
</div>

<form action="ProductsServlet?action=viewAll" method="get" style="text-align:center; margin-top:20px;">
    <button type="submit" 
            style="padding:10px 20px; background-color:#333; color:white; border:none; border-radius:5px; cursor:pointer;">
        Home
    </button>
</form>
</body>
</html>
