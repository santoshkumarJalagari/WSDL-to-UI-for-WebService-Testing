<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
%>
<%
	sit_username=(String)session.getAttribute("username");
	if(session.getAttribute("username")==null )
		response.sendRedirect("index.jsp");
	else
	{
// 		int chac=sit_username.indexOf("@");
		user=sit_username;	//.substring(0,chac);

%>
<body>
	<div class="container">
		<div class="navbar navbar-fixed-top">
	            <div class="navbar-inner">
                     <a class="brand" href="home.jsp"><img alt="SSS" src="img/Autodesklogo.jpg"></a>
                     <div class="offset4 span2 ">
						Welcome: <%=user %>
					</div>
                     <br>
                     <div class="span12 nav-collapse collapse">
                        <ul class="nav nav-pills">
                            <li class="active"><a href="home.jsp">Home</a></li>
                            <li><a href="logout">Logout</a></li>
                        </ul>
                    </div>					
	            </div>
	        </div>
	       
        </div>
                 
	<div class="container">
	 
	    <div class="row">
	    
	    	<div class="span12"> <hr> </div>
	    	<form method="post" action="AddWSDLFile" enctype="multipart/form-data">
								<input type="file" name="dataFile" id="fileChooser"/><br/><br/>
								<input type="submit" value="Upload" />
			</form>
	        <div class="span12">
	            <h4 class="title">Available WSDL files</h4>
	            
	                <li>
	                    Click on add WSDL button above.
	                </li>
	            	<li>
	                    Browse WSDL file.
	                </li>
	                <li>
	                    Select the Operation to see the request form
	                </li>
	                <li>
	                    Fill the request form and click on Submit button to see the responce
	                </li>
	        </div>
	        
	         <div class="span12"> <hr> </div>
	         
	         <div class="span12">
	            <h2 class="title">Some Info</h2>
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
<%
	}
%>
</html>