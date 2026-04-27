<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.beans.User, com.services.AdminServices, com.servicesImpl.AdminServicesImpl, com.DAOImpl.AdminDAOImpl, com.aspect.DBConnection" %>
<%
    // Get username from request
    String username = request.getParameter("username");

    // Load user from DB
    AdminServices adminServices = new AdminServicesImpl(new AdminDAOImpl(DBConnection.getSQLConnection()));
    User user = adminServices.findUserByUsername(username);
%>

<html>
<head>
    <title>Edit User</title>
    <style>
        body {
    font-family: 'Segoe UI', Arial, sans-serif;
    background: linear-gradient(135deg, #f9fafb, #eef2ff);
    margin: 0;
    padding: 40px;
}

h2 {
    font-size: 22px;
    font-weight: 600;
    color: #1e293b;
    margin-bottom: 25px;
    text-align: center;
}

form {
    max-width: 420px;
    margin: auto;
    background: #ffffff;
    padding: 25px 30px;
    border-radius: 12px;
    box-shadow: 0 8px 20px rgba(0,0,0,0.12);
}

label {
    display: block;
    margin-top: 12px;
    font-weight: 500;
    color: #374151;
}

input {
    width: 100%;
    padding: 12px;
    margin-top: 6px;
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

.btn {
    margin-top: 20px;
    width: 100%;
    padding: 14px;
    border-radius: 8px;
    border: none;
    background: linear-gradient(135deg, #6366f1, #3b82f6);
    color: #ffffff;
    font-weight: 600;
    font-size: 15px;
    cursor: pointer;
    transition: background 0.3s ease, transform 0.2s ease;
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
.btn:hover {
    background: linear-gradient(135deg, #4f46e5, #2563eb);
    transform: translateY(-2px);
}
.btn:active {
    transform: scale(0.98);
}

    </style>
</head>
<body>
	<a href="adminservlet?action=viewUsers">
    <button type="button">Home</button>
</a>

    <h2>Edit User: <%= user.getUsername() %></h2>

    <form action="adminservlet" method="post">
        <input type="hidden" name="action" value="updateUser">
        <input type="hidden" name="id" value="<%= user.getId() %>">
        <input type="hidden" name="username" value="<%= user.getUsername() %>">

        <label>Password:</label>
        <input type="password" name="password" value="<%= user.getPassword() %>">

        <label>Email:</label>
        <input type="email" name="email" value="<%= user.getEmail() %>">

        <label>Mobile:</label>
        <input type="text" name="mobileno" value="<%= user.getMobileno() %>">
        
        <label>Address:</label>
        <input type="text" name="address" value="<%= user.getAddress() %>">

        <label>Security Question:</label>
        <input type="text" name="securityques" value="<%= user.getSecurityques() %>">

        <label>Answer:</label>
        <input type="text" name="secquesans" value="<%= user.getSecquesans() %>">

        <button class="btn">Save Changes</button>
    </form>
</body>
</html>
>