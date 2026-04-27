<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Product</title>
    <style>
        body {
    background: linear-gradient(135deg, #f9fafb, #eef2ff);
    font-family: 'Segoe UI', Arial, sans-serif;
    margin: 0;
    padding: 0;
}

#addContainer {
    width: 420px;
    margin: 60px auto;
    background: #ffffff;
    padding: 25px 30px;
    border-radius: 12px;
    box-shadow: 0 8px 20px rgba(0,0,0,0.12);
    transition: transform 0.3s ease;
}
#addContainer:hover {
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
    border-color: #6366f1;
    box-shadow: 0 0 0 3px rgba(99,102,241,0.3);
    outline: none;
}

button {
    margin-top: 22px;
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
button:hover {
    background: linear-gradient(135deg, #4f46e5, #2563eb);
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
    <div id="addContainer">
        <h2>Add New Product</h2>
        <form action="productservlet" method="get">
            <input type="hidden" name="action" value="add">

            <label>Name</label>
            <input type="text" name="name" required>

            <label>Description</label>
            <input type="text" name="description" required>

            <label>Price</label>
            <input type="text" name="price" required>

            <label>Image URL</label>
            <input type="text" name="image" required>

            <label>Quantity</label>
            <input type="number" name="quantity" min="1" value="1" required>

            <label>Category</label>
            <select name="category" required>
                <option value="electronics">Electronics</option>
                <option value="fashion">Fashion</option>
                <option value="books">Books</option>
                <option value="groceries">Groceries</option>
                <option value="general">General</option>
            </select>

            <button type="submit">Add Product</button>
        </form>
        <div class="back-link">
            <a href="adminDashboard.jsp">Back to Dashboard</a>
        </div>
    </div>
</body>
</html>
