package com.fileData;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.algo.AES;
import com.connection.Dbconn;
import com.csp.DecryptedFile;

public class downloads {
	public static String datafinal = null;

	public static String filedownload(String username, String filename) {
		long stime, etime, ttime = 0, ttime1 = 0, ttime2 = 0, ttime3 = 0, ttime4 = 0;
		try {
			String servername=null;
			int i = 0, t = 0;
			stime = System.currentTimeMillis();
			//Connection conn = Dbconn.conn1(servername);
			
			String key = null;
			byte[] bytes = null;
			for (int k = 1; k < 5; k++) {
				servername="server"+k;
				Connection conn = Dbconn.conn1(servername);
				Statement StInsert = conn.createStatement();
				ResultSet rs = StInsert.executeQuery("select * from csp where user='" + username + "' and filename='"
						+ filename + "' ");
				if (!rs.isBeforeFirst()) {
					t++;
					System.out.println("No data csp" + k);
				} else {
					while (rs.next()) {
						t++;
						key = rs.getString("file_key");
						bytes = rs.getBytes("part");
						byte[] keys = key.getBytes();
						byte[] dec = AES.decrypt(bytes, keys);
						String origional = new String(dec);
						filename = rs.getString("filename");
						FileWriter fw = new FileWriter(Dbconn.filechunk
								+ filename + "_" + i + ".txt");
						fw.write(origional);
						fw.close();
						
						System.out.println("Data csp" + k);
					}
					// fw.close();
				}
				i++;
			}
			datafinal = DecryptedFile.download(filename);
			etime = System.currentTimeMillis();
			ttime = etime - stime;
		} catch (ClassNotFoundException e) {
			//
			e.printStackTrace();
		} catch (SQLException e) {
			//
			e.printStackTrace();
		} catch (IOException e) {
			//
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return datafinal;
	}

	public static void main(String[] args) {

	}

}
