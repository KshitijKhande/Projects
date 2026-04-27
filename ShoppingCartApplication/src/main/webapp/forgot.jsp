<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Forgot Password</title>
    <style>
        body {
    font-family: 'Segoe UI', Arial, sans-serif;
    background: linear-gradient(135deg, #f9fafb, #eef2ff);
    margin: 0;
    padding: 40px;
    color: #1e293b;
}

#forgotcontainer {
    background: #111827;
    color: #ffffff;
    margin: 100px auto;
    width: 320px;
    padding: 30px;
    border-radius: 12px;
    text-align: center;
    box-shadow: 0 8px 20px rgba(0,0,0,0.25);
    transition: transform 0.3s ease;
}
#forgotcontainer:hover {
    transform: translateY(-3px);
}

h3 {
    margin-bottom: 20px;
    font-size: 20px;
    font-weight: 600;
    color: #f3f4f6;
}

input, select {
    width: 100%;
    padding: 12px;
    margin-top: 8px;
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
    margin-top: 20px;
    width: 100%;
    padding: 12px;
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

a {
    display: block;
    margin-top: 15px;
    color: #6366f1;
    text-decoration: none;
    font-weight: 500;
    transition: color 0.3s ease;
}
a:hover {
    text-decoration: underline;
    color: #4f46e5;
}
        
    </style>
</head>
<body>
    <div id="forgotcontainer">
        <h3>Forgot Password</h3>
        <form action="AuthServlet" method="post">
            <input type="hidden" name="action" value="forgot">
            Username <input type="text" name="username" required>
            Security Question
            <select name="securityques" required>
                <option value="">--Select--</option>
                <option value="pet">What is your pet's name?</option>
                <option value="school">What was your first school?</option>
                <option value="city">What is your birth city?</option>
            </select>
            Answer <input type="text" name="secquesans" required>
            <button type="submit">Recover Password</button>
        </form>
        <a href="login.jsp" style="color:white; text-decoration:underline;">Back to Login</a>
    </div>
</body>
</html>
