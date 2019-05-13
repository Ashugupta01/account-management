package com.authenticate;

import java.sql.*;

public class AuthenticationServlet 
{
	public static boolean validate(String username, String password)
	{
		boolean status = false;
		
		String query;
		String url = "jdbc:mysql://localhost:3306/details";
		String uname = "root";
		String passw = "password17";
		
		try{Class.forName("com.mysql.jdbc.Driver");
		
		Connection con;
		con = DriverManager.getConnection(url,uname,passw);
		
		PreparedStatement pstmt;
		
		query = "select * from authenticate where username=? and password=?";
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, username); 
		pstmt.setString(2, password);
	
		ResultSet rs = pstmt.executeQuery();
		status = rs.next();
		
		}catch (Exception e) {}
		
		return status;
	}
}
