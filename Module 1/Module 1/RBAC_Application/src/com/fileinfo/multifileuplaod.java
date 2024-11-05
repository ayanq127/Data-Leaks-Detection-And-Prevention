package com.fileinfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.JavaX;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.algo.cpuload;
import com.algo.dynamicjobordering;
//import com.algo.saveserversize;
import com.connection.Dbconn;
import com.csp.EncryptFile;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class multifileuplaod
 */
@WebServlet("/multifileuplaod")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class multifileuplaod extends HttpServlet {
	private static final long serialVersionUID = 1L;
	File finalpath;
	public static String Filename = null,contenttype=null;
	public static Connection con;
	public long startingtime,endingtime,totaltime;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public multifileuplaod() {
        super();
        // 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		PrintWriter pw = response.getWriter();
	HttpSession session = request.getSession(true);
	dynamicjobordering.chunksize.clear();
	dynamicjobordering.filenamelist.clear();
	dynamicjobordering.memoryloadlist.clear();
	dynamicjobordering.filesizelist.clear();
	cpuload.memoryload();
			//System.out.print("\nisMultipart" + isMultipart);
			// process only if its multipart content
			String Username=(String)session.getAttribute("name");
			if (isMultipart)
			{
	
				// Create a factory for disk-based file items
				FileItemFactory factory = new DiskFileItemFactory();
				// Create a new file upload handler
				ServletFileUpload upload = new ServletFileUpload(factory);
			
				try {
					// Parse the request
					int filechunkid=1;
					@SuppressWarnings("unchecked")
					List<FileItem> multiparts = upload.parseRequest(request);
//					System.out.println(multiparts.size());
					if(multiparts.size()==5)// check file select only 4
					{
					
					finalpath = new File(Dbconn.filepath,Username);
					finalpath.mkdir();
					//fileuploadread.multipart(multiparts,finalpath,Username);
					for (FileItem item : multiparts) {
						if (!item.isFormField())
						{
							int filesize=(int)item.getSize();
							Filename = new File(item.getName()).getName();
							contenttype=item.getContentType();
							String[] FileNames=Filename.split("\\.");
							String filename=FileNames[0].toString()+"_"+filechunkid+".txt";			 
							item.write(new File(finalpath + File.separator+ filename));
							dynamicjobordering.chunksize.add(filesize);// one file size
							dynamicjobordering.filenamelist.add(Filename);// file name 
							dynamicjobordering.filesizelist.put(filechunkid,filesize);//file chunk id  and file size
							dynamicjobordering.listfilename.put(filechunkid, filename);// file chunk  id and file name
							System.out.println("Second chunkid :"+filechunkid+"\tFile Size :"+filesize+"\tFile Name=>"+filename);
							filechunkid++;
							@SuppressWarnings("resource")
							BufferedReader br= new BufferedReader(new FileReader(new File(finalpath + File.separator+ filename)));// file read and line by lineJavaX.initComponents();
							StringBuilder sb1=new StringBuilder();
							 String data1;
						        while ((data1 = br.readLine()) != null) {
						                     sb1.append(data1);
						            }
							con = Dbconn.conn();
					        String sql="insert into fileinfo values(?,?,?,?,?)";// all file data and file name
							PreparedStatement prest;
								prest = (PreparedStatement) con.prepareStatement(sql);
								prest.setString(1,Username );
								prest.setString(2,filename);
								prest.setString(3,sb1.toString());
								prest.setString(4,String.valueOf(filesize));
								prest.setString(5,contenttype);
								prest.executeUpdate();
							}
						}// for loop end 
					
			Collections.sort(dynamicjobordering.memoryloadlist);// desc cpu load 
			Collections.sort(dynamicjobordering.chunksize);// desc file size
			int no=1,k=0;
			for(int str: dynamicjobordering.memoryloadlist){
				System.out.println("Hi");
				startingtime=System.currentTimeMillis();
				//first file chunk id return 
				int chunkid=(int)getKeyFromValue(dynamicjobordering.filesizelist,dynamicjobordering.chunksize.get(k));
				String filename=dynamicjobordering.listfilename.get(chunkid);// file chunk id return file name
				 System.out.println("FileName=>"+filename+"\tchunkid :"+chunkid+"\tMemoryLoad :"+str+"\tFile Chunk Size :"+dynamicjobordering.chunksize.get(k));
				File file1=new File(finalpath + File.separator+filename);
				@SuppressWarnings("resource")
				BufferedReader br1= new BufferedReader(new FileReader(file1));// file read and line by line
				StringBuilder sb1=new StringBuilder();
				 String data1;
			        while ((data1 = br1.readLine()) != null) {
			                     sb1.append(data1);
			            }
			        try {
			    
			        	 String vmName="VM"+no;
			        	 long fsize=dynamicjobordering.chunksize.get(k);
			        	// saveserversize.serversize(vmName,fsize);
			        String servername="server"+no;
			        String inputFile=finalpath + File.separator+filename;
			        EncryptFile.Chunk(inputFile,filename,Dbconn.filechunk,Username,servername);
			        String vmname="vm"+no;
			        String Servername="server"+no;
			       
			        System.out.println("Job Number=>"+no+"\tVM Name=>vm"+no+"\tServer Name=>server"+no+"\tFile Name=>"+filename);
						        
			endingtime=System.currentTimeMillis();
			totaltime=endingtime-startingtime;
			String sql2="insert into masterinfo(jobid,user,filename,servername,vmname,Time,downloadtime,algoid) values(?,?,?,?,?,?,?,?)";// server tables add one by one file name
			PreparedStatement p1;
			String download="0";
			String algoid="1";
				p1 = (PreparedStatement) con.prepareStatement(sql2);
				p1.setString(1,String.valueOf(no));
				p1.setString(2,Username);
				p1.setString(3,filename);
				p1.setString(4,Servername);
				p1.setString(5,vmname);
				p1.setString(6,String.valueOf(totaltime));
				p1.setString(7,download);
				p1.setString(8,algoid);
				p1.executeUpdate();
					p1.executeUpdate();
					System.out.println("Job Number=>"+no+"\tStarting Time=>"+startingtime+"\tEnding Time=>"+endingtime+"\tTotal Time=>"+totaltime);
			        } catch (Exception e) {
						System.out.println(e);
					} 
			no++;
			k++;
		   }// for loop end 
					}
					else
					{
						System.out.println("File not Five");
					}
				} catch (Exception e) {
				}
			}//if end ismultipart
			pw.println("<html><script>alert('File Upload Successfully...');</script><body>");
			pw.println("");
			pw.println("</body></html>");
			RequestDispatcher rd = request.getRequestDispatcher("/MultipleFileUpload.jsp");
			rd.include(request, response);
	}//post method end
public static Object getKeyFromValue(Map<Integer, Integer> hm, Object value) {
    for (Object o : hm.keySet()) {
      if (hm.get(o).equals(value)) {
        return o;
      }
    }
    return null;
  }

}
