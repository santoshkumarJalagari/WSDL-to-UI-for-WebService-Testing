<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.webservice.services.WebServicesImp"%>
<%
response.setHeader("Cache-Control","no-cache");  //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma","no-cache");//HTTP 1.0 backward compatibility 
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
	<title>Web Services Home Page</title>
	<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />
	<link rel="icon" href="img/favicon.ico" type="image/x-icon" />
	
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" href="css/bootstrap-responsive.css"/>
	<link rel="stylesheet" type="text/css" href="css/style.css"/>
	
<!--     <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script> -->
	<script src="js/jquery.min-1.9.0.js"></script>
   	<script src="js/bootstrap.js"></script>
	
</head>
<%!
String sit_username,user,enq;

WebServicesImp ws;
List<String> wsdlFiles;
List<String> operationsList=new ArrayList<String>();
List<String> portList=new ArrayList<String>();
List<String> bindingList=new ArrayList<String>();
String strExpired ;
String wsdlName=null;

%>

<%	
		
	wsdlName=request.getParameter("wsdlName");
	ws=new WebServicesImp("C:\\WSDLFiles\\"+wsdlName+".wsdl");
	operationsList=ws.getOperations();
	portList=ws.getPorts();
	bindingList=ws.getBinding();

	sit_username=(String)session.getAttribute("username");
	if(session.getAttribute("username")==null )
		response.sendRedirect("index.jsp");
	else
	{

// 		int chac=sit_username.indexOf("@");
		user=sit_username;	//.substring(0,chac);
	}
	
	wsdlFiles=ws.getWsdlFiles();
	
%>
<body>
	<div class="container">
		<div class="navbar navbar-fixed-top">
		<a class="brand" href=done.jsp><img alt="SSS" src="img/Autodesklogo.jpg"></a>
	            <div class="navbar-inner">
                     <div class="offset8 span2 ">
						Welcome: <%=user %>
					</div>
                     <br>
                     <div class="span12 nav-collapse collapse">
                        <ul class="nav nav-pills">
                            <li class="active"><a href="done.jsp">Home</a></li>
                            <li><a href="logout">Logout</a></li>
                        </ul>
                    </div>					
	            </div>
	        </div>
	       
        </div>
                 
	<div class="container">
	 
	    <div class="row">
	    
	    	<div class="span12"> <br> </div>
	    	<form method="post" action="AddWSDLFile" enctype="multipart/form-data">
								<input type="file" name="dataFile" id="fileChooser"/><br/><br/>
								<input type="submit" value="Upload" />
			</form>
	        <div class="span12">
	        
	            <h4 class="title">Available WSDL files</h4>
	               <% for (int i=0;i<wsdlFiles.size();i++){
	              %>

	                <li class="active"><a href="DisplayOperations.jsp?wsdlName=<%=wsdlFiles.get(i)%>"><%= wsdlFiles.get(i) %></a></li>
	                   
	                
	             <% 	}%>      
  			
	        </div>
	        
	         <div class="span12"> <hr> </div>
	         
	         <div class="span12">
	            <h4 class="title">Operations in <%="'"+wsdlName+"'"%></h4>
	                <% for (int i=0;i<operationsList.size();i++){
	              %>

	                <li class="active"><a href="requestForm.jsp?wsdlName=<%=wsdlName%>&operationName=<%=operationsList.get(i)%>&portName=<%=portList.get(0)%>&bindingName=<%=bindingList.get(0)%>"><%= operationsList.get(i) %></a></li>
	                   
	                
	             <% 	}%> 
	         </div>
	    </div>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="span12">
				<hr>
			</div>
		</div>

		<div class="row">
			<div class="offset2 span8">
				

			</div>
		</div>
	</div>
</body>

</html>