package com.fileData;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import com.algo.AES;
import com.connection.Dbconn;
/**
 * Servlet implementation class Ofiledownload
 */
@WebServlet("/Ofiledownload")
public class Ofiledownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ofiledownload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream op = response.getOutputStream();
		HttpSession session=request.getSession(true);
		
		String fileName = request.getParameter("filenamedb");
		
		String username=(String)session.getAttribute("emailid");
		
		String datafinal = null;
		try {
			StringBuilder sb=new StringBuilder();
			Connection conn = Dbconn.conn();
			Statement StInsert = conn.createStatement();
			Statement st=conn.createStatement();
			ResultSet rsDb=st.executeQuery("select * from fileinfo where filename='"+fileName+"'");
			String key = null;
			byte[] bytes = null;
			while(rsDb.next())
			{
			 key=rsDb.getString("File_Key");
			 bytes = rsDb.getBytes("data");
			}
			
			byte[] keys = key.getBytes();
			byte[] dec = AES.decrypt(bytes, keys);
		String	origional = new String(dec);
			datafinal=downloads.filedownload(username,fileName);
		
		
		response.setHeader("Content-Type", "application/octet-stream");
		response.setContentType("text/plain");
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ fileName + "\"");
		op.println(origional);
		op.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
