<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.awt.print.Printable"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.webservice.services.WebServicesImp"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
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
String wsdlName=null;
String portName=null;
String bindingName=null;
String fieldName=null;
String fieldValue=null;
String operationName=null;
String temp;
String wsdlPath;
String fieldNamewithOutTags;
List fieldList;
String wsdlFilePath;
String requestFilePath;
Map<String,String> autoRequestFillData;

%>

<%	


operationName=(String)session.getAttribute("operationName");
requestFilePath=(String)session.getAttribute("requestFilePath");
wsdlFilePath=(String)session.getAttribute("wsdlFilePath");
fieldList=(List<String>)session.getAttribute("fieldNames");
autoRequestFillData=(Map)session.getAttribute("autoFillData");
Iterator<String> keySetIterator = autoRequestFillData.keySet().iterator();



	session.setAttribute("RequestFieldNames", fieldList);
	session.setAttribute("operationName", operationName);
	session.setAttribute("wsdlFilePath", wsdlFilePath);
	
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
		<div class="navbar navbar-fixed-top">
		<a class="brand" href="done.jsp"><img alt="SSS" src="img/Autodesklogo.jpg"></a>
	            <div class="navbar-inner">
                     <div class="offset8 span2 ">
						Welcome: <%=user %>
					</div>
                     <br>
                     <div class="span12 nav-collapse collapse">
                        <ul class="nav nav-pills">
                        <li class="active"><a href="done.jsp">Home</a></li>
                            <li><a href="logout">Logout</a></li>
                             <li>
                            <form method="post" action="AutoFill">
			                 <input type="submit" value="Auto Fill Responce"/>
			            </form>
			            </li>
                        </ul>
                        
                        <h4 align="center"><%=operationName%></h4>
                        
                        
                    </div>	
                   
                    				
	            </div>
	             
	        </div>
	       
        </div>

	    	<br> 
	    	<br>
	    	
	    	 
	    	<br> 
	<ul>
		    	<form method="post" action="SendRequestFields">
		    	<table width="800" align="center">
		    	
		    			<% while(keySetIterator.hasNext()){
		    				fieldName = keySetIterator.next();
			    			fieldValue=autoRequestFillData.get(fieldName);
			    			
		    				temp=fieldName;
		    			 	if (temp.contains(":")){
		    			   	    String[] temp1=temp.split(":");
		    			   	 fieldNamewithOutTags=temp1[1];
		    			   	    }else{
		    			   	    	fieldNamewithOutTags=temp;
		    			   	}%>
		    		<tr>
						<td>
							<table>
								<tr>
			             			<td><%=fieldNamewithOutTags+": " %></td>
			             		<tr>
			             		<tr>
			             			<td><input type="text" name=<%=fieldName%> value= <%=fieldValue%> ></input></td>
			             		</tr>
							</table>
						</td>
						<% if (keySetIterator.hasNext()){ 
							fieldName = keySetIterator.next();
			    			fieldValue=autoRequestFillData.get(fieldName);
		    				temp=fieldName;
		    			 	if (temp.contains(":")){
		    			   	    String[] temp1=temp.split(":");
		    			   	 fieldNamewithOutTags=temp1[1];
		    			   	    }else{
		    			   	    	fieldNamewithOutTags=temp;
		    			   	}%>
							<td>
								<table>
								<tr>
			             			<td><%=fieldNamewithOutTags+": " %></td>
			             		<tr>
			             		<tr>
			             			<td><input type="text" name=<%=fieldName%> value= <%=fieldValue%>></input></td>
			             		</tr>
								</table>
							</td>
							<% 	}%>  
							<% if (keySetIterator.hasNext()){ 
								fieldName = keySetIterator.next();
				    			fieldValue=autoRequestFillData.get(fieldName);
			    				temp=fieldName;
			    			 	if (temp.contains(":")){
			    			   	    String[] temp1=temp.split(":");
			    			   	    fieldNamewithOutTags=temp1[1];
			    			   	    }else{
			    			   	    fieldNamewithOutTags=temp;
			    			   	}%>
								<td>
								<table>
									<tr>
			             			<td><%=fieldNamewithOutTags+": " %></td>
			             			<tr>
			             			<tr>
			             			<td><input type="text" name=<%=fieldName%> value= <%=fieldValue%> ></input></td>
			             			</tr>
								</table>
								</td>
							<% 	}%> 
					</tr>
				<% 	}%>    
				</table>
					<br>
					<br>
					<br>
				<div class="container">
					<div class="navbar-fixed-bottom" align="center" >	
					<div class="navbar-inner">
						
			                        
						<b>End Point URL: <input type="text" name="EndPointURL">    
						&nbsp
						&nbsp  
						<b>User Name: <input type="text" name="AutUserName"> 
						&nbsp 
						&nbsp    
						<b>Password: <input type="text" name="AutPassword">	
						&nbsp
						&nbsp
						&nbsp
						&nbsp
						&nbsp
						&nbsp
						<input type="submit" value="Get Responce" />
						
						<br>
					</div>
					</div>
				</div>
				
				</form>
	</ul>

</body>

</html>