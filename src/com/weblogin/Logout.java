package com.weblogin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;
 
public class Logout extends HttpServlet  
{
    protected void doGet(HttpServletRequest request,HttpServletResponse res)throws ServletException,IOException
    {
    	HttpSession session = request.getSession();
    	session.setAttribute("username", null);
    	session.invalidate();
    	res.sendRedirect("index.jsp");
    }
}