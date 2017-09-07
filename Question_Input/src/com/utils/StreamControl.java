package com.utils;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StreamControl {
	
	protected String code;
	protected Integer number;
	
	public String getCode() {return code;}
	public void setCode(String code) {this.code = code;}
	public Integer getNumber() {return number;}
	public void setNumber(Integer number) {this.number = number;}
	
	protected Connection conn;
	protected BufferedReader bReader;
	protected Reader reader;
	protected PrintWriter pWriter;
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	
	protected void destoryStream(){
		try{
			if(rs != null){rs.close(); rs = null;}
			if(pstmt != null){pstmt.close(); pstmt = null;}
			if(pWriter != null){pWriter.close(); pWriter = null;}
			if(bReader != null){bReader.close(); bReader = null;}
			if(reader != null){reader.close(); reader = null;}
			//Do not recycle conn so other programs won't lose databse connection
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
}
