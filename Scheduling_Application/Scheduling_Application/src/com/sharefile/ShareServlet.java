package com.sharefile;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
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
 * Servlet implementation class ShareServlet
 */
@WebServlet("/ShareServlet")
public class ShareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShareServlet() {
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
		PrintWriter pw = response.getWriter();
		String[] username = request.getParameterValues("Checkbox");
		String filename =request.getParameter("filename");
		HttpSession session = request.getSession(false);
		String name=(String)session.getAttribute("emailid");
		//String emailid=(String)session.getAttribute("emailid");
		System.out.println("Email=>"+name+"FileName=>"+filename+"username=>"+username[0]);
		String email="";
		String key=null;
		
		try {
			Connection con = Dbconn.conn();
			
Statement st = con.createStatement();
String query1 = "select * from fileinfo where filename='"+ filename + "' and user='"+name+"'";
ResultSet rs = st.executeQuery(query1);
if(rs.next()) {
	key = rs.getString("File_Key");
}

			for (String s : username) {
				
				try {
					String id = null;
					Statement stt = con.createStatement();
					
				stt.executeUpdate("insert into access(From_Name,File_Name,To_Name,TPA_Status,User_Status) values('"+ name + "' , '"+ filename + "' , '" + s + "', '0','0')");					
				
				} 
				catch (Exception e) 
				{
					System.out.println(e);
				}
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		pw.println("<html><script>alert('File Share Success');</script><body>");
		pw.println("");
		pw.println("</body></html>");
		RequestDispatcher rd = request
				.getRequestDispatcher("/OwnerHome.jsp");
		rd.include(request, response);
	
		}

}
