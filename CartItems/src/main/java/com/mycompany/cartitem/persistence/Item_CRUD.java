/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cartitem.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author student
 */
public class Item_CRUD {
    private static Connection getCon() {
        Connection con = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connection = System.getenv("DB_URL"); //"localhost:3306";
            con = DriverManager.getConnection("jdbc:mysql://"+connection+"/cart_ASMS", "root", "student");
            System.out.println("Connection established.");
        } catch (Exception e){
            System.out.println(e);
        }
        return con;
    }
    
    public static int getItemStock (int item_id){
        int stock = 0;
        
        try {
            Connection con=getCon();
            
            //SELECT item_stock FROM Item WHERE item_id = 1;
            String q = "SELECT item_stock FROM Item WHERE item_id = "+ item_id + ";";
            //System.out.println("Made SQL command: " + q);
            PreparedStatement ps=con.prepareStatement(q);
            ResultSet rs=ps.executeQuery();
            
            if (rs.next()){  
                int itemstock = rs.getInt("item_stock");
                
                
                stock = itemstock;
            }
           con.close(); 
           //System.out.println("in Item CRUD, stock is" + stock);
           
        } catch (Exception e) {
            System.out.println(e);
            
        }
        
        return stock;
    }
}
