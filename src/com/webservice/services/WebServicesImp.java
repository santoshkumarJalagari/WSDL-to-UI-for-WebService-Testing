package com.webservice.services;

import groovy.xml.MarkupBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.predic8.wsdl.Binding;
import com.predic8.wsdl.Definitions;
import com.predic8.wsdl.Operation;
import com.predic8.wsdl.Port;
import com.predic8.wsdl.PortType;
import com.predic8.wsdl.Service;
import com.predic8.wsdl.WSDLParser;
import com.predic8.wstool.creator.RequestTemplateCreator;
import com.predic8.wstool.creator.SOARequestCreator;

public class WebServicesImp implements WebServices {

	List<String> cache;
	String wsdlFilePath;
	WSDLParser parser = new WSDLParser();
	Definitions wsdl;

	
	public WebServicesImp(){
		
	}
	public WebServicesImp(String wsdlFilePath)
	{
			this.wsdlFilePath=wsdlFilePath;
			wsdl = parser.parse(wsdlFilePath);
	}
	
	
	@Override
	public List<String> getServices() {

		List<String> returnallServices = new ArrayList<String>();
		List serviceListIte = wsdl.getServices();
		
		Iterator itrSer = serviceListIte.iterator();
		while (itrSer.hasNext()) {
			Service ser = (Service) itrSer.next();
			returnallServices.add(ser.getName());
		}
		return returnallServices;
	}

	@Override
	public List<String> getOperations() {

		List<String> returnOperationList = new ArrayList<String>();
		List opList;
		
		for (Service service : wsdl.getServices()) {
			for (Port port : service.getPorts()) {
				Binding binding = port.getBinding();
				PortType portType = binding.getPortType();
				opList = portType.getOperations();
				Iterator itrOpe = opList.iterator();
				while (itrOpe.hasNext()) {
					Operation oper = (Operation) itrOpe.next();
					returnOperationList.add(oper.getName());

				}

			}
		}
		return returnOperationList;
	}

	@Override
	public List<String> getBinding() {
		
		List<String> returnBindingList = new ArrayList<String>();
		List opList = null;
		for (Service service : wsdl.getServices()) {
			for (Port port : service.getPorts()) {
				Binding binding = port.getBinding();
				returnBindingList.add(binding.getName());
			}
		}
		

		return returnBindingList;
	}

	@Override
	public List<String> getPorts() {

		List<String> returnBindingList = new ArrayList<String>();
		List portList;
		
		for (Service service : wsdl.getServices()) {
			 portList=service.getPorts();
			 Iterator itrOpe = portList.iterator();
				while (itrOpe.hasNext()) {
					Port oper = (Port) itrOpe.next();
					returnBindingList.add(oper.getName());

				}
		}

		return returnBindingList;
	}

