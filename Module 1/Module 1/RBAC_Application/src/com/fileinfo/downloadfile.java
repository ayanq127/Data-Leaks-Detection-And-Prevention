package com.fileinfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.connection.Dbconn;

public class downloadfile {

public static String FileDownload(String Filename)
{
	StringBuilder sb = new StringBuilder();
	int id = 1;
	try {
		Connection con;
		//BufferedWriter out = null;
		Map<Integer, String> map = new HashMap<Integer, String>();
		con = Dbconn.conn();
		Statement stRegister = con.createStatement();
		Statement st = con.createStatement();
		ResultSet rsLogin;
		ResultSet rs;
		String data;
		int chunkid;
		String[] FileNames = Filename.split("\\.");
		for (int i = 0; i < 4; i++) {
			rsLogin = stRegister.executeQuery("select * from vm" + id + "");
			if (rsLogin.next()) {
				chunkid = Integer.parseInt(rsLogin.getString("ChunkID"));
				data = rsLogin.getString("Data");
				String str = FileNames[0] + "_" + chunkid + ".txt";
				rs = st.executeQuery("select * from vm" + id
						+ " where ChunkID='" + chunkid + "' and FileName='"
						+ str + "'");
				if (rs.next()) {
					chunkid = Integer.parseInt(rs.getString("ChunkID"));
					data = rs.getString("Data");
					 System.out.println("FileName:" + str + "\nVM :" + id
					 + "\nchunkid:" + chunkid + "\nData=>" + data);
					 System.out.println("======================");

					map.put(chunkid, data);
				}// second if end
			}// first if end
			id++;
		}// for loop end

		
		for (Entry<Integer, String> entry : map.entrySet()) {
			//int key = entry.getKey();
			String values = entry.getValue();
			sb.append(values);
			}
//		FileWriter fstream1 = new FileWriter(
//				"F:\\ME2017-2018\\VidyMECOEP\\ProjectCode\\ChunkFileData\\"
//						+ Filename); //
//		// Destination File Location
//		out = new BufferedWriter(fstream1);
//		out.write(sb.toString());
//		out.close();
		//
	} catch (SQLException | ClassNotFoundException ex) {
	}

	
	return sb.toString();
}
	
	
	
	
	public static void main(String[] args) {
		
	}

}
