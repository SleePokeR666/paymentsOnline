<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" charset="UTF-8">
	<title>Online Payments</title>
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="w3-container">
	<div class="w3-bar w3-purple">
		<a href="login" class="w3-button w3-deep-purple w3-right">Login</a>
		<a href="register" class="w3-button w3-deep-purple w3-right">Register</a>
	</div>
</div>

<a href="customers">View all customers</a>
<form action="customer">
	<input type="text" name="customerId" placeholder="Id клиента"> Просмотр клиента по ID
	<input type="submit">
</form>
</body>
</html>