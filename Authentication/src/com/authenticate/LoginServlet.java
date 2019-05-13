package com.authenticate;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet 
{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		res.setContentType("text/html");
		
	    if(AuthenticationServlet.validate(username,password))
		{
	    	req.getRequestDispatcher("welcome.html").include(req, res);
	    	HttpSession session = req.getSession();
	    	session.setAttribute("username", username);
		}
		else
	    {
	    	PrintWriter out = res.getWriter();
	    	out.println("<font class='error' face='Comic Sans MS'>Username or password incorrect!</font>");
	    	RequestDispatcher rd = req.getRequestDispatcher("/login.html");
	    	rd.include(req, res);
	    }	
	}
}
