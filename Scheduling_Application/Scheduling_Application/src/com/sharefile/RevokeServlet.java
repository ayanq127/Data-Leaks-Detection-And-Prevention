package com.sharefile;

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
 * Servlet implementation class RevokeServlet
 */
@WebServlet("/RevokeServlet")
public class RevokeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RevokeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);  
		PrintWriter pw = response.getWriter();
		String FileName=(String)session.getAttribute("FileName");
		String username=(String)session.getAttribute("emailid");
		String[] Checkbox = request.getParameterValues("Checkbox");

		String Checked[] = Checkbox;
		int i = 0;
		try{
			
			//String PriKey = dbServlet.keyData();
			Connection con = Dbconn.conn();
			Statement st = con.createStatement();
		for (String s : Checkbox) {
			Checked[i] = s;
			System.out.println("Select User Name=>" + s+"File Name=>"+FileName);
			
			i++;
			    st.executeUpdate("delete from access where From_Name='"+username+"' and To_Name='"+s+"'and File_Name='"+FileName+"'");
		    }
		
		pw.println("<html><script>alert('Revoke File Success');</script><body>");
		pw.println("");
		pw.println("</body></html>");
		
	    RequestDispatcher rd = request.getRequestDispatcher("/OwnerHome.jsp");
		rd.include(request, response); 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
