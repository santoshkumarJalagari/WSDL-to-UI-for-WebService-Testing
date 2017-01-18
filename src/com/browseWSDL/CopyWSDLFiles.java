package com.browseWSDL;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class CopyWSDLFiles {

public void copyWSDLFiles() throws IOException{
	
	File destinationFilePath = new File(System.getProperty("user.dir")+"/WebContent/WSDLFilesInApplication");
	System.out.println(System.getProperty("user.dir")+"/WebContent/WSDLFilesInApplication");
	File sourceFilePath=new File("C:\\WSDLFiles");
	File[] listOfFiles = sourceFilePath.listFiles();
    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile()) {
        System.out.println("File " + listOfFiles[i].getName());
        FileUtils.copyFile(sourceFilePath, destinationFilePath);
		}

    }	
}
}	
