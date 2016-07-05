<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
					<strong> <a href="./delivery?action=list">Delivery
							Status</a>
					</strong>
				</div>
				<div>
					<strong> <a href="./newDeliveryman.jsp">New
							Deliveryman</a>
					</strong>
				</div>
				<div>
					<strong> <a href="./delivery?action=addScreen">New
							Delivery</a>
					</strong>
				</div>
			</div>
			<div class="col-md-5">
				<div>
					<h2>Register New Delivery</h2>
				</div>
				<div class="col col-md-12">
					<form method="post" action="delivery?action=add"
						enctype="multipart/form-data">
						<div class="form-group">
							<label for="inputAddress">Address</label> <input type="text"
								name="address" class="form-control" id="inputAddress"
								placeholder="Address" />
						</div>
						<div class="form-group">
							<label for="inputImage">Image</label> <input name="image"
								id="inputImage" type="file" accept="image/jpeg">
						</div>
						<div class="form-group">
							<label for="inputDescription">Description</label> <input
								type="text" name="description" class="form-control"
								id="inputDescription" placeholder="Description" />
						</div>
						<div class="form-group">
							<label for="inputDeliveryman">Deliveryman</label>
							<div>
								<select class="form-control" name="deliverymanid">
									<c:forEach var="deliveryman" items="${deliverymanList}">
										<option value="${deliveryman.id}">${deliveryman.login}</option>
									</c:forEach>
								</select>
							</div>
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