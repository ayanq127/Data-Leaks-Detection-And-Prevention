<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

    <%@ page import="java.sql.*" %>
    <%@ page import="com.connection.Dbconn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Secure Role Base Access Control in Cloud environemnt  </title>
	
	<!-- core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <link href="css/responsive.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
<!--/head-->
</head>

<body class="homepage">

    <header id="header">
        <div class="top-bar">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6 col-xs-4">
                 <nav class="navbar navbar-inverse" role="banner">
            <div class="container">
                <div class="navbar-header center wow fadeInDown">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
						<jsp:include page="titlepage.jsp"></jsp:include>                 
                </div>
		
		</nav><!--/nav-->      
                    </div>
                   
                </div>
            </div><!--/.container-->
        </div><!--/.top-bar-->

        <nav class="navbar navbar-inverse" role="banner">
            <div class="container">
                
                <div class="collapse navbar-collapse ">
                    <ul class="nav navbar-nav">
                       <li ><a href="InverstigatorHome.jsp">Home</a></li>
							<li><a href="Inverstigatorshow.jsp">Show Data</a></li>
							
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><%=session.getAttribute("Investigatorname")%><i class="fa fa-angle-down"></i></a>
                            <ul class="dropdown-menu">
                                <li><a href="Login">Logout</a></li> 
                            </ul>
                        </li>
                        
                                               
                    </ul>
                </div>
            </div><!--/.container-->
        </nav><!--/nav-->
		
            </div><!--/.container-->
        		
    </header><!--/header-->

 
    <section id="feature" >
        <div class="container">
           <div class="center wow fadeInDown">
                <h2>HOME PAGE</h2>
                <p class="lead">Welcome to Home, "<%=session.getAttribute("tpaname")%>"	</p>
                
                <%

			Connection con = Dbconn.conn();
	%>
	<table class="table table-responsive table-bordered">
	
	<tr>
	<td ><h3>IP_Address</h3></td>
	<td  ><h3>Mac_Address</h3></td>
	<td  ><h3>UserName</h3></td>
	<td ><h3>Time_Data</h3></td>
	<td><h3>Attacker_Server First</h3></td>
	<td><h3>Attacker_Server Second</h3></td>
	
			
	</tr>
	
				<%
				String msg1="Accept",msg2="Reject";
				Statement stat1 = con.createStatement();
				String qrry1 = "select * from attacker";
				ResultSet rs1= stat1.executeQuery(qrry1);
				while (rs1.next())
				 {
					 
       		 %>	
       		 <tr>
           
                 <td> <%= rs1.getString("IP_Address") %> </td>
              <td> <%= rs1.getString("Mac_Address") %> </td>
              <td> <%= rs1.getString("UserName") %> </td>
              <td> <%= rs1.getString("Time_Data") %> </td>
               <td> <%= rs1.getString("Attacker_Server") %> </td>
              <td> <%= rs1.getString("Attacker_ServerSecond") %> </td>
               
               </tr>
       		  <%
				 }

%>
</table>
            </div>

            <div class="row">
                <div class="features">
                </div><!--/.services-->
            </div><!--/.row-->    
        </div><!--/.container-->
    </section><!--/#feature-->


    <footer id="footer" class="midnight-blue">

    </footer><!--/#footer-->

    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/jquery.isotope.min.js"></script>
    <script src="js/main.js"></script>
    <script src="js/wow.min.js"></script>
</body>
</html>