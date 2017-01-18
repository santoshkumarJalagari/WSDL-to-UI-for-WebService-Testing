<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<title>Web Services Responce Page</title>
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
String responcePath;
Map<String,String> fieldList;
String operationName;
String FieldName;
String temp;
String key;
String value;
%>

<%	

responcePath=(String)session.getAttribute("responcePath");
operationName=(String)session.getAttribute("operationNameOut");
session.setAttribute("operationName",operationName);
ws=new WebServicesImp();
fieldList=ws.getFieldsAndValueFromXML(responcePath);
Iterator<String> keySetIterator = fieldList.keySet().iterator();

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
                     <div class="offset8 span3 ">
						Welcome: <%=user %>
					</div>
                     <br>
                     <div class="span12 nav-collapse collapse">
                        <ul class="nav nav-pills">
                        <li class="active"><a href="done.jsp">Home</a></li>
                            <li><a href="logout">Logout</a></li>
                        </ul>
                        <h4 align="center"><%=operationName%></h4> 
                    </div>	
                    				
	            </div>
	        </div>
	       
        </div>

	    	<br> 
	    	<br>


		    	
		    	<table>
		    		<% while(keySetIterator.hasNext()){
		    			 
		    			key = keySetIterator.next();
		    			value=fieldList.get(key);
		    			
		    			temp=key;
		    			 if (temp.contains(":")){
		    			   	    String[] temp1=temp.split(":");
		    			   	    FieldName=temp1[1];
		    			   	    }else{
		    			   	    FieldName=temp;
		    			   	    }%>
		    		<tr>
						<td>
							<table>
								<tr>
			             			<td><b><%=FieldName+": " %></b></td>
			             			<td><%=value %></td>
			             			

			             		</tr>
							</table>
						</td>
						<% if ((keySetIterator.hasNext())){
						key = keySetIterator.next();
		    			value=fieldList.get(key);
		    			
		    			temp=key;
		    			 if (temp.contains(":")){
		    			   	    String[] temp1=temp.split(":");
		    			   	    FieldName=temp1[1];
		    			   	    }else{
		    			   	    FieldName=temp;
		    			   	    } %>
						<td>
							<table>
								<tr>
			             			<td><b><%=FieldName+": " %></b></td>
			             			<td><%=value %></td>
			             			

			             		</tr>
							</table>
						</td>
						<% if ((keySetIterator.hasNext())){
						key = keySetIterator.next();
		    			value=fieldList.get(key);
		    			
		    			temp=key;
		    			 if (temp.contains(":")){
		    			   	    String[] temp1=temp.split(":");
		    			   	    FieldName=temp1[1];
		    			   	    }else{
		    			   	    FieldName=temp;
		    			   	    } %>
						<td>
							<table>
								<tr>
			             			<td><b><%=FieldName+": " %></b></td>
			             			<td><%=value %></td>

			             		</tr>
							</table>
						</td>
						<% if ((keySetIterator.hasNext())){
						key = keySetIterator.next();
		    			value=fieldList.get(key);
		    			
		    			temp=key;
		    			 if (temp.contains(":")){
		    			   	    String[] temp1=temp.split(":");
		    			   	    FieldName=temp1[1];
		    			   	    }else{
		    			   	    FieldName=temp;
		    			   	    } %>
						<td>
							<table>
								<tr>
			             			<td><b><%=FieldName+": " %></b></td>
			             			<td><%=value %></td>

			             		</tr>
							</table>
						</td>
					</tr>
				<% 	
						}}}}%>    
				</table>
					<br>
					<br>
					<br>
				
					</div>
				</div>
				

	
	
	<div class="container">
					<div class="navbar-fixed-bottom" align="center" >	
					<div class="navbar-inner">
						<form action="downloadResponceFile" method="post">
		                 	<input type="submit" value="View Responce as XML">
		                </form>
					</div>
					</div>
				</div>

</body>

</html>