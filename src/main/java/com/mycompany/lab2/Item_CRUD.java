/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab2;

import com.mycompany.lab2.Item;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

import java.util.Date;

/**
 *
 * @author student
 */
public class Item_CRUD {
    
    private static Connection getCon() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ASMS", "root", "student");
            System.out.println("Connection established.");
        } catch (Exception e){
            System.out.println(e);
        }
        return con;
    }
    
   public static ItemInfo read(String command, String identifier) {
       //command can be keyword, category or brand
       //identifier is for specific item we are looking
       ItemInfo info = new ItemInfo();

       try {
            Connection con=getCon();
            
            String q = ""; //SQL scripts depending on what we're looking for
            if (command == null) {
                //SELECT * FROM Item
                q = "SELECT * FROM Item";
                
            } else if (command=="keyword") {
                //SELECT * from Item where item_name LIKE "%keyword%";
                q = "SELECT * FROM Item WHERE item_name LIKE \"%"+ identifier +"%\"";
            } else if (command=="id") {
                //SELECT * FROM Item WHERE item_id = 111;
                q = "SELECT * FROM Item WHERE item_id = "+ identifier +";";
            } else if (command=="releasedate") {
                //SELECT * from Item 
                //ORDER BY item_list_date DESC;
                q = "SELECT * from Item ORDER BY item_list_date DESC;";
            }
            
            
            System.out.println("Made SQL command: " + q);
            PreparedStatement ps=con.prepareStatement(q);
            ResultSet rs=ps.executeQuery();
            
            while (rs.next()){
                int itemid = rs.getInt("item_id");
                String itemname=rs.getString("item_name");
                double itemprice = rs.getDouble("item_price");
                int itemstock = rs.getInt("item_stock");
                Date itemlistdate = rs.getDate("item_list_date");
                int itembrand = rs.getInt("brand_id");
                
                if (itemstock > 0){
                    //only list items in stock
                    info.addItem(new Item(itemid, itemname, itemprice, itemstock, itemlistdate, itembrand));
                }
            }
            
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

       return info;
   }
}
