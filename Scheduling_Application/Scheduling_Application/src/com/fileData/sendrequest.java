package com.fileData;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.Dbconn;

/**
 * Servlet implementation class sendrequest
 */
@WebServlet("/sendrequest")
public class sendrequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sendrequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession(true);
		//String fromuser,filename,usern;
		String name = request.getParameter("filenamedb");
		
		String arr[]=name.split(",");
		String From_Name=arr[1].toString();
		String filename=arr[0].toString();
		String id=arr[2].toString();
		String emailid=(String)session.getAttribute("uemailid");
		
	
		try 
		{
			Connection con;
			PreparedStatement pt;
			con=Dbconn.conn();
			
			Statement stavailable = con.createStatement();
			ResultSet rsavailable = stavailable
					.executeQuery("select * from keyrequest where fromuser='"+From_Name+"' and filename='"+filename+"' and touser='"+emailid+"' and id='"+id+"'");
		if(rsavailable.next()) {
			PrintWriter out1 = null;
				response.setContentType("text/html;charset=UTF-8");
				out1 = response.getWriter();
				out1.println("<html><script>alert('Request Already Send');</script><body>");
				out1.println("");
				out1.println("</body></html>");
			}
			
		else
		{
			
			System.out.println("IDuser:="+id+"Fromuser:="+From_Name+"\tFileName:="+filename+"\ttouser:="+emailid);
			Statement st=con.createStatement();
			String status="Send";
			String qryupdate="update access set User_Status='Send' where From_Name='"+From_Name+"' and File_Name='"+filename+"' and To_Name='"+emailid+"' and id='"+id+"'";
		
			st.executeUpdate(qryupdate);
			String query="INSERT INTO keyrequest (id,fromuser, filename,touser,status) values (?,?,?,?,?)";
			pt=con.prepareStatement(query);
			pt.setString(1,id);
			pt.setString(2,From_Name);
			pt.setString(3,filename);
			pt.setString(4, emailid);
			pt.setString(5, status);
			 pt.executeUpdate();
			
			out.println("<script type=\"text/javascript\">");  
			out.println("alert('Key Request Send Successfully');");  
			out.println("</script>");    
			
		}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/UFileDownload.jsp");
		rd.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
