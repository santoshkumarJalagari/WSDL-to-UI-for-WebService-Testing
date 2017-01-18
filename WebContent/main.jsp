<%@page import="com.webservice.services.WebServicesImp"%>
<!document html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<style type="text/css">
				table{
					position:absolute;
					top:30%;
					left:16%;
					background-color:999999;
					border-collapse:collapse;
				}

				th{
					border:1px solid black;padding:5px; background-color:#61B5CF
				}

				td{
					border:1px solid black;padding:5px;background-color:#cecece;align:center;
				}				
		</style>
		<LINK rel="stylesheet" type="text/css" href="css/header.css" media="all">
		<div class="headerBlock">
			<img style="vertical-align:middle padding:5px;" align="absmiddle" src="img/autodesklogo.jpg"height="40"/>
		</div>

		
	</head>

<%@ page import = "wsdlMethods.*" %>
<%@ page import = "com.browseWSDL.*" %>
<%@ page import = "com.predic8.wsdl.Operation" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>


<%
//String wsdlPath="D:\\wsdlFiles\\PartyService.wsdl";
//WebServicesImp ws=new WebServicesImp(wsdlPath);
//ws.getWsdlFiles();
if (session.getAttribute("upload_sucess")==null){   
	%>
	<script type="text/javascript">
	alert('File uploaded Failed');
	</script>
	<%
}
else{
	%>
	<script type="text/javascript">
	alert('File uploaded sucessfully');
	</script>
	<%
}
if (session.getAttribute("no_data")=="yes"){   
	%>
	<script type="text/javascript">
	alert('browse file');
	</script>
	<%
}
%>






</html>








