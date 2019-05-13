package com.authenticate;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignupServlet extends HttpServlet	{
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String password = req.getParameter("password");
		String cpassword = req.getParameter("cpassword");
		
		res.setContentType("text/html");  
		
		if(username.length()>15 || username.length()<5)	{
			
			PrintWriter out = res.getWriter();
			out.println("<font color='red' face='Comic Sans MS'>User name should be between 5 to 15 letters long. Please try again!!</font>");
			RequestDispatcher rd = req.getRequestDispatcher("signup.html");
			rd.include(req,res);
		}
		
		else if(email.length()>50 || email.indexOf('@')==-1)
		{
			PrintWriter out = res.getWriter();
			out.println("<font color='red' face='Comic Sans MS'>Valid E-mail ids should contain @ and should have less than 50 characters. Please try again!!</font>");
			RequestDispatcher rd = req.getRequestDispatcher("signup.html");
			rd.include(req,res);
			
		}
		
		else if(phone.length()<10 || phone.length()>10)	{
			
			PrintWriter out = res.getWriter();
			out.println("<font color='red' face='Comic Sans MS'>Phone number should be 10 digits long. Please try again!!</font>");
			RequestDispatcher rd = req.getRequestDispatcher("signup.html");
			rd.include(req,res);
		}
		
		else if(password.length()<5 || password.length()>20)	{
			
			PrintWriter out = res.getWriter();
			out.println("<font color='red' face='Comic Sans MS'>Password should be between 5 to 20 characters long. Please try again!!</font>");
			RequestDispatcher rd = req.getRequestDispatcher("signup.html");
			rd.include(req,res);
		}
			
		else if(!(password.equals(cpassword))) {
			
			PrintWriter out = res.getWriter();
			out.println("<font color='red' face='Comic Sans MS'>The entered passwords do not match. Please try again!!</font>");
			RequestDispatcher rd = req.getRequestDispatcher("signup.html");
			rd.include(req,res);
		}
		
		else	{
			
			String query;
			String url = "jdbc:mysql://localhost:3306/details";
			String uname = "root";
			String passw = "password17";
			try {
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			Connection con;
			
				con = DriverManager.getConnection(url,uname,passw);
			
			PreparedStatement pstmt;
			
			query = "insert into authenticate(username,email,phone,password) values (?,?,?,?)";
			pstmt = con.prepareStatement(query);
				pstmt.setString(1, username);
				pstmt.setString(2, email);
				pstmt.setString(3, phone);
				pstmt.setString(4, password);
				pstmt.executeUpdate();
				
				PrintWriter out = res.getWriter();
				out.println("<div class='container' style='color: green; font-size: 20px; text-align: center; !important'>Account successfully added to Database! Please login now!<br></div>");
				
				RequestDispatcher rd = req.getRequestDispatcher("/index.html");
				rd.include(req, res);
				
		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
}
}
