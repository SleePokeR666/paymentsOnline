<%@ page contentType="text/html;charset=UTF-8" %>
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
			<td><a href="customer/${customer.id}">${customer.id}</a></td>
			<td>${customer.name}</td>
			<td>${customer.login}</td>
			<td>${customer.password}</td>
			<td><a href="delete/${customer.id}">Delete</a></td>
			<td><a href="update/${customer.id}">Update</a></td>
		</tr>
	</c:forEach>
</table>
<a href="index">На главную</a>
<p>
<table>
	<tr>
		<th>Id ${customer.id}</th>
		<th>Name ${customer.name}</th>
		<th>Login ${customer.login}</th>
		<th>Password ${customer.password}</th>
	</tr>
</table>
</p>
</body>
</html>
