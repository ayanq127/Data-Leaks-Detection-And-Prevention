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

	public static String filepath = "F:/ME2019-2020/VJTI/Data/";

	public static String filechunk = "F:/ME2019-2020/VJTI/Data/split/";

	public static String Output = "F:/ME2019-2020/VJTI/Data/Output/";
	public static String localpath = "F:/ME2019-2020/VJTI/Data/Output/Local/";
	public Dbconn() throws SQLException {
		super();
	}

	public static Connection conn() throws SQLException, ClassNotFoundException {
		Connection con;

		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(
				"jdbc:mysql://localhost/vjtiraacs", "root",
				"admin");
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
					"root", "admin");
		}
		if(flag==2)
		{
			con = DriverManager.getConnection("jdbc:mysql://localhost/" + Db,
					"root", "admin");
		}
		if(flag==0)
		{
			con = DriverManager.getConnection("jdbc:mysql://localhost/" + Db,
					"root", "admin");
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
