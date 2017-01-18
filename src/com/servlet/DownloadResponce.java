package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DownloadResponce
 */
@WebServlet("/DownloadResponce")
public class DownloadResponce extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadResponce() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session =  request.getSession();
		String operationName=(String)session.getAttribute("operationName");
//		System.out.println(operationName);
//		System.out.println(getServletContext().getRealPath("/")+"data\\"+operationName+"_responce.xml");
//		response.sendRedirect(getServletContext().getRealPath("/")+"data\\"+operationName+"_responce.xml");
		response.sendRedirect("data\\"+operationName+"_responce.xml");
	}

}
