<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
	<title>Registration</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="w3-card-4">
	<div class="w3-container w3-purple">
		<h2>Register</h2>
	</div>
	<form class="w3-container" action="${pageContext.request.contextPath}/customer/register"
		  method="post" accept-charset="UTF-8">
		<p>
			<label class="w3-text-deep-purple"><b>Name</b></label>
			<input class="w3-input w3-border w3-sand" name="name" type="text" placeholder="Name"
				   required pattern="[A-Za-zА-яа-я0-9_]+"
				   title="At least one alphanumeric or underscore symbol">
		</p>
		<p>
			<label class="w3-text-deep-purple"><b>Login</b></label>
			<input class="w3-input w3-border w3-sand" name="login" type="text"
				   placeholder="Login" required pattern="[A-Za-z0-9_]{3,}"
				   title="At least three alphanumeric or underscore symbols">
		</p>
		<div class="w3-text-deep-purple"><b>${errorMessage}</b></div>
		<p>
			<label class="w3-text-deep-purple"><b>Password</b></label>
			<input class="w3-input w3-border w3-sand" name="password" type="password"
				   placeholder="Password" required pattern="[A-Za-z0-9_]{5,}"
				   title="At least five alphanumeric or underscore symbols">
		</p>
		<p>
			<a class="w3-btn w3-deep-purple" href="${pageContext.request.contextPath}/index">Back
			</a>
			<button class="w3-btn w3-deep-purple">Register</button>
		</p>
	</form>
</div>
</body>
</html>