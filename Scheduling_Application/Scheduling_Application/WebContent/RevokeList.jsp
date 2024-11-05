
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.*"%>
<%@page import="com.connection.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Secure Role Base Access Control in Cloud Environemnt  </title>
	
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
                        <li ><a href="OwnerHome.jsp">Home</a></li>
                       	<li><a href="MultipleFileUpload.jsp">FileUpload</a></li>
                       		
							<li><a href="FileDownload.jsp">File Download</a></li>
							<li><a href="ShareFileName.jsp">File Share</a></li>
							<li><a href="RevokeList.jsp">Revoke</a></li>
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
          <form action="RevokeFile.jsp" name="frm"  onSubmit="return validate();">
<table class="table table-responsive table-bordered"
										style="width: 75%" border="1">
										<tr>
											<td colspan="2" align="center">
												<h2>Select File For Share</h2>
											</td>
										</tr>
<%

String USR=(String)session.getAttribute("emailid");

ResultSet rs=null;


PreparedStatement st=null;
String quer=null;
 
try
{

Connection con=Dbconn.conn();



response.setContentType("text/html;charset=UTF-8");
quer="select * from access where From_Name='"+USR+"'" ;
	   st = con.prepareStatement(quer);
	   rs = st.executeQuery();
	
	 while(rs.next()){
		 String FILES=rs.getString(3);
%>	 

	 
	 <tr>
											<td class="col-md-2"><input type="radio" name="rad1" value="<%=FILES%>" /><%=FILES %></td>
											</tr>


<% 

	 }

}
catch (SQLException e) {

 e.printStackTrace();
}
      

 %>       
      
<tr>
											<td class="col-md-2" colspan="2" align="center"><input
												type="submit" name="ok" value="Next" style="width: 100px;">
											</td>
										</tr>
									</table>




 </form>

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