package com.tpa;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.Dbconn;
import com.sharefile.SendMail;
/**
 * Servlet implementation class tpaaccept
 */
@WebServlet("/tpaaccept")
public class tpaaccept extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public tpaaccept() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		
		String name = request.getParameter("filenamedb");
		
		String arr[]=name.split(",");
		String id=arr[0].toString();
		String From_Name=arr[1].toString();
		String filename=arr[2].toString();
		
		String emailid=arr[3].toString();
		String msg=arr[4].toString();
		System.out.println("IDuser:="+id+"Fromuser:="+From_Name+"\tFileName:="+filename+"\ttouser:="+emailid+"\t msg"+msg);
		
		Connection con;
		try {
			con=Dbconn.conn();
		
		Statement st=con.createStatement();
		Statement st1=con.createStatement();
		
		if(msg.equals("Accept"))
		{
		String status="Accept";
		String qryupdate="update access set User_Status='"+status+"',TPA_Status='"+status+"' where From_Name='"+From_Name+"' and File_Name='"+filename+"' and To_Name='"+emailid+"' and id='"+id+"'";
		st.executeUpdate(qryupdate);
		String key="";
		ResultSet rs=st1.executeQuery("select * from fileinfo where user='"+From_Name+"' and filename='"+filename+"'");
		if(rs.next())
		{
			
			key=rs.getString("File_Key");
		}
		SendMail.mailSendaccept(emailid,key);
		pw.println("<script> alert('Accepted Successfully');</script>");
		}
		else
		{
			String status="Reject";
			String qryupdate="update access set User_Status='"+status+"',TPA_Status='"+status+"' where From_Name='"+From_Name+"' and File_Name='"+filename+"' and To_Name='"+emailid+"' and id='"+id+"'";
		
			st.executeUpdate(qryupdate);
			pw.println("<script> alert('Reject Successfully');</script>");
			
		}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/TPAaccept.jsp");
		rd.include(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
