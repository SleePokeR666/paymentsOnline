<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html lang="en">
<head>
	<title><spring:message code="ui.index.title"></spring:message></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-light-grey">

<!-- Page container -->
<div class="w3-container">
	<header class="w3-blue-grey w3-center">
		<h1><spring:message code="ui.index.header"></spring:message></h1>
	</header>

	<!-- The grid -->
	<div class="w3-row-padding">

		<!-- Left column -->
		<div class="w3-twothird">
			<div class="w3-container w3-light-blue">
				<h1>
					<spring:message code="ui.index.content"></spring:message>
				</h1>
			</div>
			<!-- End left column -->
		</div>

		<!-- Right column -->
		<div class="w3-third">
			<div class="w3-container w3-light-blue">
				<div class="w3-bar w3-border">
					<a href="login"
					   class="w3-bar-item w3-button w3-deep-purple w3-right w3-margin">
					   <spring:message code="ui.button.login"></spring:message>
					</a>
					<a href="register" class="w3-bar-item w3-button w3-deep-purple w3-right w3-margin">
					<spring:message code="ui.button.register"></spring:message></a>
					<form action="">
						<input type="submit"><spring:message code="ui.select.language"/></input>
						<select id="locale" name="locale">
							<option value=""></option>
							<option value="en">
								<spring:message code="ui.select.language.en"/>
							</option>
							<option value="ru">
								<spring:message code="ui.select.language.ru"/>
							</option>
						</select>
					</form>
				</div>
			</div>
			<!-- End right column -->
		</div>

		<!-- End the grid -->
	</div>
	<footer class="w3-blue-grey w3-center">
		<h1><spring:message code="ui.index.footer"></spring:message></h1>
	</footer>

	<!-- End page container -->
</div>
</body>
</html>
