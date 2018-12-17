<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Users list</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="w3-container">
	<h1>Customers</h1>
	<table class="w3-table-all w3-hoverable">
		<tr class="w3-light-grey">
			<th>Id</th>
			<th>Name</th>
			<th>Login</th>
			<th>Card Number</th>
			<th>Account balance</th>
			<th>Account status</th>
		</tr>
		<c:forEach items="${customers}" var="customer">
			<c:forEach items="${customer.creditCards}" var="creditCard">
				<tr>
					<td><a href="customer/${customer.id}">${customer.id}</a></td>
					<td>${customer.name}</td>
					<td>${customer.login}</td>
					<td>${creditCard.number}</td>
					<td>${creditCard.account.balance}</td>
					<c:choose>
						<c:when test="${creditCard.account.isActive}">
							<td>Active <a href="account/${creditCard.account.id}/block">Block
								account</a>
							</td>
						</c:when>
						<c:otherwise>
							<td>Active <a href="account/${creditCard.account.id}/unblock">Unblock
								account</a>
							</td>
						</c:otherwise>
					</c:choose>
					<td><a href="update/${customer.id}">Update</a></td>
				</tr>
			</c:forEach>
		</c:forEach>
	</table>
</div>
<a href="index">На главную</a>
</body>
</html>
