<%@page import="java.sql.ResultSet"%>
<%@page import="com.connection.Dbconn"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.activity.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>Secure RBAC</title>

<!-- core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="css/animate.min.css" rel="stylesheet">
<link href="css/prettyPhoto.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet">
<link href="css/responsive.css" rel="stylesheet">

<body class="homepage">
	<header id="header">
	<div class="top-bar">
		<div class="container">
			<div class="row">
				<div class="col-sm-6 col-xs-4">
					<nav class="navbar navbar-inverse" role="banner">
					<div class="container">
						<div class="navbar-header center wow fadeInDown">
							<button type="button" class="navbar-toggle"
								data-toggle="collapse" data-target=".navbar-collapse">
								<span class="sr-only">Toggle navigation</span> <span
									class="icon-bar"></span> <span class="icon-bar"></span> <span
									class="icon-bar"></span>
							</button>
							<a class="navbar-brand " href="#">"Data Security of Dynamic and Robust Role Based Access Control from Multiple Authorities in Cloud Environment"</a>
						</div>
					 </div>
					</nav>
				</div>
			</div>
		</div>
	</div>
</header>
	<!--/.top-bar--> <nav class="navbar navbar-inverse" role="banner">
	<div class="container">
		<div class="collapse navbar-collapse ">
			<ul class="nav navbar-nav">
                        <li class="active"><a href="UserHome.jsp">Home</a></li>
                       	<li><a href="MultipleFileUpload.jsp">FileUpload</a></li>
						<li><a href="FileDownload.jsp">File Download</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><%=session.getAttribute("name")%><i class="fa fa-angle-down"></i></a>
                            <ul class="dropdown-menu">
                                <li><a href="Login">Logout</a></li> 
                            </ul>
                        </li>                       
                    </ul>
		</div>
	</div>
	</nav>

	<section id="feature">
	<div class="container">
		<div class="center wow fadeInDown">
			<h2>SEARCH FILE</h2>
			 
			 <div class="col-md-8 col-md-offset-2">
				<form enctype="multipart/form-data"  action="multifileuplaod" method="post" autocomplete="off">
				<div class="form-group">
						<table class="table table-responsive table-bordered" width="100%">
							<tr>
								<td width="30%"><label for="txt_Uname">Select File
										Path:</label></td>
								<td width="30%"><input type="file" directory multiple
									style="border-color: gray;" name="txt_search" id="txt_search" /></td>
								<td align="left"><input type="submit" name="btn_Submit"
									id="btn_Submit" Value="Submit"><br></td>
							</tr>
						</table>
					</div>
			</form> 			
			</div>
		</div>
		<div class="row">
		<div class="features"></div>
		</div>
	 </div>
	 </section>
	<footer id="footer" class="midnight-blue"> </footer>
	<!--/#footer-->

	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.prettyPhoto.js"></script>
	<script src="js/jquery.isotope.min.js"></script>
	<script src="js/main.js"></script>
	<script src="js/wow.min.js"></script>
</body>
</html>