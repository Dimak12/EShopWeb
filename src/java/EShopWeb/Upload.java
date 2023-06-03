
package EShopWeb;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


/**
 *
 * @author PLANDI
 */

@WebServlet(name = "Upload", urlPatterns = {"/Upload"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, 
        maxFileSize = 1024 * 1024 * 1000, 
        maxRequestSize = 1024 * 1024 * 1000)   	


public class Upload extends HttpServlet {

    
   
    private String feedback;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            if(feedback=="Stock successfully updated"){
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Handler</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>" + feedback + "</h1>");
                out.println("</body>");
                out.println("</html>");
           
            }
            else{
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Handler</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Stock not updated</h1>");
                out.println("</body>");
                out.println("</html>");
            }
           
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                   
        String folderName = "files";
        String uploadPath = request.getServletContext().getRealPath("") + folderName;
        File dir = new File(uploadPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            
            Part filePart = request.getPart("file");
            String fileName = filePart.getSubmittedFileName();           
            String location = uploadPath+ File.separator +fileName;            
            InputStream is = filePart.getInputStream();
            Files.copy(is, Paths.get(uploadPath + File.separator + fileName), StandardCopyOption.REPLACE_EXISTING);
            
             ElectronicShop eshop = new ElectronicShop("Plandi","Card@4817","electronic_shop");
             try {
            feedback = eshop.csvRead(location);
        } catch (SQLException ex) {
           
        }
       
        processRequest(request, response);
        try {
            eshop.getCon().close();
        } catch (SQLException ex) {
            
        }
        
        
    }


}
