<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, com.beans.Product" %>
<html>
<head>
    <title>View Products</title>
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

img {
    border-radius: 6px;
    box-shadow: 0 2px 6px rgba(0,0,0,0.1);
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

    </style>
</head>
<body>
<a class="navBtn" href="adminDashboard.jsp">Home</a>

<h2>Products</h2>

<%
    List<Product> products = (List<Product>) request.getAttribute("products");
    if (products != null && !products.isEmpty()) {
%>
<table>
    <tr>
        <th>ID</th><th>Name</th><th>Description</th><th>Price</th><th>Image</th><th>Actions</th>
    </tr>
    <% for (Product p : products) { %>
    <tr>
        <td><%= p.getId() %></td>
        <td><%= p.getName() %></td>
        <td><%= p.getDescription() %></td>
        <td>₹ <%= p.getPrice() %></td>
        <td><img src="<%= p.getImage() %>" width="80"></td>
        <td>
            <!-- Update -->
            <a class="btn" href="adminservlet?action=editProduct&id=<%= p.getId() %>">Edit</a>

            <!-- Delete -->
            <form action="adminservlet" method="post" style="display:inline;">
                <input type="hidden" name="action" value="deleteProduct">
                <input type="hidden" name="id" value="<%= p.getId() %>">
                <button class="btn">Delete</button>
            </form>
        </td>
    </tr>
    <% } %>
</table>
<% } else { %>
<p>No products found.</p>
<% } %>



</body>
</html>
