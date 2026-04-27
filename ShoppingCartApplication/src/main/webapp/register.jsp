<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        body {
    font-family: 'Segoe UI', Arial, sans-serif;
    background: linear-gradient(135deg, #f9fafb, #eef2ff);
    margin: 0;
    padding: 40px;
    color: #1e293b;
}

#signupcontainer {
    background: #111827;
    color: #ffffff;
    display: flex;
    flex-direction: column;
    margin: 80px auto;
    border-radius: 12px;
    width: 320px;
    padding: 30px;
    box-shadow: 0 8px 20px rgba(0,0,0,0.25);
    transition: transform 0.3s ease;
}
#signupcontainer:hover {
    transform: translateY(-3px);
}

#signupform {
    display: flex;
    flex-direction: column;
    gap: 12px;
    align-items: stretch;
    justify-content: center;
}

label {
    font-weight: 500;
    margin-bottom: 4px;
    color: #f3f4f6;
}

input, select {
    padding: 10px;
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

#signupbtn {
    margin-top: 15px;
    padding: 12px;
    border-radius: 8px;
    border: none;
    background: linear-gradient(135deg, #6366f1, #3b82f6);
    color: #ffffff;
    font-weight: 600;
    font-size: 15px;
    cursor: pointer;
    transition: background 0.3s ease, transform 0.2s ease;
}
#signupbtn:hover {
    background: linear-gradient(135deg, #4f46e5, #2563eb);
    transform: translateY(-2px);
}
#signupbtn:active {
    transform: scale(0.98);
}

#logredirect {
    display: block;
    margin-top: 15px;
    text-align: center;
    color: #6366f1;
    text-decoration: none;
    font-weight: 500;
    transition: color 0.3s ease;
}
#logredirect:hover {
    text-decoration: underline;
    color: #4f46e5;
}

    </style>
</head>
<body>
    <div id="signupcontainer">
        <form id="signupform" action="AuthServlet" method="post">
        <input type="hidden" name="action" value="signup">
            Username<input type="text" id="signupuname" name="username">
            Password<input type="text" id="signuppass" name="password">
            Email<input type="email" id="signupmail" name="email">
            
            Mobile No<input type="text" id="signupmobile" name="mobileno">
            Address<input type="text" id="signupaddress" name="address">
            Security Question <select name="securityques" required> 
            <option value="">--Select--</option> 
            <option value="pet">What is your pet's name?</option> 
            <option value="school">What was your first school?</option> 
            <option value="city">What is your birth city?</option> 
            </select> 
            
            Answer <input type="text" name="secquesans" required>
            <button id="signupbtn">SIGN UP</button>
            <a id="logredirect" href="login.jsp">Already a User? Login Now</a>

        </form>
    </div>
</body>
</html>