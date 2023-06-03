/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EShopWeb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PLANDI
 */
public class StockHandler extends HttpServlet {
    
    private String feedback;

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Update Response</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + feedback + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ElectronicShop eshop = new ElectronicShop("Plandi","Card@4817","electronic_shop");
        String barcode = request.getParameter("barcode1").trim();
        String product = request.getParameter("product1").trim();
        String description = request.getParameter("description1").trim();
        String quantity = request.getParameter("quantity1").trim();
        String price = request.getParameter("price1").trim();
        String query = "select * from stock where barcode = ?";
        feedback = eshop.checkDatabase(barcode, query, barcode, product, description, quantity, price);
        
        processRequest(request, response);
        try {
            eshop.getCon().close();
        } catch (SQLException ex) {
           
        }
    }

    

}
