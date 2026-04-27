<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <style>
       /* Reset */
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

/* Base */
body {
    background: linear-gradient(135deg, #6366f1, #3b82f6);
    font-family: 'Segoe UI', Arial, sans-serif;
    height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
}

/* Login container */
#logincontainer {
    background-color: #fff;
    color: #333;
    padding: 40px 30px;
    border-radius: 12px;
    box-shadow: 0 8px 25px rgba(0,0,0,0.15);
    width: 350px;
    text-align: center;
    animation: fadeIn 0.6s ease;
}

/* Title */
#logincontainer h2 {
    margin-bottom: 20px;
    font-size: 24px;
    font-weight: 700;
    color: #1f2937;
}

/* Form */
#loginform {
    display: flex;
    flex-direction: column;
    gap: 15px;
}

/* Labels */
#loginform label {
    font-size: 14px;
    font-weight: 500;
    text-align: left;
    margin-bottom: 4px;
    color: #374151;
}

/* Inputs */
#loginform input[type="text"],
#loginform input[type="password"],
#loginform select {
    width: 100%;
    padding: 12px;
    border: 1px solid #d1d5db;
    border-radius: 8px;
    font-size: 14px;
    transition: border-color 0.3s ease, box-shadow 0.3s ease;
}
#loginform input:focus,
#loginform select:focus {
    border-color: #6366f1;
    box-shadow: 0 0 0 3px rgba(99,102,241,0.3);
    outline: none;
}

/* Button */
#loginform button {
    padding: 12px;
    background: linear-gradient(135deg, #6366f1, #3b82f6);
    color: #fff;
    border: none;
    border-radius: 8px;
    font-size: 16px;
    font-weight: 600;
    cursor: pointer;
    transition: transform 0.2s ease, background 0.3s ease;
}
#loginform button:hover {
    transform: translateY(-2px);
    background: linear-gradient(135deg, #4f46e5, #2563eb);
}

/* Redirect link */
#loginform p {
    margin-top: 15px;
    font-size: 14px;
}
#loginform a {
    color: #6366f1;
    text-decoration: none;
    font-weight: 500;
}
#loginform a:hover {
    text-decoration: underline;
}

/* Animation */
@keyframes fadeIn {
    from { opacity: 0; transform: translateY(-10px); }
    to { opacity: 1; transform: translateY(0); }
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
    <div id="logincontainer">
    
    
    
        <form action="AuthServlet" method="post">
		    <input type="hidden" name="action" value="signin">
		    <label>Username:</label>
		    <input type="text" name="username" required><br>
		
		    <label>Password:</label>
		    <input type="password" name="password" required><br>
		
		    <label>Role:</label>
		    <select name="role" required>
		        <option value="user">User</option>
		        <option value="admin">Admin</option>
		    </select><br>
		
		    <button type="submit">Login</button>
		    <p>New user? <a href="register.jsp">Register here</a></p>
		    <p>Forgot Password? <a href="forgot.jsp">Forgot Password</a></p>
		    <p>Reset Password? <a href="reset.jsp">Reset Password</a></p>
		</form>

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