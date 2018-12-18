<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<title>Transfer money</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<body>

<form action="transfer"
	  class="w3-container w3-card-4 w3-light-grey w3-text-blue w3-margin"
	  method="post" accept-charset="UTF-8">
	<h2 class="w3-center">Make transfer</h2>

	<div class="w3-row w3-section">
		<div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-user"></i></div>
		<div class="w3-rest">
			<input class="w3-input w3-border" name="amount" type="text" placeholder="amount">
		</div>
	</div>

	<div class="w3-row w3-section">
		<div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-user"></i></div>
		<div class="w3-rest">
			<input class="w3-input w3-border" name="targetAccountId" type="text"
				   placeholder="account number">
		</div>
	</div>

	<button class="w3-button w3-block w3-section w3-blue w3-ripple w3-padding">Transfer
		money</button>

</form>
</body>
</html>