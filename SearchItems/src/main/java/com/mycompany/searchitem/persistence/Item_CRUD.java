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
    
   public static ArrayList <Item> searchForItems(String type, String query) {
       //command can be keyword, category or brand
       //identifier is for specific item we are looking
       ArrayList <Item> info = new ArrayList<Item>();

       try {
            Connection con=getCon();
            
            String q = ""; //SQL scripts depending on what we're looking for
            switch (type) {
                case "all_items":
                    q = "SELECT * FROM Item";
                    break;
                case "keyword":
                    //SELECT * from Item where item_name LIKE "%keyword%";
                    q = "SELECT * FROM Item WHERE item_name LIKE \"%"+ query +"%\"";
                    break;
                case "new_arrivals":
                    //SELECT * from Item ORDER BY item_list_date DESC;
                    q = "SELECT * from Item ORDER BY item_list_date DESC;";
                    break;
                case "brand":
                    //SELECT * from Item where brand_id = 2;
                    q = "SELECT * from Item where brand_id = "+query+";";
                    break;
                case "itemid":
                    //SELECT * FROM search_ASMS.Item where item_id = 1;
                    q="SELECT * FROM Item where item_id = "+query+";";
                    break;
                case "multipleid":
                    //SELECT * FROM Item where item_id IN (3,2,4,1) ORDER BY FIELD(item_id, 3,2,4,1);
                    //String numbers = query.substring(1,query.length()-1);
                    q="SELECT * FROM Item where item_id IN ("+query+") ORDER BY FIELD(item_id, "+query+");";
                    break;
                
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
