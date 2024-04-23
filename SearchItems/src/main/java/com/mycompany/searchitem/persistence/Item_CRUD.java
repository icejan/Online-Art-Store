/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.searchitem.persistence;

import com.mycompany.searchitem.helper.Item;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;

import java.util.Date;

import java.sql.SQLException;

public class Item_CRUD {
    
    private static Connection getCon() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connection = System.getenv("DB_URL"); //"localhost:3306";
            con = DriverManager.getConnection("jdbc:mysql://"+connection+"/search_ASMS", "root", "student");
            System.out.println("Connection established.");
        } catch (Exception e){
            System.out.println(e);
        }
        return con;
    }
    
   public static ArrayList <Item> searchForItems(String command, String identifier) {
       //command can be keyword, category or brand
       //identifier is for specific item we are looking
       ArrayList <Item> info = new ArrayList<Item>();

       try {
            Connection con=getCon();
            
            String q = ""; //SQL scripts depending on what we're looking for
            if (command == null) {
                //SELECT * FROM Item
                q = "SELECT * FROM Item";
                
            } else if (command=="keyword") {
                //SELECT * from Item where item_name LIKE "%keyword%";
                q = "SELECT * FROM Item WHERE item_name LIKE \"%"+ identifier +"%\"";
            
            } else if (command=="releasedate") {
                //SELECT * from Item 
                //ORDER BY item_list_date DESC;
                q = "SELECT * from Item ORDER BY item_list_date DESC;";
            } else if (command=="itemid") {
                //SELECT * FROM search_ASMS.Item where item_id = 1;
                q="SELECT * FROM Item where item_id = "+identifier+";";
            }
            
            
            //System.out.println("Made SQL command: " + q);
            PreparedStatement ps=con.prepareStatement(q);
            ResultSet rs=ps.executeQuery();
            
            while (rs.next()){
                int itemid = rs.getInt("item_id");
                String itemname=rs.getString("item_name");
                double itemprice = rs.getDouble("item_price");
                int itemstock = rs.getInt("item_stock");
                Date itemlistdate = rs.getDate("item_list_date");
                int itembrand = rs.getInt("brand_id");
                
                info.add(new Item(itemid, itemname, itemprice, itemstock, itemlistdate, itembrand));
                
            }
            
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

       return info;
   }
   
   public static boolean updateStock (String item_id, int quantity) throws ClassNotFoundException, SQLException {
       boolean success = false;
       try {
            Connection con=getCon();
            
            //UPDATE Item SET item_stock = 20 WHERE item_id = 1;
            String q = "UPDATE Item SET item_stock = "+quantity+" WHERE item_id = "+item_id+";";
            
            //System.out.println("Made SQL command: " + q);
            PreparedStatement ps=con.prepareStatement(q);
            int result = ps.executeUpdate();
            con.close();
            
            success=true;
            
        } catch (Exception e) {
            System.out.println(e);
        }
       
       return success;
   }
}
