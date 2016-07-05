<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <title>Delivery System Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body class="login-page">
    <div class="container">
        <div class="row">
            <div class="col-md-offset-3">
                <div>
                    <h2>
                    	Welcome to the Delivery System
                    </h2>
                </div>
                <div class="col col-md-4">
                    <form method="post" action="login?action=operator">
                        <div class="form-group">
                            <label for="inputLogin">Login</label>
                            <input type="text" name="login" class="form-control" id="inputLogin" placeholder="login" />
                        </div>
                        <div class="form-group">
                            <label for="inputPassword">Password</label>
                            <input type="password" name="password" class="form-control" id="inputPassword" placeholder="password" />
                        </div>
                        <c:if test="${isWrongPassword != null}">
                        <div>
                        	Wrong Password!
                        </div>
                        </c:if>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"></link>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <style type="text/css">
    .login-page {
        background-color: rgba(0, 0, 0, 0.1);
        background-image: url("./images/bgStreets.jpg");
        background-size: cover;
    }
    </style>
</body>

</html>
