<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Login</title>
    <style>
        body { background-color: #f0f0f0; font-family: Arial; }
        #adminLoginContainer {
            background-color: black; color: white;
            margin: auto; margin-top: 100px;
            width: 300px; padding: 20px;
            border-radius: 8px; text-align: center;
        }
        input {
            width: 90%; padding: 8px; margin: 8px 0;
            border-radius: 5px; border: none;
        }
        button {
            margin-top: 10px; padding: 8px 15px;
            border-radius: 5px; border: none;
            background-color: orange; color: black;
            cursor: pointer;
        }
        a {
            color: white; text-decoration: underline;
            display: block; margin-top: 10px;
        }
    </style>
</head>
<body>
    <div id="adminLoginContainer">
        <h3>Admin Login</h3>
        <form action="adminservlet" method="post">
            <input type="hidden" name="action" value="login">
            Username <input type="text" name="username" required>
            Password <input type="password" name="password" required>
            <button type="submit">LOGIN</button>
        </form>
        <a href="login.jsp">Back to User Login</a>
    </div>
</body>
</html>
