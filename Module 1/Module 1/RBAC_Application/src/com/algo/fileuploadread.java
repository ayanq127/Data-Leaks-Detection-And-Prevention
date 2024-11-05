package com.algo;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import com.connection.Dbconn;

public class fileuploadread {
	public static String Filename = null,contenttype=null;
	 public static Connection con;
	public static int multipart(List<FileItem> multiparts,File finalpath,String Username)
	{ int y=0;
	int filechunkid=1;
	try {
	for (FileItem item : multiparts) {
		if (!item.isFormField()) {
			int filesize=(int)item.getSize();
			Filename = new File(item.getName()).getName();
			contenttype=item.getContentType();
			String[] FileNames=Filename.split("\\.");
			String filename=FileNames[0].toString()+"_"+filechunkid+".txt";
			con=Dbconn.conn();
			Statement stRegister=con.createStatement();
			ResultSet rsLogin;
			rsLogin=stRegister.executeQuery("select * from fileinfo where user='" +Username+ "' and filename='"+filename+"'");
			if(rsLogin.next())
			{
				
			}
			else
			{
			item.write(new File(finalpath + File.separator+ filename));
			dynamicjobordering.chunksize.add(filesize);// one file size
			dynamicjobordering.filenamelist.add(Filename);// file name 
			dynamicjobordering.filesizelist.put(filechunkid,filesize);//file chunk id  and file size
			dynamicjobordering.listfilename.put(filechunkid, filename);// file chunk  id and file name
			System.out.println("First chunkid :"+filechunkid+"\tFile Chunk Size :"+filesize+"\tFile Name=>"+Filename);
			filechunkid++;
			}	
		}// if end 
	}// for loop end 
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return y;
		
	}

}
