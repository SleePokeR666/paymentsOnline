<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Main page</title>
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-light-grey">

<!-- Page container -->
<div class="w3-container">
	<header class="w3-blue-grey w3-center">
		<h1>Онлайн платежи, как бальзам для души!</h1>
	</header>

	<!-- User info -->
	<div class="w3-panel">
		<a href="../index" class="w3-bar-item w3-button w3-deep-purple w3-right w3-margin">Logout
		</a>
		<p>${customer.name}</p>
		<p>${customer.login}</p>
	</div>

	<!-- The grid -->
	<div class="w3-row-padding">

		<!-- Left column -->
		<div class="w3-quarter">
			<div class="w3-container w3-light-blue">
				<h1>
					Здесь будет полезная левая панель!
				</h1>
			</div>
			<!-- End left column -->
		</div>

		<!-- Middle column -->
		<div class="w3-half">
			<div class="w3-container w3-light-blue">
				<h1>
					Здесь будет информация об аккаунте!
				</h1>
			</div>
			<!-- End middle column -->
		</div>

		<!-- Right column -->
		<div class="w3-quarter">
			<div class="w3-container w3-light-blue">
				Здесь будет полезная правая панель!
			</div>

			<!-- End right column -->
		</div>

		<!-- End the grid -->
	</div>
	<footer class="w3-blue-grey w3-center">
		<h1>Самый тёмный подвал в мире!</h1>
	</footer>

	<!-- End page container -->
</div>
</body>
</html>