	@Override
	public String createRequest(String wsdlPath, String portName,
			String operationName, String bindingName) throws IOException {

		 
		StringWriter writer = new StringWriter();
		String requestFilePath="C:\\WSDLFiles\\requestFiles\\" + operationName
				+ "_reguest.xml";
		SOARequestCreator creator = new SOARequestCreator(wsdl,
				new RequestTemplateCreator(), new MarkupBuilder(writer));

		creator.createRequest(portName, operationName, bindingName);
		try{
			File createFolder=new File("C:\\WSDLFiles\\requestFiles");
			if (!createFolder.exists()){
				createFolder.mkdir();
			}
				
			FileWriter fw = new FileWriter(requestFilePath);
			fw.write(writer.toString());
			fw.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		System.out.println(requestFilePath);
		return requestFilePath;

	}

	@Override
	public List<String> getWsdlFiles() throws IOException {
		
		List returnList=new ArrayList<String>();
//		File destinationFilePath = new File(System.getProperty("user.dir")+"/WebContent/WSDLFilesInApplication");
//		System.out.println(System.getProperty("user.dir")+"/WebContent/WSDLFilesInApplication");
		File sourceFilePath = new File("C:\\WSDLFiles");
//		if (sourceFilePath.exists()){
//			File[] listOfFiles = sourceFilePath.listFiles();
//		    for (int i = 0; i < listOfFiles.length; i++) {
//		      if (listOfFiles[i].isFile()) {
//		        System.out.println("File " + listOfFiles[i].getName());
//		        FileUtils.copyFileToDirectory(listOfFiles[i], destinationFilePath);
//		        sourceFilePath.delete();		        
//				}
//		}
		
		
		File[] listOfFilesindes = sourceFilePath.listFiles();
		
//		System.out.println(sourceFilePath.getAbsolutePath());
		    for (int i = 0; i < listOfFilesindes.length; i++) {
		      if (listOfFilesindes[i].isFile()) {
//		        System.out.println("File " + listOfFilesindes[i].getName());
		    	 String wsdlName=listOfFilesindes[i].getName();
		    	 if (wsdlName.contains(".wsdl")){
//		    		 System.out.println(wsdlName);
		    		 String[] temp=wsdlName.split(".wsdl");
//		    		 System.out.println(temp[0]);
		    		 returnList.add(temp[0]);
		    		 
		    	 }
//		        System.out.println(returnList.get(i));
		      } 
		    }
//	}   
		
		return returnList;
	}
	@Override
	public List<String> getFieldsFromRequest(String requestFilePath) {
	
		ArrayList<String> returnTabList=new ArrayList<String>();
		NodeList nodeList;
		System.out.println("fields: "+requestFilePath);
		 try {
	   	  final Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(requestFilePath);
	   	  final XPathExpression xpath = XPathFactory.newInstance().newXPath().compile("//*[count(./*) = 0]");
	   	  nodeList = (NodeList) xpath.evaluate(doc, XPathConstants.NODESET);
	   	  for(int i = 0; i < nodeList.getLength(); i++) {
	   	    final Element el = (Element) nodeList.item(i);
	   	    String retrunString=el.getNodeName();  	   
	   	    returnTabList.add(retrunString);
//	   	    System.out.println(el.getNodeName()+" "+el.getNodeValue()+" "+el.getNodeType());
	   	    
	   	  }
	   	} catch (Exception e) {
	   	  e.printStackTrace();
	   	}
		 
		 return returnTabList;
	}
	public void setValuesInRequest(String requestFilePath,List fieldList, String nodeValue) {

		
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(requestFilePath);
			String nodeName;
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			Result output = new StreamResult(new File("C:\\testxmlresponce.xml"));
			
			
			for (int i=0;i<fieldList.size();i++){
				nodeName=(String)fieldList.get(i);
//				nodeValue=request.getParameter(nodeName);
				Node node= doc.getElementsByTagName(nodeName).item(0);//zero tells the order in the xml
				if (!(nodeValue.equals(""))){
					node.setTextContent(nodeValue);
					System.out.println(node.getNodeName()+":"+node.getTextContent());
				}else{
					node.setTextContent("");
					System.out.println(node.getNodeName()+":"+node.getTextContent());
				}
				
			}
		
			Source input = new DOMSource(doc);
			transformer.transform(input, output);
	        
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	@Override
	public void setValuesInRequest(String requestFilePath, String nodeName,
			String value) {
		// TODO Auto-generated method stub
		
	} 
	public Map<String,String> getFieldsAndValueFromXML(String responceFilePath) {
		Map<String, String> myData = new HashMap<String, String>();
		NodeList nodeList;
		System.out.println("fields: "+responceFilePath);
		 try {
	   	  final Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(responceFilePath);
	   	  final XPathExpression xpath = XPathFactory.newInstance().newXPath().compile("//*[count(./*) = 0]");
	   	  nodeList = (NodeList) xpath.evaluate(doc, XPathConstants.NODESET);
	   	  for(int i = 0; i < nodeList.getLength(); i++) {
	   	    final Element el = (Element) nodeList.item(i);
	   	    String nodeName=el.getNodeName();
	   	    String nodeValue=el.getTextContent();  
	   	    myData.put(nodeName, nodeValue);
	   	    System.out.println(nodeName+": "+nodeValue);
	   	  }
	   	} catch (Exception e) {
	   	  e.printStackTrace();
	   	}
		 
		 return myData;
	}

}
