
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

<!-- Isolated Version of Bootstrap, not needed if your site already uses Bootstrap -->
<link rel="stylesheet"
	href="https://formden.com/static/cdn/bootstrap-iso.css" />
<!-- Bootstrap Date-Picker Plugin -->
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<style>
.square_btn {
	position: relative;
	display: inline-block;
	font-weight: bold;
	padding: 20px 20% 20px 20%;
	text-decoration: none;
	color: #FFA000;
	background: #fff1da;
	border-bottom: solid 4px #FFA000;
	border-radius: 15px 15px 0 0;
	transition: .4s;
}

.square_btn:hover {
	background: #ffc25c;
	color: #FFF;
}

.sidebar {
	margin: 0;
	padding: 0;
	width: 150px;
	background-color: #C9C7AF;
	position: fixed;
	height: 100%;
	overflow: auto;
}

/* Sidebar links */
.sidebar a {
	display: block;
	color: black;
	padding: 16px;
	text-decoration: none;
}

/* Active/current link */
.sidebar a.active {
	background-color: #FFA000;
	color: white;
}

/* Links on mouse-over */
.sidebar

 

a


:hover
:not

 

(
.active

 

)
{
background-color


:

 

#FFA000


;
color:white;
}

/* Page content. The value of the margin-left property should match the value of the sidebar's width property */
div.content {
	margin-left: 200px;
	padding: 1px 16px;
	height: 1000px;
}

/* On screens that are less than 700px wide, make the sidebar into a topbar */
@media screen and (max-width: 700px) {
	.sidebar {
		width: 100%;
		height: auto;
		position: relative;
	}
	.sidebar a {
		float: left;
	}
	div.content {
		margin-left: 0;
	}
}

/* On screens that are less than 400px, display the bar vertically, instead of horizontally */
@media screen and (max-width: 400px) {
	.sidebar a {
		text-align: center;
		float: none;
	}
}
</style>

<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body style="background: #F3F0E8">


	<div class="sidebar">
		<a class="active" href="#home">Home</a>
		 <a href="logout">Logout</a> 
	</div>

	<div class="container" style="padding: 60px;">
	
	<jsp:include page="navbar.jsp" />
