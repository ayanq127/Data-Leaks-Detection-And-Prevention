package com.fileinfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.Dbconn;
/**
 * Servlet implementation class deletefile
 */
@WebServlet("/deletefile")
public class deletefile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deletefile() {
        super();
        // 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter pw = response.getWriter();
		 String username=(String)session.getAttribute("name");

		System.out.print("UserName:-" + username);
		String input = request.getParameter("filenamedb");
	
		String arr[]=input.split(",");
		String filename=arr[0].toString();
		System.out.println("file Name===>" + arr[0]+"Server Name=>"+arr[1]+"UserName=>"+username);
		try {

			Connection conn = Dbconn.conn(); // connection to the database

			// queries the database
			String sql = "Delete FROM fileinfo WHERE user = ? and filename =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, filename);
			 statement.executeUpdate();
			 Connection con = Dbconn.conn1(arr[1].toString());
			 Statement st = con.createStatement();
			 for (int i = 1; i <= 4; i++) {
				 String str="delete from csp"+i+ " where user='"+username+"' and filename='"+filename+"'";
				 st.executeUpdate(str);	
				
			}
			 Statement sts = conn.createStatement();
			 String str="Delete FROM masterinfo where user='"+username+"' and filename='"+filename+"'";
			 sts.executeUpdate(str);	
			 pw.println("<html><script>alert('File Delete Successfully...');</script><body>");
				pw.println("");
				pw.println("</body></html>");
			RequestDispatcher rd = request
					.getRequestDispatcher("/FileDownload.jsp");

			rd.include(request, response);
		} catch (Exception e) {
			System.out.print(" " + e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
	}

}
