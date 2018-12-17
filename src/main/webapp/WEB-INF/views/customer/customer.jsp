<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
	<title>Main page</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-light-grey">

<!-- Page container -->
<div class="w3-container">
	<header class="w3-blue-grey w3-center">
		<h1>Online payments</h1>
	</header>

	<!-- User info -->
	<div class="w3-panel">
		<a href="logout" class="w3-bar-item w3-button w3-deep-purple w3-right w3-margin">Logout
		</a>
		<p>${customer.name}</p>
		<p>${customer.login}</p>
	</div>

	<!-- The grid -->
	<div class="w3-row-padding">

		<!-- Left column -->
		<div class="w3-col l2">
			<div class="w3-container w3-light-blue">
				<h1>
					Left panel
				</h1>
			</div>
			<!-- End left column -->
		</div>

		<!-- Middle column -->
		<div class="w3-col l8">
			<div class="w3-row-padding w3-light-blue">
				<h1>
					Account info
				</h1>
				<c:forEach items="${customer.creditCards}" var="creditCard">
					<div class="w3-third">
						<div class="w3-card-4 w3-dark-grey">
							<div class="w3-container">
								<div>Номер счёта: ${creditCard.account.id}</div>
								<div class="w3-center">Баланс</div>
								<p class="w3-center">${creditCard.account.balance}</p>
								<div>Номер карты: ${creditCard.number}</div>
								<c:choose>
									<c:when test="${creditCard.account.isActive}">
										<div class="w3-section w3-center">
											<a class="w3-button w3-green">Сделать перевод</a>
										</div>
										<div class="w3-section w3-center">
											<a class="w3-button w3-green">Пополнить счёт</a>
											<a href="account/${creditCard.account.id}/block"
											   class="w3-button w3-red">Заблокировать счёт</a>
										</div>
									</c:when>
									<c:otherwise>
										<h3 class="w3-center">Счёт заблокирован</h3>
										<div class="w3-section w3-center">
											<button class="w3-button w3-green" disabled>Сделать
												перевод
											</button>
										</div>
										<div class="w3-section w3-center">
											<button class="w3-button w3-green" disabled>Пополнить
												счёт
											</button>
											<button class="w3-button w3-red" disabled>Заблокировать
												счёт
											</button>
										</div>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</c:forEach>
				<div class="w3-clear"></div>
			</div>
			<!-- End middle column -->
		</div>

		<!-- Right column -->
		<div class="w3-col l2">
			<div class="w3-container w3-light-blue">
				Right panel
			</div>

			<c:if test="${customer.isAdmin}">
				<a href="customers" class="w3-button w3-green">Список клиентов</a>
			</c:if>

			<!-- End right column -->
		</div>

		<!-- End the grid -->
	</div>
	<footer class="w3-blue-grey w3-center">
		<h1>Footer</h1>
	</footer>

	<!-- End page container -->
</div>
</body>
</html>
