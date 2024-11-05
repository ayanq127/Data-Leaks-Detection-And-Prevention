package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Dbconn {
	public static ArrayList<String> ResList = new ArrayList<String>();
	public static ArrayList<String> filetitle = new ArrayList<String>();
	public static ArrayList<String> offline2list = new ArrayList<String>();
	public static int hitCount;
	public static String data = "";

	public static String filepath = "K:\\DATA\\TechM 2020\\105 Sayali Gite\\Project Modules\\Data\\";

	public static String filechunk = "K:\\DATA\\TechM 2020\\105 Sayali Gite\\Project Modules\\Data\\split\\";

	public static String Output = "K:\\DATA\\TechM 2020\\105 Sayali Gite\\Project Modules\\Data\\Output\\";

	public Dbconn() throws SQLException {
		super();
	}

	public static Connection conn() throws SQLException, ClassNotFoundException {
		Connection con;

		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(
				"jdbc:mysql://localhost/rbac_db", "root",
				"");
		return (con);
	}
	public static Connection conn1(String DbName) throws SQLException,
	ClassNotFoundException {

		String Db = "dynamicResource";
		int flag = 0;
		Connection con=null;
		Class.forName("com.mysql.jdbc.Driver");
		if (DbName.equals("server1")) {
			flag = 1;
			Db = "server1";
		} else if (DbName.equals("server2")) {
			flag = 2;
			Db = "server2";
		} else if (DbName.equals("server3")) {
			flag=0;
			Db = "server3";
		} else if (DbName.equals("server4")) {
			flag=0;
			Db = "server4";
		}
		
		if (flag == 1) {

			con = DriverManager.getConnection("jdbc:mysql://localhost/" + Db,
					"root", "");
		}
		if(flag==2)
		{
			con = DriverManager.getConnection("jdbc:mysql://localhost/" + Db,
					"root", "");
		}
		if(flag==0)
		{
			con = DriverManager.getConnection("jdbc:mysql://localhost/" + Db,
					"root", "");
		}
		return (con);

}
	
	
	
//	public static Connection conn1(String DbName) throws SQLException,
//			ClassNotFoundException {
//
//		String Db = "dynamicResource";
//
//		if (DbName.equals("server1")) {
//			Db = "server1";
//		} else if (DbName.equals("server2")) {
//			Db = "server2";
//		} else if (DbName.equals("server3")) {
//			Db = "server3";
//		} else if (DbName.equals("server4")) {
//			Db = "server4";
//		}
//		//System.out.println("Server Selected " + Db);
//		Connection con;
//		Class.forName("com.mysql.jdbc.Driver");
//		con = DriverManager.getConnection("jdbc:mysql://localhost/" + Db,
//				"root", "admin");
//
//		return (con);
//
//	}

}
