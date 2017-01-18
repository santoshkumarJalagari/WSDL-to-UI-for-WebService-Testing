package com.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.KeyStore;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
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
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.webservice.services.WebServicesSoap;

/**
 * Servlet implementation class SendRequest
 */
@WebServlet("/SendRequest")
public class SendRequest extends HttpServlet {
	final String REQUEST_FILE_PATH="C:\\WSDLFiles\\requestFiles";
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SendRequest() {
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
		String nodeName;
		List fieldList;
		String requestFilePath;
		String operationName;
		String nodeValue;
		Transformer transformer;
		String wsdlFilePath;
		String endPointURL;
		String autUserName;
		String autPassword;
		String soapResponce;
		HttpSession session = request.getSession();
		fieldList=(List)session.getAttribute("RequestFieldNames");
		operationName=(String)session.getAttribute("operationName");
		wsdlFilePath=(String)session.getAttribute("wsdlFilePath");
		endPointURL=request.getParameter("EndPointURL");
		autUserName=request.getParameter("AutUserName");
		autPassword=request.getParameter("AutPassword");
		
		requestFilePath=REQUEST_FILE_PATH+"\\"+operationName+"_reguest.xml";
		String outPutRequestFilePath=REQUEST_FILE_PATH+"\\outputFiles\\"+operationName+"_output.xml";
		String responcePath=REQUEST_FILE_PATH+"\\responce"+"\\"+operationName+"_responce.xml";
		String responcePath2=getServletContext().getRealPath("/")+"data/"+operationName+"_responce.xml";
		File createFolder=new File(REQUEST_FILE_PATH+"\\outputFiles");
		File responceFolderPath=new File(REQUEST_FILE_PATH+"\\responce");
		
		if (!createFolder.exists()){
			createFolder.mkdir();
		}
		if (!responceFolderPath.exists()){
			responceFolderPath.mkdir();
		}
		
		
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(requestFilePath);
			for (int i=0;i<fieldList.size();i++){
				nodeName=(String)fieldList.get(i);
				System.out.println(nodeName);
				nodeValue=request.getParameter(nodeName);
				System.out.println(nodeValue);
				Node node= doc.getElementsByTagName(nodeName).item(0);//zero tells the order in the xml
				if (!(nodeValue.equals(""))){
					node.setTextContent(nodeValue);
					System.out.println(node.getNodeName()+":"+node.getTextContent());
				}else{
					node.setTextContent("");
					System.out.println(node.getNodeName()+":"+node.getTextContent());
					
				}
			}
				transformer = TransformerFactory.newInstance().newTransformer();
				Result output = new StreamResult(new File(outPutRequestFilePath));
			    Source input = new DOMSource(doc);
				transformer.transform(input, output);
				
				//Create SOAP project 
				WebServicesSoap wss=new WebServicesSoap();
				wss.CreateSOAPProject(operationName, wsdlFilePath);
				soapResponce=wss.SendRequest(operationName, outPutRequestFilePath, endPointURL, autUserName, autPassword);
				
				 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			     DocumentBuilder builder = factory.newDocumentBuilder();
			     Document resDoc =builder.parse(new InputSource(new StringReader(soapResponce)));
			  // Write the parsed document to an xml file
			     TransformerFactory transformerResponce = TransformerFactory.newInstance();
			     Transformer transformerReq = transformerResponce.newTransformer();
			     DOMSource source = new DOMSource(resDoc);
			     StreamResult result =  new StreamResult(new File(responcePath));
			     StreamResult result2 =  new StreamResult(new File(responcePath2));
			     transformerReq.transform(source, result);
//			     transformerReq.transform(source, result2);
			     
			    session.setAttribute("operationNameOut", operationName);
				session.setAttribute("responcePath", responcePath);
				
//				 response.sendRedirect(responcePath);
				response.sendRedirect("responce.jsp");
				
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerFactoryConfigurationError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
	       
			
			
	}	
		
	
	
	public String sendRequest(){
//		
//		 KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
//	        InputStream keystoreInput = new FileInputStream(KEY_STORE_PATH);
//	        keystore.load(keystoreInput, KEY_STORE_PASSWORD.toCharArray());
//	        System.out.println("Keystore has " + keystore.size() + " keys");
//
//	        SchemeRegistry schemeRegistry = new SchemeRegistry();
//	        SSLSocketFactory lSchemeSocketFactory = new SSLSocketFactory(keystore,  KEY_STORE_PASSWORD);
//	        schemeRegistry.register(new Scheme("https", 443, lSchemeSocketFactory));
//
//	        final HttpParams httpParams = new BasicHttpParams();
//	        DefaultHttpClient lHttpClient = new DefaultHttpClient(new SingleClientConnManager(schemeRegistry), httpParams);
//
//	        String lUrl = "URL";
//
//	        String lXml = getStringFromDocument(new File("request1.xml"));
//	        System.out.println(lXml + "\n\n\n");
//
//	        HttpPost lMethod = new HttpPost(lUrl);
//	        HttpEntity lEntity = new StringEntity(lXml, "text/xml", "UTF-8");
//	        lMethod.setEntity(lEntity);
//	        lMethod.setHeader("SOAPAction", "soapaction");
//
//	        HttpResponse lHttpResponse = lHttpClient.execute(lMethod);
//
//	        System.out.println("Response status code: "
//	                + lHttpResponse.getStatusLine().getStatusCode());
//	        System.out.println("Response body: ");
//	        System.out.println(EntityUtils.toString(lHttpResponse.getEntity()));
		return null;
		
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
	
	public Document updateUserDataInRequest(String requestFilePath,Document doc, String nodeName, String nodeValue){
		
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(requestFilePath);
			Node messageType= doc.getElementsByTagName(nodeName).item(0);//zero tells the order in the xml
			if (!(nodeValue.equals(null))){
			messageType.setTextContent(nodeValue);
			}else{
				messageType.setTextContent("");
			}
		
		
		}
			catch (TransformerFactoryConfigurationError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		return doc;
		}
	
	
}
