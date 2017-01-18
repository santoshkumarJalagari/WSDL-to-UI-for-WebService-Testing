
package com.browseWSDL;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.webservice.services.WebServicesImp;

/**
 * Servlet implementation class UploadServlet
 */
public class UploadWSDLFile extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DATA_DIRECTORY = "data";
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        List<String> retrunWsdlList=new ArrayList<String>();
        HttpSession session = request.getSession();

        if (!isMultipart) {
            return;
        }

        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Sets the size threshold beyond which files are written directly to
        // disk.
        factory.setSizeThreshold(MAX_MEMORY_SIZE);

        // Sets the directory used to temporarily store files that are larger
        // than the configured size threshold. We use temporary directory for
        // java
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
//        System.out.println(getServletContext().getContextPath());
//        System.out.println(getServletContext().getRealPath(""));
        // constructs the folder where uploaded file will be stored
//        String uploadFolder=System.getProperty("user.dir")+"\\WebContent\\WSDLFilesInApplication";
//        String uploadFolder = getServletContext().getRealPath("")
//                + File.separator + DATA_DIRECTORY;
        String uploadFolder="C:\\WSDLFiles";
        File checkFolder=new File(uploadFolder);
        if (!checkFolder.exists())
        {
        	checkFolder.mkdir();
        }
        
        	System.out.println(uploadFolder);
        	
        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Set overall request size constraint
        upload.setSizeMax(MAX_REQUEST_SIZE);

        try {
            // Parse the request
//        	File statsFile = new File(this.getServletContext().getRealPath("/") + "stats/stats.csv");
//        	File folder = new File(System.getProperty("user.dir")+"/WebContent/WSDLFilesInApplication");
            List items = upload.parseRequest(request);
            System.out.println("items: "+items.size());
            if (!(items.size()==0)) {
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (!item.isFormField()) {
                    String fileName = new File(item.getName()).getName();
                    String filePath = uploadFolder + File.separator + fileName;
                    File uploadedFile = new File(filePath);
//                    System.out.println(filePath);
                    // saves the file to upload directory
                    item.write(uploadedFile);  
                }
                
            }
            
            
            
            session.setAttribute("upload_sucess","done");
                        }
            else{
            	 
                 session.setAttribute("no_data","yes");
            }
            
//            WebServicesImp ws = new WebServicesImp();
//            retrunWsdlList=ws.getWsdlFiles();
//            session.setAttribute("wsdlList",retrunWsdlList);

            // displays done.jsp page after upload finished
            getServletContext().getRequestDispatcher("/confrimUpload.jsp").forward(
                    request, response);

        } catch (FileUploadException ex) {
            throw new ServletException(ex);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }

    }

}