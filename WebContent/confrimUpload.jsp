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

WebServicesImp ws=new WebServicesImp();
List<String> wsdlFiles;
List<String> temp1=new ArrayList<String>();
String strExpired ;

%>

<%	
	
	sit_username=(String)session.getAttribute("username");
	if(session.getAttribute("username")==null )
		response.sendRedirect("index.jsp");
	else
	{

// 		int chac=sit_username.indexOf("@");
		user=sit_username;	//.substring(0,chac);
	}

%>
<body>
	<div class="container">
		<div class="row">
			<div class="span12">
				<h4 class="title">File uploaded sucessfully!!!!!</h4>
	                <a Style="  text-align:center ;" href="done.jsp">Click here to go back</a>
			</div>
		</div>

	</div>
</body>

</html>