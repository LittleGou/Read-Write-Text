package com.text;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Scanner;
import com.utils.MyDBUtilsForDbcp;
import com.utils.StreamControl;

/**
 * 
 * @author Gou
 * This file prints out the contents in .txt file
 */
public class ReadText extends StreamControl{
	
	public void readText(String sql, String column){
		try{
			pWriter = new PrintWriter(System.out);
			conn = MyDBUtilsForDbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, this.code.toUpperCase());
			pstmt.setInt(2, this.number);
			rs = pstmt.executeQuery();
			//determine if the comment is null
			if(rs.next() && rs.getCharacterStream(column) != null){
				bReader = new BufferedReader(rs.getCharacterStream(column));
				String temp = null;
				while((temp = bReader.readLine()) != null){
					pWriter.println(temp);
					pWriter.flush();
					
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			System.out.println("");
		}
	}
	public static void main(String[] args) {
		System.out.println("\n|| Read Text ||\n");
		ReadText rt = new ReadText();
		//Scan the exam code and question number that you wish to retrieve
		Scanner scan = new Scanner(System.in);
		System.out.println("Please input exam code:");
		rt.setCode(scan.next());
		System.out.println("Please input question number:");
		rt.setNumber(scan.nextInt());
		scan.close();
		scan = null;
		System.out.println("\n---------------------------------");
		
		//Write SQL Query
		String sql = "select * from ocjp where code = ? and number = ?";
		
		//Connect and Retrieve
		rt.readText(sql, "question");
		rt.readText(sql, "choice");
		rt.readText(sql, "comment");
		
		rt.destoryStream();

	}

}
