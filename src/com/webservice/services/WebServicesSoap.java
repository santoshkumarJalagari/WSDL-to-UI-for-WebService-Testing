package com.webservice.services;
//
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.security.KeyStore;
import javax.management.modelmbean.XMLParseException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import com.eviware.soapui.SoapUI;
import com.eviware.soapui.StandaloneSoapUICore;
import com.eviware.soapui.impl.wsdl.WsdlInterface;
import com.eviware.soapui.impl.wsdl.WsdlOperation;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.WsdlRequest;
import com.eviware.soapui.impl.wsdl.WsdlSubmit;
import com.eviware.soapui.impl.wsdl.WsdlSubmitContext;
import com.eviware.soapui.impl.wsdl.support.wsdl.WsdlImporter;
import com.eviware.soapui.model.iface.Operation;
import com.eviware.soapui.model.iface.Response;
import com.eviware.soapui.config.AnonymousTypeConfig;
import com.eviware.soapui.config.DefinitionCacheConfig;
import com.eviware.soapui.config.OperationConfig;
import com.eviware.soapui.config.SoapVersionTypesConfig;
import com.eviware.soapui.config.WsaVersionTypeConfig;
import com.eviware.soapui.config.WsdlInterfaceConfig;
import com.eviware.soapui.impl.WsdlInterfaceFactory;
import com.eviware.soapui.impl.support.AbstractHttpRequest;
import com.eviware.soapui.impl.support.AbstractHttpRequestInterface;
import com.eviware.soapui.impl.support.AbstractInterface;
import com.eviware.soapui.impl.wsdl.mock.WsdlMockResponse;
import com.eviware.soapui.impl.wsdl.support.ExternalDependency;
import com.eviware.soapui.impl.wsdl.support.FileAttachment;
import com.eviware.soapui.impl.wsdl.support.PathPropertyExternalDependency;
import com.eviware.soapui.impl.wsdl.support.PathUtils;
import com.eviware.soapui.impl.wsdl.support.policy.PolicyUtils;
import com.eviware.soapui.impl.wsdl.support.soap.SoapMessageBuilder;
import com.eviware.soapui.impl.wsdl.support.soap.SoapVersion;
import com.eviware.soapui.impl.wsdl.support.wsdl.WsdlContext;
import com.eviware.soapui.impl.wsdl.support.wsdl.WsdlLoader;
import com.eviware.soapui.impl.wsdl.support.wsdl.WsdlUtils;
import com.eviware.soapui.impl.wsdl.teststeps.BeanPathPropertySupport;
import com.eviware.soapui.impl.wsdl.teststeps.WsdlTestRequest;
import com.eviware.soapui.impl.wsdl.teststeps.WsdlTestRequestStep;
import com.eviware.soapui.model.ModelItem;
import com.eviware.soapui.model.propertyexpansion.PropertyExpander;
import com.eviware.soapui.settings.SSLSettings;
import com.eviware.soapui.settings.WsaSettings;
import com.eviware.soapui.settings.WsdlSettings;
import com.eviware.soapui.support.StringUtils;
import com.eviware.soapui.support.UISupport;
import com.eviware.soapui.support.resolver.ResolveContext;
import com.eviware.soapui.support.types.StringList;

