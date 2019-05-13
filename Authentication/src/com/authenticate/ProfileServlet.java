package com.authenticate;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileServlet extends HttpServlet
{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		res.setContentType("text/html");
        PrintWriter out=res.getWriter();  
        
        HttpSession session = req.getSession(false);  
        
        if(session!=null)
        {  
        	String name=(String)session.getAttribute("name");
        	req.getRequestDispatcher("welcome.html").include(req, res);
        }  
        else
        {
        	out.println("<p class='error'>Login first!</p>");
        	req.getRequestDispatcher("login.html").include(req, res);
        }
	}
}
