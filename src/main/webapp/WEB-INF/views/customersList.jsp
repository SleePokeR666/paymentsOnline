<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Users list</title>
</head>
<body>
	<h1>Customers!</h1>
	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Login</th>
			<th>Password</th>
		</tr>
		<c:forEach items="${customers}" var="customer">
			<tr>
				<td>${customer.id}</td>
				<td>${customer.name}</td>
				<td>${customer.login}</td>
				<td>${customer.password}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
