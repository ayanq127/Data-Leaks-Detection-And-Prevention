/**
 * Command-line program encodes one file using Reed-Solomon 4+2.
 *
 * Copyright 2015, Backblaze, Inc.
 */

package com.csp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.Random;

import com.algo.AES;
import com.connection.Dbconn;
public class EncryptFile {

    public static final int DATA_Chunk = 4;
    public static final int PAIR_SHARDS = 2;
    public static final int TOTAL_Chunk = 6;

    public static final int BYTES_IN = 4;
    public static long totaltime1, totaltime2, totaltime3, totaltime4;
	public static int matrix[][], matrix1[][], matrix2[][], matrix3[][];

    @SuppressWarnings("resource")
	public static void Chunk(String inputF,String filename,String filesplit,String Username,String servername)
    {
    	
    	final File inputFile = new File(inputF);
       

        final int fileSize = (int) inputFile.length();

        final int storedSize = fileSize + BYTES_IN;
        final int shardSize = (storedSize + DATA_Chunk - 1) / DATA_Chunk;
        final int bufferSize = shardSize * DATA_Chunk;
        final byte [] allBytes = new byte[bufferSize];
        ByteBuffer.wrap(allBytes).putInt(fileSize);
        InputStream in;
		try {
			in = new FileInputStream(inputFile);
			int bytesRead = in.read(allBytes, BYTES_IN, fileSize);
	        if (bytesRead != fileSize) {
	            throw new IOException("not enough bytes read");
	        }
	        in.close();
		} catch (Exception e1) {
			// 
			e1.printStackTrace();
		}
        

        // Make the buffers to hold the shards.
        byte [] [] chunk = new byte [TOTAL_Chunk] [shardSize];

        // Fill in the data shards
        for (int i = 0; i < DATA_Chunk; i++) {
            System.arraycopy(allBytes, i * shardSize, chunk[i], 0, shardSize);
        }

        // Use  to calculate the parity.
        Secureconding reedSolomon = new Secureconding(DATA_Chunk, PAIR_SHARDS);
        reedSolomon.encodeParity(chunk, 0, shardSize);
//String filename="title.txt";
int no=1;
        // Write out the resulting files.

        for (int i = 0; i < TOTAL_Chunk; i++) {
        	
        	String str;
			try {
				if(i<=3)
	        	{
					
					servername="server"+no;
					System.out.println("Server"+servername);
				str = new String(chunk[i], "UTF-8");
				Connection conn = Dbconn.conn1(servername);
				//Statement StInsert = conn.createStatement();
				try {
					
					//long filesize=str.length();
					String keys=keyData().toString();
					byte[] key=keys.getBytes();
					 byte[] input=str.toString().getBytes();
					
					 byte[] enc=AES.encrypt(input, key);
					//StInsert.executeUpdate("insert into csp"+no+" values('"+Username+"','" + str + "','"+filename+"','"+filesize+"')");
					String sql1 = "INSERT INTO csp values (?,?,?,?)";
					PreparedStatement stt = conn.prepareStatement(sql1);
					stt.setString(1, Username);
					stt.setBytes(2, enc);
					stt.setString(3, filename);
					stt.setString(4,keys);
					
				
					stt.executeUpdate();
					System.out.println("File"+Dbconn.localpath+filename+"_"+i+".txt");
					File outputFile = new File(Dbconn.localpath+filename+"_"+i+".txt");
		             OutputStream out = new FileOutputStream(outputFile);
		             out.write(enc);
		             out.close();
					
					
					
					
				} catch (Exception e) {
					// 
					e.printStackTrace();
				}

	        	
        	no++;
        	
	        	}
        	else
        	{
        	 File outputFile = new File(filesplit+filename+"_"+i+".txt");
             OutputStream out = new FileOutputStream(outputFile);
             out.write(chunk[i]);
             out.close();
             // local path
             File outputFile1 = new File(Dbconn.localpath+filename+"_"+i+".txt");
             OutputStream out1 = new FileOutputStream(outputFile1);
             out1.write(chunk[i]);
             out1.close();
             
             
        	}
			
        	} catch (ClassNotFoundException e) {
				// 
				e.printStackTrace();
			} catch (SQLException e) {
				// 
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// 
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// 
				e.printStackTrace();
			} catch (IOException e) {
				// 
				e.printStackTrace();
			}	
        }
        
    	
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
		

	}
  }
