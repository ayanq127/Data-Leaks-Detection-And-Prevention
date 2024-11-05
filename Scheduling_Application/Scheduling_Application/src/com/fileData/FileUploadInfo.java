package com.fileData;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.algo.AES;
import com.algo.HashGenerationException;
import com.algo.HashGeneratorUtils;
import com.connection.Dbconn;
import com.csp.EncryptFile;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class FileUploadInfo
 */
@WebServlet("/FileUploadInfo")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class FileUploadInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Connection con;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadInfo() {
        super();
        // TODO Auto-generated constructor stub
    }
    String getFileName(Part filePart) {
		for (String cd : filePart.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String fileName = cd.substring(cd.indexOf('=') + 1).trim()
						.replace("\"", "");
				return fileName.substring(fileName.lastIndexOf('/') + 1)
						.substring(fileName.lastIndexOf('\\') + 1);
			}
		}
		return null;

	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part filePart = request.getPart("txt_search");
		HttpSession session=request.getSession(true);
		String Username=(String)session.getAttribute("emailid");
		PrintWriter pw=response.getWriter();
		String fileName = getFileName(filePart);
		String contenttype =filePart.getContentType();
		InputStream inputStream = null;
		long size = filePart.getSize();
		long stratingtime,endtime, totaltime;
		System.out.println("User Name===>" +Username);
		System.out.println("FileName===>" + fileName);
		System.out.println("ContentType===>" + contenttype);
		System.out.println("File Size===>" + size);
		try {
			
	    con=Dbconn.conn();
		Statement stRegister=con.createStatement();
		ResultSet rsLogin;
		rsLogin=stRegister.executeQuery("select * from fileinfo where user='" +Username+ "' and filename='"+fileName+"'");
		if(rsLogin.next())
		{
			pw.println("<html><script>alert('File Already Exists');</script><body>");
			pw.println("");
			pw.println("</body></html>");
		}
		else
		{
			stratingtime=System.currentTimeMillis();
			
		inputStream = filePart.getInputStream();
		BufferedReader br;
		StringBuilder sb = new StringBuilder();
		String line;
		br = new BufferedReader(new InputStreamReader(inputStream));
		while ((line = br.readLine()) != null) {
			sb.append(line).append("\n");
		}// end while loop
		System.out.println("Data===>" + sb.toString());
		String plain1 = sb.toString();
		String keys=keyData().toString();
		byte[] key=keys.getBytes();
		 byte[] input=plain1.toString().getBytes();
		
		 byte[] enc=AES.encrypt(input, key);
		String sql="insert into fileinfo(user,filename,data,Size,contenttype,File_Key) values(?,?,?,?,?,?)";
		PreparedStatement prest;
			prest = (PreparedStatement) con.prepareStatement(sql);
			prest.setString(1,Username );
			prest.setString(2,fileName);
			prest.setBytes(3, enc);
			prest.setString(4,String.valueOf(size));
			prest.setString(5,contenttype);
			prest.setBytes(6, key);
			prest.executeUpdate();
			
			String datainfo=String.valueOf(enc);
			Date day=new Date();
			
			HashGeneratorUtils generatorUtils=new HashGeneratorUtils();
			String File_Data_Info=generatorUtils.generateSHA256(datainfo);
			String hash_Key=generatorUtils.generateSHA256(keys);
			String sql1="insert into tblshainfo(UserName,File_Name,File_Data_Info,hash_Key,Update_Time) values(?,?,?,?,?)";
			PreparedStatement pt;
				pt = (PreparedStatement) con.prepareStatement(sql1);
				pt.setString(1,Username);
				pt.setString(2,fileName);
				pt.setString(3,File_Data_Info);
				pt.setString(4, hash_Key);
				pt.setString(5,String.valueOf(day));
				
				pt.executeUpdate();
			endtime=System.currentTimeMillis();
			totaltime=endtime-stratingtime;
			
			FileWriter(plain1,fileName,Username);
			System.out.println("StratTime=>"+stratingtime+"\tEnd Time=>"+endtime+"\tTime=>"+totaltime);
			
			pw.println("<script> alert('File Upload Successfuly');</script>");
		}// end else 
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (HashGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/FileUpload.jsp");
		rd.include(request, response);
	}
	public static void  FileWriter(String data,String filename,String Username) {

		FileWriter fw;
		String servername=null;
		String inputFile=Dbconn.filepath+filename;
		try {
			
			fw = new FileWriter(Dbconn.filepath+filename);
			fw.write(data);
			fw.close();
		
		} catch (IOException e) {
			// 
			e.printStackTrace();
		}
		EncryptFile.Chunk(inputFile,filename,Dbconn.filechunk,Username,servername);

	}
	public static String keyData() {
		StringBuilder ss = new StringBuilder();
		Random r = new Random();
		char ch;

		for (int i = 0; i < 5; i++) {
			ch = (char) (Math.floor(26 * r.nextDouble() + 65));
			ss.append(ch);
		}

		return ss.toString();

	}
}
