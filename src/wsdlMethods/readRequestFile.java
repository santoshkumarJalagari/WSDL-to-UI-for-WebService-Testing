package wsdlMethods;


import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class readRequestFile {

	public ArrayList<String> getTabNamesFromRequest(String requestFilePath)
	{
			ArrayList<String> returnTabList=new ArrayList<String>();
			NodeList nodeList;
			
			 try {
		   	  final Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(requestFilePath);
		   	  final XPathExpression xpath = XPathFactory.newInstance().newXPath().compile("//*[count(./*) = 0]");
		   	  nodeList = (NodeList) xpath.evaluate(doc, XPathConstants.NODESET);
		   	  for(int i = 0; i < nodeList.getLength(); i++) {
		   	    final Element el = (Element) nodeList.item(i);
		   	    String temp=el.getNodeName();
		   	    String[] temp1=temp.split(":");
		   	    returnTabList.add(i, temp1[1]);
//		   	    System.out.println(el.getNodeName()+" "+el.getNodeValue()+" "+el.getNodeType());
		   	    
		   	  }
		   	} catch (Exception e) {
		   	  e.printStackTrace();
		   	}
			 
			 return returnTabList;
	}
   
	
}
