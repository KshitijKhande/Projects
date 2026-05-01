# 🛒 Shopping Cart Application

A Java-based web application that implements a shopping cart and order management system using **Servlets, JSP, JDBC, and MySQL**.  
This project demonstrates a normalized schema with **order headers** and **order items**, ensuring that multiple products in a cart share the same `order_id`.

---

## ✨ Features
- User authentication and session management
- Add, update, and remove products from cart
- Checkout flow with **single order_id** for multiple products
- Payment method support (COD, Card, UPI)
- Stock validation before order placement
- Order history for users and admin
- Normalized database schema (`ordertable` + `order_items`)

---

⚙️ Setup Instructions
Clone the repository:

-----git clone https://github.com/yourusername/ShoppingCartApplication.git

Import into Eclipse/IntelliJ as a Maven project.

Configure your database in DBConnection.java:

java
private static final String URL = "jdbc:mysql://localhost:3306/shoppingdb";
private static final String USER = "root";
private static final String PASSWORD = "yourpassword";
Run the SQL scripts to create tables (ordertable, order_items, products, users).

Deploy on Apache Tomcat.

🚀 Usage
-Register/Login as a user.
-Browse products and add them to cart.
=Proceed to checkout → one order_id is generated per checkout.
-Admin can view all orders with product details.
