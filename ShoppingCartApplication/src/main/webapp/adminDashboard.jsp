<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.beans.Admin"%>
 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <style>
        body {
    font-family: 'Segoe UI', Arial, sans-serif;
    background: linear-gradient(135deg, #f9fafb, #eef2ff);
    margin: 0;
    padding: 40px;
    color: #1e293b;
}

#dashboardContainer {
    width: 600px;
    margin: 80px auto;
    background: #ffffff;
    padding: 40px;
    border-radius: 12px;
    box-shadow: 0 8px 20px rgba(0,0,0,0.12);
    text-align: center;
    transition: transform 0.3s ease;
}
#dashboardContainer:hover {
    transform: translateY(-3px);
}

h2 {
    font-size: 26px;
    font-weight: 700;
    color: #111827;
    margin-bottom: 20px;
}

p {
    font-size: 15px;
    margin-bottom: 25px;
    color: #374151;
}

.navBtn {
    display: inline-block;
    margin: 12px;
    padding: 12px 20px;
    border-radius: 8px;
    font-weight: 600;
    text-decoration: none;
    color: #ffffff;
    background: linear-gradient(135deg, #6366f1, #3b82f6);
    transition: background 0.3s ease, transform 0.2s ease;
}
.navBtn:hover {
    background: linear-gradient(135deg, #4f46e5, #2563eb);
    transform: translateY(-2px);
}

.logoutBtn {
    display: inline-block;
    margin-top: 30px;
    padding: 12px 20px;
    border-radius: 8px;
    font-weight: 600;
    text-decoration: none;
    color: #ffffff;
    background: linear-gradient(135deg, #ef4444, #dc2626);
    transition: background 0.3s ease, transform 0.2s ease;
}
.logoutBtn:hover {
    background: linear-gradient(135deg, #b91c1c, #991b1b);
    transform: translateY(-2px);
}
 .modal {
            display: none; /* hidden by default */
            position: fixed;
            z-index: 999;
            left: 0; top: 0;
            width: 100%; height: 100%;
            background-color: rgba(0,0,0,0.5);
        }

        /* Modal content box */
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

        .modal-content h3 {
            margin-bottom: 15px;
            font-size: 18px;
            font-weight: 600;
        }

        .modal-content p {
            margin-bottom: 20px;
            font-size: 14px;
        }

        .closeBtn {
            padding: 8px 16px;
            border: none;
            border-radius: 6px;
            background: linear-gradient(135deg, #6366f1, #3b82f6);
            color: #fff;
            font-weight: 500;
            cursor: pointer;
            transition: background 0.3s ease;
        }
        .closeBtn:hover {
            background: linear-gradient(135deg, #4f46e5, #2563eb);
        }
    </style>
</head>
<body>
    <div id="dashboardContainer">
        
        <p>Select an option below to manage the application:</p>

        <a class="navBtn" href="adminservlet?action=viewUsers">View Users</a>
        <a class="navBtn" href="adminservlet?action=viewProducts">View Products</a>
        <a class="navBtn" href="addProduct.jsp">Add Product</a>
        <a class="navBtn" href="adminservlet?action=viewOrders">View Orders</a>
        

        <br>
        <a class="logoutBtn" href="AuthServlet?action=logout">Logout</a>
    </div>
    
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
</script>
</body>
</html>
