
/*PLANDI MAKALI
  220344817
*/

package EShopWeb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;



public class ElectronicShop {

    private Connection con;
    private PreparedStatement pstmnt;
    private String feedback;
    
    
    public ElectronicShop(String user,String password,String dbName){
        
        String url = "jdbc:mysql://localhost:3306/"+dbName;  
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(url,user,password);
        } catch (SQLException|ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }
    
    public static void main(String[] args) {                        
        
    }
    
    public String csvRead (String path) throws SQLException{
        
        String line ="";
        String splitBy= ";";
        String query = "select * from stock where barcode = ?";
        try{
            
            BufferedReader br = new BufferedReader(new FileReader(path));
            br.readLine();
            while((line = br.readLine()) !=null  ){
               
                String[] content = line.split(splitBy);
                
                String barcode = content[0];
                String product = content[1];
                String description = content[2];
                String quantity = content[3];
                String price = content[4];
                
            feedback = checkDatabase (barcode,query,barcode,product,description,quantity,price);
        
            }
        } catch(IOException e){ e.printStackTrace();}
        return feedback;
    }
    
    public String manualUpdate (String barcode,String product,String description,String quantity,String price) throws ClassNotFoundException,SQLException{

   
  
   String insert = "insert into stock (barcode,product,description,quantity,price_zar) values (?,?,?,?,?)";
  
   pstmnt = con.prepareStatement(insert);
   pstmnt.setString(1, barcode);
   pstmnt.setString(2, product);
   pstmnt.setString(3, description);
   pstmnt.setInt(4, Integer.parseInt(quantity));
   pstmnt.setDouble(5, Double.parseDouble(price));
   
   pstmnt.executeUpdate();
   
    
    feedback = "Stock successfully updated"; 
   
return feedback;
}

    public String getFeedback() {
        return feedback;
    }

    public Connection getCon() {
        return con;
    }
    
   
    
    public String checkDatabase (String column,String query,String barcode,String product,String description,String quantity,String price){
    
    try{              
               
        pstmnt = con.prepareStatement(query);
        PreparedStatement pst = con.prepareStatement("update stock set quantity = quantity + ? , price_zar = ? where barcode = ?");
        
        pstmnt.setString(1, column);
        pst.setInt(1, Integer.parseInt(quantity));
        pst.setDouble(2, Double.parseDouble(price));
        pst.setString(3, column);
        ResultSet result = pstmnt.executeQuery();
        
        if(!result.next()){
            
            feedback = manualUpdate(barcode,product,description,quantity,price); 
        }
        
        else{
            
            pst.executeUpdate();
            feedback = "Stock successfully updated";
             
        }
        
       
    
    }catch(SQLException|ClassNotFoundException e){
        System.out.println(e);
    }
    
    return feedback;
}
    
   
    public void deduct(String barcode, String quantity){
        
        try{
            String deduct = "update stock set quantity = quantity - ? where barcode = ?";
            pstmnt = con.prepareStatement(deduct);
            pstmnt.setString(1, quantity);
            pstmnt.setString(2,barcode );
            pstmnt.executeUpdate();
            
        }catch(SQLException e){
        System.out.println(e);
    }
    }
    
    public void deleteTemp(){
        try{
            String del = "delete from temporary";
            pstmnt = con.prepareStatement(del);
            pstmnt.executeUpdate();
           
        
        }catch(SQLException e){
        System.out.println(e);
    }
        
        
        
    }
    
    public ResultSet readDBase(String dbName){
        
        ResultSet rs=null;
        try {
            String query = "select * from " + dbName;
            pstmnt = con.prepareStatement(query);
            rs = pstmnt.executeQuery();
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
         
        return rs;
         
        
    }
     
}
