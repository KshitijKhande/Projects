<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, com.beans.User" %>
<html>
<head>
    <title>View Users</title>
    <style>
        body {
            font-family: 'Segoe UI', Arial, sans-serif;
            background: linear-gradient(135deg, #f9fafb, #eef2ff);
            margin: 0;
            padding: 20px;
            color: #1e293b;
        }

        h2 {
            text-align: center;
            margin: 25px 0;
            font-size: 24px;
            font-weight: 600;
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

        table {
            width: 95%;
            margin: 0 auto 40px auto;
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

        .btn.edit {
            background: linear-gradient(135deg, #6366f1, #3b82f6);
            color: #fff;
        }
        .btn.edit:hover {
            background: linear-gradient(135deg, #4f46e5, #2563eb);
        }

        .btn.delete {
            background: linear-gradient(135deg, #ef4444, #dc2626);
            color: #fff;
        }
        .btn.delete:hover {
            background: linear-gradient(135deg, #b91c1c, #991b1b);
        }

        .btn.block {
            background: linear-gradient(135deg, #f59e0b, #d97706);
            color: #fff;
        }
        .btn.block:hover {
            background: linear-gradient(135deg, #b45309, #92400e);
        }

        .btn.unblock {
            background: linear-gradient(135deg, #10b981, #059669);
            color: #fff;
        }
        .btn.unblock:hover {
            background: linear-gradient(135deg, #047857, #065f46);
        }
    </style>
</head>
<body>
<a class="navBtn" href="adminDashboard.jsp">Home</a>

<h2>Registered Users</h2>

<%
    List<User> users = (List<User>) request.getAttribute("users");
    if (users != null && !users.isEmpty()) {
%>
<table>
    <tr>
        <th>ID</th><th>Username</th><th>Email</th><th>Mobile</th><th>Status</th><th>Actions</th>
    </tr>
    <% for (User u : users) { %>
    <tr>
        <td><%= u.getId() %></td>
        <td><%= u.getUsername() %></td>
        <td><%= u.getEmail() %></td>
        <td><%= u.getMobileno() %></td>
        <td><%= u.getStatus() %></td>
        <td>
            <!-- Edit -->
            <form action="adminservlet" method="post" style="display:inline;">
                <input type="hidden" name="action" value="updateUser">
                <input type="hidden" name="username" value="<%= u.getUsername() %>">
                <button class="btn edit">Edit</button>
            </form>
            <!-- Delete -->
            <form action="adminservlet" method="post" style="display:inline;">
                <input type="hidden" name="action" value="deleteUser">
                <input type="hidden" name="username" value="<%= u.getUsername() %>">
                <button class="btn delete">Delete</button>
            </form>
            <!-- Block/Unblock -->
            <% if ("active".equalsIgnoreCase(u.getStatus())) { %>
                <form action="adminservlet" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="blockUser">
                    <input type="hidden" name="id" value="<%= u.getId() %>">
                    <button class="btn block">Block</button>
                </form>
            <% } else { %>
                <form action="adminservlet" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="unblockUser">
                    <input type="hidden" name="id" value="<%= u.getId() %>">
                    <button class="btn unblock">Unblock</button>
                </form>
            <% } %>
        </td>
    </tr>
    <% } %>
</table>
<% } else { %>
<p style="text-align:center;">No users found.</p>
<% } %>

</body>
</html>