public class WebServicesSoap implements WebServices {
//
	final String SOAP_PROJECT_FILE_PATH="C:\\WSDLFiles\\SoapProjects";
	@Override
	public List<String> getOperations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getServices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getPorts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getBinding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createRequest(String wsdlPath, String portName,
			String operationName, String bindingName) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getWsdlFiles() throws UnsupportedEncodingException,
			IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getFieldsFromRequest(String requestFilePath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValuesInRequest(String requestFilePath, String nodeName,
			String value) {
		// TODO Auto-generated method stub
		
	}
	 public void CreateSOAPProject(String operaionName, String WSDlFilePath) throws Exception

	 {
		 
		 String ProjectName=SOAP_PROJECT_FILE_PATH+"\\"+operaionName+".xml";
		 WsdlInterface wsdl;
		 File createFolder=new File(SOAP_PROJECT_FILE_PATH);
			if (!createFolder.exists()){
				createFolder.mkdir();
			}
		 File projectFile =new File(ProjectName);
	
		 SoapUI.setSoapUICore(new StandaloneSoapUICore(true));
		 SoapUI.getSettings().setString(SSLSettings.KEYSTORE, "eistesting.pfx");
		 SoapUI.getSettings().setString( SSLSettings.KEYSTORE_PASSWORD, "Adsk2016" );
		 WsdlProject project = new WsdlProject(operaionName);
	
		 WsdlInterface[] wsdls = WsdlImporter.importWsdl(project, WSDlFilePath);
	
		 System.out.println("The wsdl count ="+wsdls.length); //To get the number of  wsdl interfaces


	 for(int j=0;j<wsdls.length;j++){

	 
		 	 wsdl = wsdls[j];
		 	
			 String soapVersion = wsdl.getSoapVersion().toString();
		
			 System.out.println("The SOAP version ="+soapVersion);
		
			 System.out.println("The binding name = "+wsdl.getBindingName());
		
			 System.out.println("The binding type ="+wsdl.getBinding());
		
			 int c = wsdl.getOperationCount();
		
			 System.out.println("The total number of operations ="+c);
		
			 String reqContent="";
		
			 String result="";
			 
			 WsdlOperation op = wsdl.getOperationByName(operaionName);
		
			 System.out.println("OP:"+operaionName);
		
			 reqContent = op.createRequest(true);
		
			 WsdlRequest req = op.addNewRequest("Req_"+soapVersion+"_"+operaionName);
			 
			 System.out.println("Req_"+soapVersion+"_"+operaionName);
		
		//	 req.setRequestContent(reqContent);
		
		
		
		}
	 
			 project.saveIn(projectFile);
		
	}
	 
	 public String SendRequest(String operaionName, String requestFilePath,String endPointURL,String userName, String password) throws Exception

	 {
		 
	 String responce="";
	 String projectFile = SOAP_PROJECT_FILE_PATH+"\\"+operaionName+".xml";
	 String request=getStringFromDocument(new File(requestFilePath));
	 SoapUI.setSoapUICore(new StandaloneSoapUICore(true));

	 WsdlProject project = new WsdlProject(projectFile);

	 int c = project.getInterfaceCount();

	 System.out.println("The interface count   ="+c);

	 for(int i=0;i<c;i++)

	 {

	 WsdlInterface wsdl = (WsdlInterface) project.getInterfaceAt(i);

	 String soapVersion = wsdl.getSoapVersion().toString();

	 System.out.println("The SOAP version ="+soapVersion);

	 System.out.println("The binding name = "+wsdl.getBindingName());

	 int opc = wsdl.getOperationCount();

	 System.out.println("Operation count ="+opc);

	 


	 WsdlOperation op = wsdl.getOperationByName(operaionName);		
	 WsdlRequest req = op.getRequestByName("Req_"+soapVersion+"_"+operaionName);			 
	 System.out.println("REQUEST :"+req.getName());		
	 req.setRequestContent(request);		
	 System.out.println("The action is ="+req.getAction());
	 req.setEndpoint(endPointURL);
	 //Assigning u/p to an operation: Generate
//	 if(operaionName.equals("Generate")){
			
//						 req.setAuthType("Preemptive"); //Setting the Authorization type
//					
						 req.setUsername(userName);
					
						 req.setPassword(password);
			
//		}
			
				 WsdlSubmitContext wsdlSubmitContext = new WsdlSubmitContext(req);
			
				 WsdlSubmit<?> submit = (WsdlSubmit<?>) req.submit(wsdlSubmitContext, false);
			
				 Response response = submit.getResponse();
				 String xml=response.getContentAsXml();
				 responce = response.getContentAsString();
			
				 System.out.println("The result ="+responce);
	 }
	 
	 return responce;
	
	 }
	 
	 public static String getStringFromDocument(File file)
	   {
	       try
	       {
	           DocumentBuilderFactory dbFactory = DocumentBuilderFactory
	                   .newInstance();
	           DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	           Document doc = dBuilder.parse(file);        
	          DOMSource domSource = new DOMSource(doc);
	          StringWriter writer = new StringWriter();
	          StreamResult result = new StreamResult(writer);
	          TransformerFactory tf = TransformerFactory.newInstance();
	          Transformer transformer = tf.newTransformer();
	          transformer.transform(domSource, result);
	          return writer.toString();
	       }
	       catch(Exception ex)
	       {
	          ex.printStackTrace();
	          return null;
	       }
	   } 
}
