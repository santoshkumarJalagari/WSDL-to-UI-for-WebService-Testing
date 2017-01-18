<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
response.setHeader("Cache-Control","no-cache");  //Forces //caches to obtain a// //new copy of the page from the origin server
response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma","no-cache");//HTTP 1.0 backward compatibility 
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-cache">
	<META HTTP-EQUIV="Expires" CONTENT="-1">
	<title>Web Services Portal Login</title>
	<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />
	<link rel="icon" href="img/favicon.ico" type="image/x-icon" />
	
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" href="css/bootstrap-responsive.css"/>
	<link rel="stylesheet" type="text/css" href="css/style.css"/>
	
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
   	<script src="js/bootstrap.js"></script>
	
</head>
<%!
	String sit_username,user,enq;
%>
<%
sit_username=(String)session.getAttribute("username");
if(session.getAttribute("username")!=null )
	response.sendRedirect("done.jsp");
%>
<body>
	<div class="container">
		<div class="navbar navbar-fixed-top">
				<a class="brand" href="index.jsp"><img alt="SSS" src="img/Autodesklogo.jpg"> </a>
	            <div class="navbar-inner">
                    <form class="span8 form-inline" action="login" method="post">
                    &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
	                 	<input type="text" name="username" placeholder="Type Here in Username">
	                 	<input type="password" name="password" placeholder="Type Here in Password">
	                 	<input type="submit" value="Login">
	                </form>
	            </div>
	        </div>
        </div>
        
	<div class="container">
	    <div class="row">
	    	<div class="span12">  </div>
	        <div class="span12">
	            <h4 class="title">Autodesk Web Services Portal</h4>
	            
	                <li>
	                   Click on "Choose file" to browse the WSDL file from local server. 
	                </li>
	            	<li>
	                    After selecting WSDL file click on Upload to add the WSDL file to the portal.
	                </li>
	                <li>
	                    Click on WSDL file to see the operations under 'Operations'.
	                </li>
	                <li>
	                    Click on Operations to generate the request form.
	                </li>
	                <li>
	                    Fill the request form and click on get response.
	                </li>
	                <li>
	                    You can use 'AutoFillRequest' option, if you already filled the form in past for same operation. 
	                </li>
	            
	        </div>
	        
	         <div class="span12"> <hr> </div>
	         
	         <div class="span12">
	            <h4 class="title">Additional Information</h4>
	                <p>
<!-- 	                    <img style="float: left; margin-right: 20px;" src="http://www.creativitytuts.org/img/my-img.jpg" class="img-polaroid"> -->
	                     <li>Need to add additional information in the request like certificates, username/password, End point URL</li>
	                </p>
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