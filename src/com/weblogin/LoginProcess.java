package com.weblogin;

import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
public class LoginProcess extends HttpServlet  
{
    protected void doPost(HttpServletRequest request,HttpServletResponse res)throws ServletException,IOException
    {
 
        String user=request.getParameter("username");
        String pass=request.getParameter("password");

        String flag="true";
        HttpSession session = request.getSession();
        if(flag.equals("false")==false) 
        {
        	System.out.println("Login Success...!");
	      	session.setAttribute("username",user);
	      	session.setAttribute("role", flag);
	      	res.sendRedirect("done.jsp");
        }
        else
        {
//        	System.out.println("Login Failed...!");
        	System.out.println("Login Success...!");
	      	session.setAttribute("username",user);
	      	session.setAttribute("role", flag);
	      	res.sendRedirect("done.jsp");
        }
    }
}