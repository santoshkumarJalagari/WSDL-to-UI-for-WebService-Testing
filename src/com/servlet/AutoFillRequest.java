package com.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webservice.services.WebServicesImp;

/**
 * Servlet implementation class AutoFillRequest
 */
@WebServlet("/AutoFillRequest")
public class AutoFillRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final String REQUEST_FILE_PATH="C:\\WSDLFiles\\requestFiles\\outputFiles";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutoFillRequest() {
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
		// TODO Auto-generated method stub
		Map<String, String> map=new HashMap<String, String>();
		List<String> fieldName=new ArrayList<String>();
		HttpSession session = request.getSession();
		
		String wsdlFilePath=(String)session.getAttribute("wsdlFilePath");
		String operationName=(String)session.getAttribute("operationName");
		String requestFilePath=REQUEST_FILE_PATH+"\\"+operationName+"_output.xml";
		System.out.println(requestFilePath);
		File requestFile=new File(requestFilePath);
		if(requestFile.exists()){
			WebServicesImp ws=new WebServicesImp();
			map=ws.getFieldsAndValueFromXML(requestFilePath);
			fieldName=ws.getFieldsFromRequest(requestFilePath);
			session.setAttribute("autoFillData", map);
			session.setAttribute("fieldNames", fieldName);
			session.setAttribute("operationName", operationName);
			session.setAttribute("requestFilePath", requestFilePath);
			session.setAttribute("wsdlFilePath", wsdlFilePath);
			response.sendRedirect("autoFilledRequest.jsp");
			
		}else
		{
			
		}
	}

}
