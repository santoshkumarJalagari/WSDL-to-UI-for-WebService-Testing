package wsdlMethods;

import groovy.xml.MarkupBuilder;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.predic8.wsdl.Binding;
import com.predic8.wsdl.Definitions;
import com.predic8.wsdl.Operation;
import com.predic8.wsdl.Port;
import com.predic8.wsdl.PortType;
import com.predic8.wsdl.Service;
import com.predic8.wsdl.WSDLParser;
import com.predic8.wstool.creator.RequestTemplateCreator;
import com.predic8.wstool.creator.SOARequestCreator;

public class ReadWsdl {

	public List getServices(String wsdlPath) {

		List opList = null;

		WSDLParser parser = new WSDLParser();
		Definitions wsdl = parser.parse(wsdlPath);
		List returnallServices = wsdl.getServices();

		return returnallServices;
	}

	public List getPorts(Service services) {

		List returnPortList = null;
		returnPortList = services.getPorts();

		return returnPortList;
	}

	public List<String> getOperaions(String wsdlPath) {

		List<String> returnOperationList = new ArrayList<String>();
		List opList;

		WSDLParser parser = new WSDLParser();
		Definitions wsdl = parser.parse(wsdlPath);
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

	public Binding getBinding(Port port) {

		List opList = null;
		Binding binding = port.getBinding();

		return binding;
	}

	public void createRequest(String wsdlPath, String portName,
			String operationName, String bindingName) throws IOException {

		List opList = null;
		String returnRequest = null;
		WSDLParser parser = new WSDLParser();
		Definitions wsdl = parser.parse(wsdlPath);
		StringWriter writer = new StringWriter();

		SOARequestCreator creator = new SOARequestCreator(wsdl,
				new RequestTemplateCreator(), new MarkupBuilder(writer));

		creator.createRequest(portName, operationName, bindingName);
		FileWriter fw = new FileWriter("C:\\t\\" + operationName
				+ "_reguest.xml");
		fw.write(writer.toString());
		fw.close();

	}

}
