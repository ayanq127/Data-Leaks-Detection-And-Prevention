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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                        <li class="active"><a href="OwnerHome.jsp">Home</a></li>
                       	<li><a href="MultipleFileUpload.jsp">FileUpload</a></li>
                       		
							<li><a href="FileDownload.jsp">File Download</a></li>
							<li><a href="ShareFileName.jsp">File Share</a></li>
							
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
           
				<form name="shareFile" action="ShareServlet" method="post">
				
				<table class="table table-responsive table-bordered" align="center" border="1" style="width: 100%">
			<tr><td colspan="3" align="center">
					<h2>Select User Name For Share</h2></td>
				</tr>
	       
					<%
						String FileName = request.getParameter("rad1");
					String[] FileNames = request.getParameterValues("rad1");
					for(int i=0;i<FileNames.length;i++)
					{
						Dbconn.filetitle.add(FileNames[i]);
					}
					%>
					
					<tr>
					<td colspan="2"><input type="hidden" name="filename" value="<%= FileName %>" /></td>
					</tr>
					<tr>
					<td class="col-md-2" colspan="2"><label for="txt_Uname"><h3>Select File Name</h3> </label>
					</td>
			<td class="col-md-2" ><label for="txt_Uname"> <h3><%= FileName %></h3> </label></td>
			
			</tr>
			<tr>
			<td class="col-md-2" colspan="2" align="center"><label for="txt_Uname"><h3>User ID </h3></label></td>
			
			<td class="col-md-2" colspan="2" align="center"><label for="txt_Uname"><h3>Select User Name </h3></label></td>
			
		   </tr>
					<%
						session.setAttribute("FileName", FileName);
						 
						ResultSet rs = null;

						PreparedStatement st = null;
						String quer = null;
						

						try {

							Connection con = Dbconn.conn();

							response.setContentType("text/html;charset=UTF-8");
							
							quer = "select * from userregistration";

							st = con.prepareStatement(quer);
							rs = st.executeQuery();

							while (rs.next()) {
								//if (!username.equals(rs.getString("Uname")))
								{
					%>
					<tr>
			<td class="col-md-2" colspan="2" align="center">
					<b><%=rs.getString("IdUser")%></b>
</td>
			<td class="col-md-2" colspan="2" align="left">
					<input type="checkbox" name="Checkbox"
						value="<%=rs.getString("Uemail")%>" /><b><%=rs.getString("Uname")%></b>
</td>
					<%
						}
							}

						} catch (SQLException e) {

							e.printStackTrace();
						}
					%>
					</tr>
					<tr>
			<td class="col-md-2" colspan="3" align="center">
					<input type="submit" name="Submit" value=" Share"  />
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