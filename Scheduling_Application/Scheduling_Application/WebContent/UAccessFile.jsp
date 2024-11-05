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
    <title>Collaborative Cloud Service </title>
	
	<!-- core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <link href="css/responsive.css" rel="stylesheet">
    
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
                         <li><a href="UserHome.jsp">Home</a></li>
                        <li><a href="UFileDownload.jsp">File Request</a></li>
								<li class="active"><a href="UAccessFile.jsp">File Access</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><%=session.getAttribute("uname")%><i class="fa fa-angle-down"></i></a>
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
                <p class="lead">Welcome to Home, "<%=session.getAttribute("uname")%>"	</p>
                
                <%

			Connection con = Dbconn.conn();
	
	%>
	<table class="table table-responsive table-bordered">
	<tr>
	<td  class="col-md-6"><h3>File Name</h3></td>
	<td class="col-md-2"><h3>Download</h3></td>
			
			
	</tr>
	
				<%
				String username=(String)session.getAttribute("uemailid");
				Statement stat1 = con.createStatement();
				String qrry1 = "select * from access where To_Name='"+username+"' and TPA_Status='Accept'";
				ResultSet rs1= stat1.executeQuery(qrry1);
				while (rs1.next())
				 {
					 
       		 %>	
       		 <tr>
           
              <td> <%= rs1.getString("File_Name") %> </td>
              <td  class="col-md-6" align="left"> 
              <a href="UFileKey.jsp?filenamedb=<%=rs1.getString("File_Name")%>,<%=rs1.getString("From_Name")%>,<%=rs1.getString("id")%>"><input type="button" name="Download" value="Next" class="btn btn-info"></a></td>
               
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