<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.Format"%>
<%@page import="java.sql.ResultSet"%><%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.*"%>
<%@page import="com.connection.*"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Random" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Dynamic job ordering and data recovery using cauchy coding approach for cloud storage environment. </title>
	
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
</head><!--/head-->

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
						<a class="navbar-brand " href="#">Dynamic job ordering and data recovery using cauchy coding approach for cloud storage environment. </a>                
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
            </div><!--/.container-->
        </nav><!--/nav-->
		
            </div><!--/.container-->
        		
    </header><!--/header-->

 
    <section id="feature" >
        <div class="container">
           <div class="center wow fadeInDown">
                <h2>HOME PAGE</h2>
                <p class="lead">Welcome to Home, "<%=session.getAttribute("name")%>"	</p>
            </div>
<%
String strFileName = request.getParameter("filenamedb");
String arr[]=strFileName.split(",");
String username=(String)session.getAttribute("name");
String filename= arr[0].toString();
System.out.println("file Name===>" + arr[0]+"Server Name=>"+arr[1]+"UserName=>"+username);
Connection con = Dbconn.conn1(arr[1].toString());
Statement st = con.createStatement();
Statement st2 = con.createStatement();
long first,second;
Random r=new Random();
  long no1=r.nextInt(4)+1;// byte   
  long no2=r.nextInt(4)+1;// byte   
  if(no1==no2)
  {
	  
  no2=r.nextInt(4)+1;
  first=no1;
  second=no2;
  System.out.println("No1=>"+first+"New Second=>"+second);
  }
  else
  {
	  first=no1;
	  second=no2;
  System.out.println("No1=>"+first+"Second=>"+second);
  }
	
	String str="delete from csp"+first+ " where user='"+username+"' and filename='"+filename+"'";
	 st.executeUpdate(str);	
	 ///
	 String str2="delete from csp"+second+ " where user='"+username+"' and filename='"+filename+"'";
	 st2.executeUpdate(str2);	
	 String s1="SubServer"+first,s2="SubSerer"+second;
	 session.setAttribute("first",s1);
	 session.setAttribute("second",s2);
	 session.setAttribute("Server",arr[1]);
response.sendRedirect("FileDownload.jsp");

%>
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