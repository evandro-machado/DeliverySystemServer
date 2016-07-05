<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delivery Panel</title>
</head>
<body class="login-page">
	<div class="container">
		<div class="row">
			<div class="col-md-2">
				<div>
					<h2>Menu</h2>
					<strong> <a href="./delivery?action=list">Delivery Status</a>
					</strong>
				</div>
				<div>
					<strong> <a href="./newDeliveryman.jsp">New
							Deliveryman</a>
					</strong>
				</div>
				<div>
					<strong> <a href="./delivery?action=addScreen">New Delivery</a>
					</strong>
				</div>
			</div>
			<div class="col-md-5">
				<div>
					<h2>Register New Deliveryman</h2>
				</div>
				<div class="col col-md-12">
					<form method="post" action="register">
						<div class="form-group">
							<label for="inputLogin">Login</label> <input type="text"
								name="login" class="form-control" id="inputLogin"
								placeholder="login" />
						</div>
						<div class="form-group">
							<label for="inputPassword">Password</label> <input
								type="password" name="password" class="form-control"
								id="inputPassword" placeholder="password" />
						</div>
						<button type="submit" class="btn btn-default">Register</button>
					</form>
				</div>

			</div>
		</div>
	</div>
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"></link>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<style type="text/css">
.login-page {
	background-color: rgba(0, 0, 0, 0.1);
	background-image: url("./images/bgStreets.jpg");
	background-size: cover;
}
</style>
</body>
</html>