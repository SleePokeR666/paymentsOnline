<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Customer</title>
</head>
<body>
<h1>Customer</h1>
<table>
	<tr>
		<th>Id</th>
		<th>Name</th>
		<th>Login</th>
		<th>Password</th>
	</tr>
	<tr>
		<td>${customer.id}</td>
		<td>${customer.name}</td>
		<td>${customer.login}</td>
		<td>${customer.password}</td>
	</tr>
</table>
<a href="${pageContext.request.contextPath}/index">На главную</a>
</body>
</html>
