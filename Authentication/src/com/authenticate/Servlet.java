package com.authenticate;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

//@WebServlet("/UploadServlet")
public class Servlet extends HttpServlet 
{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
    	res.setContentType("text/html");  
		PrintWriter out = res.getWriter();  
		          
		MultipartRequest m = new MultipartRequest(req,"d:/new");  
		out.print("Profile Pic successfully Uploaded!");	
	}
}
