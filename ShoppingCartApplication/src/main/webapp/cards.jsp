<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Card Payment</title>

<style>
body {
    margin: 0;
    padding: 0;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background: linear-gradient(135deg, #eef2ff, #f9fafb);
    font-family: 'Segoe UI', Arial, sans-serif;
}

#cardContainer {
    width: 400px;
    background: #fff;
    padding: 30px;
    border-radius: 12px;
    box-shadow: 0 8px 20px rgba(0,0,0,0.15);
}

h2 {
    text-align: center;
    margin-bottom: 20px;
}

label {
    display: block;
    margin-top: 12px;
    font-weight: 600;
}

input {
    width: 100%;
    padding: 10px;
    margin-top: 5px;
    border-radius: 6px;
    border: 1px solid #ccc;
    font-size: 14px;
}

input[type="submit"] {
    margin-top: 20px;
    background: linear-gradient(135deg, #22c55e, #16a34a);
    color: white;
    border: none;
    cursor: pointer;
    font-size: 16px;
    transition: 0.3s;
}

input[type="submit"]:hover {
    background: linear-gradient(135deg, #15803d, #166534);
}
</style>

<script>
function validateCardForm() {

    const cardNumber = document.getElementById("cardNumber").value.trim();
    const cvv = document.getElementById("cvv").value.trim();
    const expiry = document.getElementById("expiry").value.trim();

    // Card number check
    if (!/^\d{16}$/.test(cardNumber)) {
        alert("Card number must be 16 digits.");
        return false;
    }

    // CVV check
    if (!/^\d{3}$/.test(cvv)) {
        alert("CVV must be 3 digits.");
        return false;
    }

    // Expiry format check
    if (!/^(0[1-9]|1[0-2])\/\d{2}$/.test(expiry)) {
        alert("Expiry must be in MM/YY format.");
        return false;
    }

    // Expiry validation
    const parts = expiry.split("/");
    const expMonth = parseInt(parts[0]);
    const expYear = 2000 + parseInt(parts[1]);

    const today = new Date();
    const currentMonth = today.getMonth() + 1;
    const currentYear = today.getFullYear();

    if (expYear < currentYear || 
       (expYear === currentYear && expMonth < currentMonth)) {
        alert("Card expiry date cannot be in the past.");
        return false;
    }

    return true;
}
</script>

</head>

<body>

<div id="cardContainer">

<form action="OrderServlet" method="post" onsubmit="return validateCardForm()">

    <!-- ✅ IMPORTANT: carry checkout data -->
    <input type="hidden" name="fullname" value="${fullname}">
    <input type="hidden" name="address" value="${address}">
    <input type="hidden" name="mobileno" value="${mobileno}">
    <input type="hidden" name="paymentMethod" value="card">

    <h2>Card Payment</h2>

    <label>Name on Card</label>
    <input type="text" name="cardName" required>

    <!-- ✅ FIXED: name added -->
    <label>Card Number</label>
    <input type="text" id="cardNumber" name="cardNumber" maxlength="16" required>

    <!-- ✅ FIXED -->
    <label>CVV</label>
    <input type="password" id="cvv" name="cvv" maxlength="3" required>

    <!-- ✅ FIXED -->
    <label>Expiry Date (MM/YY)</label>
    <input type="text" id="expiry" name="expiry" placeholder="MM/YY" required>

    <input type="submit" value="Confirm Payment">

</form>

</div>

</body>
</html>
