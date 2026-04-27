<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.beans.Product"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Product</title>
    <style>
        body {
    background: linear-gradient(135deg, #f9fafb, #fff7ed);
    font-family: 'Segoe UI', Arial, sans-serif;
    color: #333;
    margin: 0;
    padding: 0;
}

#updateContainer {
    width: 420px;
    margin: 60px auto;
    background: #ffffff;
    padding: 30px;
    border-radius: 12px;
    box-shadow: 0 8px 20px rgba(0,0,0,0.12);
    transition: transform 0.3s ease;
}
#updateContainer:hover {
    transform: translateY(-3px);
}

h2 {
    text-align: center;
    margin-bottom: 25px;
    font-size: 22px;
    font-weight: 600;
    color: #1e293b;
}

label {
    font-weight: 500;
    display: block;
    margin-top: 12px;
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
    border-color: #f59e0b;
    box-shadow: 0 0 0 3px rgba(245,158,11,0.3);
    outline: none;
}

button {
    margin-top: 22px;
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

.back-link {
    text-align: center;
    margin-top: 18px;
}
.back-link a {
    color: #6366f1;
    text-decoration: none;
    font-weight: 500;
    transition: color 0.3s ease;
}
.back-link a:hover {
    text-decoration: underline;
    color: #4f46e5;
}

    </style>
</head>
<body>
    <div id="updateContainer">
        <h2>Update Product</h2>
        <%
            Product product = (Product) request.getAttribute("product");
            if (product == null) {
        %>
            <p>No product selected for update.</p>
        <%
            } else {
        %>
        <form action="adminservlet" method="post">
            <input type="hidden" name="action" value="updateProduct">
            <input type="hidden" name="id" value="<%= product.getId() %>">

            <label>Name</label>
            <input type="text" name="name" value="<%= product.getName() %>" required>

            <label>Description</label>
            <input type="text" name="description" value="<%= product.getDescription() %>" required>

            <label>Price</label>
            <input type="text" name="price" value="<%= product.getPrice() %>" required>

            <label>Image URL</label>
            <input type="text" name="image" value="<%= product.getImage() %>" required>

            <label>Quantity</label>
            <input type="number" name="quantity" value="<%= product.getQuantity() %>" required>

            <label>Category</label>
            <select name="category" required>
                <option value="electronics" <%= "electronics".equals(product.getCategory()) ? "selected" : "" %>>Electronics</option>
                <option value="fashion" <%= "fashion".equals(product.getCategory()) ? "selected" : "" %>>Fashion</option>
                <option value="books" <%= "books".equals(product.getCategory()) ? "selected" : "" %>>Books</option>
                <option value="groceries" <%= "groceries".equals(product.getCategory()) ? "selected" : "" %>>Groceries</option>
                <option value="general" <%= "general".equals(product.getCategory()) ? "selected" : "" %>>General</option>
            </select>

            <button type="submit">Update Product</button>
        </form>
        <%
            }
        %>
        <div class="back-link">
            <a href="adminDashboard.jsp">Back to Dashboard</a>
        </div>
    </div>
</body>
</html>
