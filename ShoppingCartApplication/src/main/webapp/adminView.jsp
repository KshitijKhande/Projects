<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, com.beans.User, com.beans.Product"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin View</title>
    <style>
        body { background-color: #f0f0f0; font-family: Arial; }
        #container {
            width: 90%; margin: auto; margin-top: 30px;
            background-color: #fff; padding: 20px;
            border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.2);
        }
        h2 { text-align: center; margin-top: 20px; }
        table {
            width: 100%; border-collapse: collapse; margin-top: 20px;
        }
        th, td {
            border: 1px solid #ccc; padding: 10px; text-align: center;
        }
        th { background-color: #eee; }
        .section { margin-bottom: 40px; }
        .btn {
            padding: 5px 10px; border: none; border-radius: 5px;
            cursor: pointer; background-color: orange; color: white;
        }
    </style>
</head>
<body>
    <div id="container">
        <h2>Admin Panel</h2>

        
        <div class="section">
            <h3>Registered Users</h3>
            <%
                List<User> users = (List<User>) request.getAttribute("users");
                if (users != null && !users.isEmpty()) {
            %>
            <table>
                <tr>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Mobile No</th>
                    <th>Security Question</th>
                    <th>Answer</th>
                </tr>
                <%
                    for (User u : users) {
                %>
                <tr>
                    <td><%= u.getUsername() %></td>
                    <td><%= u.getEmail() %></td>
                    <td><%= u.getMobileno() %></td>
                    <td><%= u.getSecurityques() %></td>
                    <td><%= u.getSecquesans() %></td>
                </tr>
                <% } %>
            </table>
            <%
                } else {
            %>
            <p>No users found.</p>
            <%
                }
            %>
        </div>

       
        <div class="section">
            <h3>Products</h3>
            <%
                List<Product> products = (List<Product>) request.getAttribute("products");
                if (products != null && !products.isEmpty()) {
            %>
            <table>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Image</th>
                    <th>Actions</th>
                </tr>
                <%
                    for (Product p : products) {
                %>
                <tr>
                    <td><%= p.getName() %></td>
                    <td><%= p.getDescription() %></td>
                    <td>₹ <%= p.getPrice() %></td>
                    <td><img src="<%= p.getImage() %>" alt="<%= p.getName() %>" width="80"></td>
                    <td>
                        <form action="productservlet" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="id" value="<%= p.getId() %>">
                            <button class="btn">Edit</button>
                        </form>
                        <form action="productservlet" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="<%= p.getId() %>">
                            <button class="btn">Delete</button>
                        </form>
                    </td>
                </tr>
                <% } %>
            </table>
            <%
                } else {
            %>
            <p>No products found.</p>
            <%
                }
            %>
        </div>

        <div style="text-align:center; margin-top:20px;">
            <a href="adminDashboard.jsp" style="text-decoration:none; color:blue;">Back to Dashboard</a>
        </div>
    </div>
</body>
</html>
