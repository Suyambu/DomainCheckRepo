<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container pt-3" style="text-align:center">
		<form action="login" method="post">  
		<div class="form-group">
		<label for="email">UserName:</label>
		<input type="text" class="form-control" id = "email" name="username" style = "width:50% !important;margin:auto"/><br/>  
		</div>
		<div class="form-group">
		<label for="pwd">Password:</label>
		<input type="password" class="form-control"id="pwd" name="password" style = "width:50% !important;margin:auto"/><br/>
		</div>
		
		<% if(request.getAttribute("alert") != null){ %>
		<div class="alert alert-danger alert-dismissible fade show">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<%=request.getAttribute("alert") %>
		
		</div>
		<%}%>

		<input type="submit" value="Login"  class="btn btn-secondary btn-lg" />
		</form>
	</div>
</body>
</html>