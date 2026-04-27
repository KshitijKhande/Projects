<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, com.beans.Product"%>
<%@ page session="true" %>
<%@ page import="java.util.List, com.beans.Product, com.beans.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Products</title>
    <style>
    		/* Reset */
* { margin: 0; padding: 0; box-sizing: border-box; }

body {
    background-color: #f9fafb;
    font-family: 'Segoe UI', Arial, sans-serif;
    color: #333;
    line-height: 1.6;
}

/* Navbar */
.navbar {
    background-color: #1f2937;
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 10px 20px;
    color: #fff;
}

/* Brand */
.navbar-title {
    font-size: 20px;
    font-weight: bold;
    color: #f9fafb;
    text-decoration: none;
}

/* Search form */
.search-form {
    flex: 1;
    display: flex;
    justify-content: center;
    gap: 8px;
}
.search-input {
    padding: 8px;
    width: 250px;
    border-radius: 6px;
    border: 1px solid #ccc;
}
.search-btn {
    padding: 8px 14px;
    border: none;
    border-radius: 6px;
    background: #6366f1;
    color: white;
    cursor: pointer;
}
.search-btn:hover { background: #4f46e5; }

/* Cart */
.cart-container {
    position: relative;
    margin-left: 20px;
    height: 45px;
    width: 45px;
    border-radius: 50%;          /* makes it round */
    background-color: #ffffff;   /* white background for contrast */
    display: flex;
    align-items: center;
    justify-content: center;
    box-shadow: 0 2px 6px rgba(0,0,0,0.2); /* subtle shadow */
    cursor: pointer;
}

.cart-icon {
    width: 28px;   /* slightly larger */
    height: 28px;
}

.cart-count {
    position: absolute;
    top: -6px;
    right: -6px;
    background-color: #ef4444;
    color: #fff;
    font-size: 12px;
    font-weight: bold;
    padding: 2px 6px;
    border-radius: 50%;
    box-shadow: 0 2px 4px rgba(0,0,0,0.2);
}

/* Dropdown */
.dropdown { position: relative; margin-left: 20px; }
.dropbtn {
    background: none;
    border: none;
    color: #fff;
    font-weight: 500;
    cursor: pointer;
    padding: 10px 15px;
}
.dropdown-content {
    display: none;
    position: absolute;
    right: 0;
    background-color: #fff;
    min-width: 160px;
    box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    border-radius: 4px;
}
.dropdown-content a, .logout-option {
    color: #333;
    padding: 10px 15px;
    display: block;
    text-align: left;
    background: none;
    border: none;
    width: 100%;
    cursor: pointer;
}
.dropdown-content a:hover, .logout-option:hover {
    background-color: #f3f4f6;
}
.dropdown:hover .dropdown-content { display: block; }

a{
text-decoration:none
}
/* Filters */
#filters {
    display: flex;
    justify-content: center;
    gap: 30px;
    margin: 20px auto;
    padding: 10px 20px;
    background-color: #ffffff;
    border-radius: 8px;
    box-shadow: 0 4px 10px rgba(0,0,0,0.1);
    width: fit-content;
}
#filters label { font-weight: 600; margin-right: 8px; }
#filters select {
    padding: 6px 10px;
    border-radius: 6px;
    border: 1px solid #ccc;
    font-size: 14px;
}

/* Products grid */
#productsGrid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(220px, 1fr));
    gap: 20px;
    padding: 20px;
}

