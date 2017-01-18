package wsdlMethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
 
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
 
public class readXML {
	
	    public static void main(String[] args) {
	    	NodeList parentNode;
	    	NodeList chileNode;
	    	NodeList leafNode;
	    	boolean hadChildNode=true;
//	        try {
//	            FileInputStream file = new FileInputStream(new File("D:\\wsdlFiles\\request\\regfilepartservice.xml"));
//	                 
//	            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
//	             
//	            DocumentBuilder builder =  builderFactory.newDocumentBuilder();
//	             
//	            Document xmlDocument = builder.parse(file);
//	            	
//	            System.out.println(xmlDocument.getDocumentElement());
//	            Element rootElement =xmlDocument.getDocumentElement();
////	            System.out.println(rootElement.getNodeName());
//	            
//	            parentNode = rootElement.getChildNodes();
//	            
	            try {
	            	  final Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("D:\\wsdlFiles\\request\\regfilepartservice.xml");
	            	  final XPathExpression xpath = XPathFactory.newInstance().newXPath().compile("//*[count(./*) = 0]");
	            	  final NodeList nodeList = (NodeList) xpath.evaluate(doc, XPathConstants.NODESET);
	            	  for(int i = 0; i < nodeList.getLength(); i++) {
	            	    final Element el = (Element) nodeList.item(i);
	            	    String temp=el.getNodeName();
	    		   	    String[] temp1=temp.split(":");
	    		   	     System.out.println(temp1[1]);
//	            	    System.out.println(el.getNodeName()+" "+el.getNodeValue()+" "+el.getNodeType());
	            	    
	            	  }
	            	} catch (Exception e) {
	            	  e.printStackTrace();
	            	}
	            
	            
	            
//	             parentNode=xmlDocument.getElementsByTagName("body");
	            
//	            do 
//	            {
//	            for (int i=0;i<parentNode.getLength();i++){
//	            	System.out.println(parentNode.getLength());
//	            	 Node parentNode1 = parentNode.item(i);
//	            	 if (parentNode1.hasAttributes()){
//	            		 chileNode= parentNode1.getChildNodes();
//	            		 for (int j=0;j<chileNode.getLength();j++){
//	            			 
//	            		 }
//	            	 }else{
//	            		 System.out.println(parentNode1.getNodeName());
//	            	 }
//	            	 
//	            }
//	            
//	            
//	            }while (hadChildNode);
//	            
	            
	            
	            
//	            System.out.println(node.getLength());
//	            for (int a = 0; a < node.getLength()-1; a++) {
//	            	  Node data = node.item(a);
//	            	  	System.out.println(data.getNodeName() +":"+ data.hasChildNodes());
//	            	  if (data.hasChildNodes())
//	            	  {
//	            		  NodeList childNode=data.getChildNodes();
//	            		  System.out.println(childNode.getLength());
//	            		  for (int b = 0; a < childNode.getLength()-1; b++) {
//	            			  Node childNode1 = childNode.item(b);
//	            			  System.out.println(childNode1.getNodeName() +":"+ childNode1.hasAttributes());
//	            			  if (childNode1.hasChildNodes())
//	    	            	  {
//	    	            		  NodeList leafNode=childNode1.getChildNodes();
//	    	            		  System.out.println(leafNode.getLength());
//	    	            		  for (int c = 0; a < leafNode.getLength(); c++) {
//	    	            			  Node leafNode1 = leafNode.item(c);
//	    	            			  System.out.println(leafNode1.getNodeName() +":"+ leafNode1.hasAttributes());
//	    	            	  }
//	    	            	  }
//	            	  }
////	            	  	
//	            	  }
//	            }
	        
//	        } catch (FileNotFoundException e) {
//	            e.printStackTrace();
//	        } catch (SAXException e) {
//	            e.printStackTrace();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        } catch (ParserConfigurationException e) {
//	            e.printStackTrace();
//	        }       
	    }
	}


