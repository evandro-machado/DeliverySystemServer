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
			<div class="col-md-8">
				<div>
					<h2>Products List</h2>
				</div>
				<div class="col col-md-12">
					<table class="table table-striped table-bordered"
						style="background-color: white;">
						<tr>
							<th>Product</th>
							<th>Description</th>
							<th>Address</th>
							<th>Deliveryman</th>
							<th>Status</th>
						</tr>
						<c:forEach var="delivery" items="${deliveryList}">
							<tr>
								<td><img width="30px" height="30px"
									src="./image?id=${delivery.id}" /></td>
								<td>${delivery.description}</td>
								<td>${delivery.address}</td>
								<td>${delivery.employee.login}</td>
								<td>${delivery.status}</td>
							</tr>
						</c:forEach>
					</table>
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