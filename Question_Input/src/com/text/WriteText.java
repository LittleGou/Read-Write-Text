package com.text;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import com.utils.MyDBUtilsForDbcp;
import com.utils.StreamControl;

/**
 * @author Gou
 * This file writes the .txt into database
 */
public class WriteText extends StreamControl{
	
	public void writeText(String sql, File src){
		try{
			reader = new InputStreamReader(new FileInputStream(src));
			//Update Question
			conn = MyDBUtilsForDbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setCharacterStream(1, reader);
			pstmt.setString(2, this.code.toUpperCase());
			pstmt.setInt(3, this.number);
			int result = pstmt.executeUpdate();
			if(result > 0){
				System.out.println("Update Question Successfully!");
			}else{
				System.out.println("Update Question Non-Successfully!");
			}		
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("\n|| Write Text ||\n");
		WriteText wt = new WriteText();
		// Set up the scanner to scan the file name
		Scanner scan = new Scanner(System.in);
		System.out.println("Please input exam code:");
		wt.setCode(scan.next());
		System.out.println("Please input question number:");
		wt.setNumber(scan.nextInt());
		scan.close();
		scan = null;;
		
		// Specify the file name 
		File src = new File("C://Users//Gou//Desktop//OCJP//" + "e" + wt.getCode() + "_q" + wt.getNumber() + ".txt");
		File src2 = new File("C://Users//Gou//Desktop//OCJP//" + "e" + wt.getCode() + "_q" + wt.getNumber() + "_a" + ".txt");
		File src3 = new File("C://Users//Gou//Desktop//OCJP//" + "e" + wt.getCode() + "_q" + wt.getNumber() + "_comment" + ".txt");
		// Initialize JDBC objects
		System.out.println(src.toString());
		
		// Write SQL query
		String sql = "update ocjp set question = ? where code = ? and number = ?";
		String sql2 = "update ocjp set choice = ? where code = ? and number = ?";
		
		// Connect and Update
		wt.writeText(sql, src);
		wt.writeText(sql2, src2);
		
		// Extra Concern
		if(src3.exists()){
			String sql3 = "update ocjp set comment = ? where code = ? and number = ?";
			wt.writeText(sql3, src3);
		}
		
		wt.destoryStream();
	}

}
