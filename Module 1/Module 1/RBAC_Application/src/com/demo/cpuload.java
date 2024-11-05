package com.demo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class cpuload {
public static void memoryload()
{

	Random randomGenerator = new Random();
	Map<Integer,Integer> map=new HashMap<Integer,Integer>();  
        String ip="192.168.2.";
    int copy=1,no=1;
    int memoryload;
    for (int idx = 1; idx <= 4; ++idx){
        int randomInt = randomGenerator.nextInt(100)+1;
        map.put(randomInt,idx);
      //  Individual.l.add(randomInt);
       // System.out.println("randomInt"+randomInt);
        
        
    }
}
}
