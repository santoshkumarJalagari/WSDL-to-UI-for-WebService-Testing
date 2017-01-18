package com.webservice.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;


public interface WebServices {

	public abstract List<String> getOperations();
	public abstract List<String> getServices();
	public abstract List<String> getPorts();
	public abstract List<String> getBinding();
	public abstract String createRequest(String wsdlPath, String portName, String operationName, String bindingName)throws IOException;
	public abstract List<String> getWsdlFiles() throws UnsupportedEncodingException, IOException;
	public abstract List<String> getFieldsFromRequest(String requestFilePath);
	public abstract void setValuesInRequest(String requestFilePath,String nodeName, String value);
		
	
	
	
	public class tesing {
		public static void main(String[] args) {
			
			
		}
	}
	
	tesing name = new tesing();
}
