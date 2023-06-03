<%-- 
    Document   : Display
    Created on : 24 Aug 2021, 11:48:31 PM
    Author     : PLANDI
--%>

<%@page import="EShopWeb.ElectronicShop"%>
<%@page import="java.sql.*" %>
<%
    
ResultSet rs;
ElectronicShop eshop;
%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"  crossorigin="anonymous">
        <link href="https://bootswatch.com/5/flatly/bootstrap.min.css" rel="stylesheet" type="text/css">
        
        <style>
            #body{
                background-color: #ccccff
            }
        </style>
        
        <title>Stock View</title>
    </head>
    <body id="body">
        <div>
        
        <ul class="nav nav-pills nav-fill navbar-dark bg-primary">                          
                <li class="nav-item">
                    <a class="nav-link" href="index.html" target="_blank">Home</a>
                </li>
        </ul>
    </div> </br>
    <center><h2><b>Stock View</b></h2></center></br></br>
        <table class="table">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Barcode</th>
                    <th scope="col">Product Name</th>
                    <th scope="col">Description</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Price</th>
                </tr>
            </thead>
            <tbody>
              <%
               eshop = new ElectronicShop("Plandi","Card@4817","electronic_shop");
               rs = eshop.readDBase("stock");
               while(rs.next()){
                
              %>  
               
              <tr>
                
                <td><%=rs.getString(1) %></td>
                <td><%=rs.getString(2) %></td>
                <td><%=rs.getString(3) %></td>
                <td><%=rs.getString(4) %></td>
                <td><%=rs.getString(5) %></td>
                          
               </tr>
               <%} eshop.getCon().close(); %>
            </tbody>
        </table>    
    </body>
</html>