/* Product card */
.productCard {
    background-color: #fff;
    border: 1px solid #e5e7eb;
    border-radius: 8px;
    padding: 15px;
    text-align: center;
    transition: transform 0.2s ease, box-shadow 0.2s ease;
}
.productCard:hover {
    transform: translateY(-4px);
    box-shadow: 0 6px 12px rgba(0,0,0,0.1);
}
.productCard img {
    width: 100%;
    height: 150px;
    object-fit: cover;
    border-radius: 6px;
}
.productCard h3 { margin: 10px 0 5px; font-size: 18px; color: #111; }
.productCard p { font-size: 14px; color: #6b7280; }
.price { font-weight: bold; margin: 10px 0; color: #10b981; font-size: 16px; }

/* Buttons */
button {
    border: none;
    border-radius: 6px;
    padding: 8px 14px;
    cursor: pointer;
    background-color: #f59e0b;
    color: #fff;
    font-weight: 600;
    margin-top: 8px;
    transition: background 0.3s ease;
}
button:hover { background-color: #d97706; }

/* Modal */
.modal {
    display: none;
    position: fixed;
    z-index: 999;
    left: 0; top: 0;
    width: 100%; height: 100%;
    background-color: rgba(0,0,0,0.5);
}
.modal-content {
    background: #fff;
    margin: 15% auto;
    padding: 20px;
    border-radius: 10px;
    width: 350px;
    text-align: center;
    box-shadow: 0 6px 15px rgba(0,0,0,0.2);
    animation: slideDown 0.4s ease;
}
@keyframes slideDown {
    from {transform: translateY(-50px); opacity: 0;}
    to {transform: translateY(0); opacity: 1;}
}
.closeBtn {
    padding: 8px 16px;
    border: none;
    border-radius: 6px;
    background: linear-gradient(135deg, #6366f1, #3b82f6);
    color: #fff;
    font-weight: 500;
    cursor: pointer;
}
.closeBtn:hover { background: linear-gradient(135deg, #4f46e5, #2563eb); }
    		
    
    </style>
</head>
<body>

<div class="navbar">
  <!-- Brand -->
  <a href="productservlet?action=viewAll" class="navbar-title">EASY SHOPPPER.COM</a>

  <!-- Search -->
  <form action="ProductServlet" method="get" class="search-form">
    <input type="hidden" name="action" value="search">
    <input type="text" name="keyword" placeholder="Search products..." class="search-input">
    <button type="submit" class="search-btn">Search</button>
  </form>

  <!-- Cart -->
  <a href="cart.jsp" class="cart-container">
    <img src="images/cart1.png" alt="Cart" class="cart-icon">
    <span class="cart-count">
      <%
        int count = 0;
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if (cart != null) {
            for (Product p : cart) {
                count += p.getQuantity();
            }
        }
      %>
      <%= count %>
    </span>
  </a>

  <!-- User dropdown -->
  <div class="dropdown">
    <button class="dropbtn">User ▼</button>
    <div class="dropdown-content">
      <a href="personalDetails.jsp"><b>Personal Details</b></a>
      <a href="OrderHistoryServlet"><b>Order History</b></a>
      <form action="LoginValidation" method="get" style="margin:0;">
        <input type="hidden" name="action" value="logout">
        <button type="submit" class="logout-option">Logout</button>
      </form>
    </div>
  </div>
</div>

<!-- Filters -->
<div id="filters">
  <form action="ProductServlet" method="get">
    <input type="hidden" name="action" value="viewByCategory">
    <label>Choose Category:</label>
    <select name="category" onchange="this.form.submit()">
        <option value="all">All</option>
        <option value="electronics">Electronics</option>
        <option value="fashion">Fashion</option>
        <option value="books">Books</option>
        <option value="groceries">Groceries</option>
    </select>
  </form>

  <form action="ProductServlet" method="get">
    <input type="hidden" name="action" value="sortByPrice">
    <label>Sort by:</label>
    <select name="order" onchange="this.form.submit()">
        <option value="asc">Price: Low to High</option>
        <option value="desc">Price: High to Low</option>
    </select>
  </form>
</div>

<!-- Products Grid -->
<div id="productsGrid">
    <%
        List<Product> products = (List<Product>) request.getAttribute("products");
        if (products != null) {
            for (Product p : products) {
    %>
        <div class="productCard">
            <img src="<%= p.getImage() %>" alt="<%= p.getName() %>">
            <h3><%= p.getName() %></h3>
            <p><%= p.getDescription() %></p>
            <div class="price">₹ <%= p.getPrice() %></div>
            <input type="number" id="qty_<%= p.getId() %>" value="1" min="1">

            <form action="CartServlet" method="post">
                <input type="hidden" name="action" value="add">
                <input type="hidden" name="productId" value="<%= p.getId() %>">
                <input type="hidden" name="quantity" id="cartQty_<%= p.getId() %>">
                <button type="submit" onclick="setQty(<%= p.getId() %>, 'cart')">Add to Cart</button>
            </form>

            <form action="OrderServlet" method="post">
                <input type="hidden" name="productId" value="<%= p.getId() %>">
                <input type="hidden" name="quantity" id="buyQty_<%= p.getId() %>">
                <button type="submit" onclick="setQty(<%= p.getId() %>, 'buy')">Buy Now</button>
            </form>
        </div>
    <%
            }
        } else {
    %>
        <p>No products available.</p>
    <%
        }
    %>
</div>

<!-- Popup Modal -->
<div id="popupModal" class="modal">
    <div class="modal-content">
        <h3 id="popupTitle"></h3>
        <p id="popupMessage"></p>
        <button class="closeBtn" onclick="closeModal()">OK</button>
    </div>
</div>

<script>
function showModal(title, message) {
    document.getElementById("popupTitle").innerText = title;
    document.getElementById("popupMessage").innerText = message;
    document.getElementById("popupModal").style.display = "block";
}

function closeModal() {
    document.getElementById("popupModal").style.display = "none";
}

<% 
    String loginStatus = request.getParameter("login");
    if ("success".equals(loginStatus)) { %>
        showModal("Login Successful", "Welcome back!");
<% } else if ("wrong".equals(loginStatus)) { %>
        showModal("Login Failed", "Wrong username or password.");
<% } else if ("blocked".equals(loginStatus)) { %>
        showModal("Login Failed", "Your account has been blocked by admin.");
<% } else if ("server".equals(loginStatus)) { %>
        showModal("Error", "Server error. Please try again.");
<% } %>

window.onload = function() {
    const params = new URLSearchParams(window.location.search);
    if (params.get("error") === "outOfStock") {
        showModal("Out of Stock", "Sorry, this product is not available in the requested quantity.");
    }
};

function setQty(id, type) {
    const qty = document.getElementById("qty_" + id).value;
    if (type === 'cart') {
        document.getElementById("cartQty_" + id).value = qty;
    } else {
        document.getElementById("buyQty_" + id).value = qty;
    }
}
</script>

</body>
</html>
