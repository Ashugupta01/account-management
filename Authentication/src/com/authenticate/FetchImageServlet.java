package com.authenticate;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

//@WebServlet("/fetchImage")

public class FetchImageServlet extends HttpServlet
{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
	{
		HttpSession session = req.getSession(false);
		String username = (String)session.getAttribute("username");
		
		//String username = req.getParameter("username");
		//String username = "raghav";
		
		String query;
		String url = "jdbc:mysql://localhost:3306/details";
		String uname = "root";
		String passw = "password17";
		
		JSONObject filename = new JSONObject();
		res.setContentType("text/html");
		System.out.println(username);
		
		try{Class.forName("com.mysql.jdbc.Driver");
		
		Connection con;
		con = DriverManager.getConnection(url,uname,passw);
		
		PreparedStatement pstmt;
		
		query = "select imgid from authenticate where username=?";
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, username);
	
		ResultSet rs = pstmt.executeQuery();
		
	    if(rs.next())
		{
	    	String sfilename = rs.getString("imgid");
	    	
	    	filename.put("fname",sfilename);
	    	res.getWriter().println(filename);
	    	res.getWriter().flush();
			res.getWriter().write("");
	    	res.getWriter().close();
	    	System.out.println(sfilename);
	    }
	    else
	    {
	    	PrintWriter out = res.getWriter();
	    	out.println("<font class='error' face='Comic Sans MS'>Invalid Session!</font>");
	    	RequestDispatcher rd = req.getRequestDispatcher("login.html");
	    	rd.include(req, res);
	    }	
	    
		}catch (Exception e) {}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request,response);
	}
}