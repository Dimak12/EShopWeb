
package EShopWeb;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PLANDI
 */
public class Selling extends HttpServlet {

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bcode = (String)request.getParameter("bcodetxt");
        String qty = (String)request.getParameter("qtytxt");
        ElectronicShop eshop = new ElectronicShop("Plandi","Card@4817","electronic_shop");        
        eshop.deduct(bcode, qty);
        
        try {
            eshop.getCon().close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
           
    }

}
