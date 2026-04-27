<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Error</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f8d7da; color: #721c24; padding: 20px; }
        .box { background: white; padding: 20px; border-radius: 8px; max-width: 500px; margin: auto; }
    </style>
</head>
<body>
    <div class="box">
        <h2>Oops! Something went wrong.</h2>
        <p><%= request.getParameter("error") != null ? request.getParameter("error") : "Unknown error" %></p>
        <p><a href="adminDashboard.jsp">Back to Dashboard</a></p>
    </div>
</body>
</html>
