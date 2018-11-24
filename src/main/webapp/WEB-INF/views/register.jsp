<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Registration</title>
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="w3-card-4">
	<div class="w3-container w3-purple">
		<h2>Register</h2>
	</div>
	<form class="w3-container" action="register" method="post">
		<p>
			<label class="w3-text-deep-purple"><b>Name</b></label>
			<input class="w3-input w3-border w3-sand" name="name" type="text">
		</p>
		<p>
			<label class="w3-text-deep-purple"><b>Login</b></label>
			<input class="w3-input w3-border w3-sand" name="login" type="text">
		</p>
		<p>
			<label class="w3-text-deep-purple"><b>Password</b></label>
			<input class="w3-input w3-border w3-sand" name="password" type="text">
		</p>
		<p>
			<button class="w3-btn w3-deep-purple">Register</button>
		</p>
	</form>
</div>
</body>
</html>