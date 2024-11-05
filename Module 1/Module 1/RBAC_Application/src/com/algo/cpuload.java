package com.algo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.connection.Dbconn;

import cpu.cpu;

public class cpuload {
public static void memoryload()
{

	Random randomGenerator = new Random();
	Map<Integer,Integer> map=new HashMap<Integer,Integer>();  
	try {
	Connection con = Dbconn.conn();
	Statement st = con.createStatement();
String ip="192.168.2.";
int copy=1,no=1;
int memoryload;
	    for (int idx = 1; idx < 5; ++idx){
	     // int randomInt = randomGenerator.nextInt(100)+1;
	    	String load=cpu.memoryloads(ip);
	    	int randomInt=Integer.valueOf(load);
	      map.put(randomInt,idx);
	     
	      
	    }
	    for(Map.Entry<Integer,Integer> m:map.entrySet()){  
	    if(no<=10)
	      {  
	    	memoryload=(int)m.getKey();// MB load 
	    	dynamicjobordering.memoryloadlist.add(memoryload);
	    	
			no++;
	      }
	   }
	    Collections.sort(dynamicjobordering.memoryloadlist);
	    
	} 
	catch (SQLException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//SimpleDemoGA.GA();
}
}
