package com.algo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class dynamicjobordering {
	public static ArrayList<String> chunk = new ArrayList<>();
	
	public static ArrayList<String>filenamelist = new ArrayList<>();
	public static ArrayList<Integer> chunkId = new ArrayList<>();
	public static ArrayList<Integer> chunksize = new ArrayList<>();
	public static ArrayList<Integer> memoryloadlist = new ArrayList<Integer>();
	public static Map<Integer,Integer> filesizelist=new HashMap<Integer,Integer>(); 
	public static Map<Integer,String> listfilename=new HashMap<Integer,String>(); 
		public static Object getKeyFromValue(Map<Integer, Integer> hm, Object value) {
        for (Object o : hm.keySet()) {
          if (hm.get(o).equals(value)) {
            return o;
          }
        }
        return null;
      }
//	public static Object getKeyFromValue(Map hm, Object value) {
//        for (Object o : hm.keySet()) {
//          if (hm.get(o).equals(value)) {
//            return o;
//          }
//        }
//        return null;
//      }
}
