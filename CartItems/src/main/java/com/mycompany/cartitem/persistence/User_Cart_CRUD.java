/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cartitem.persistence;

import com.mycompany.cartitem.helper.CartItem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class User_Cart_CRUD {
    private static Connection getCon() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connection = System.getenv("DB_URL");//"localhost:3306";//
            con = DriverManager.getConnection("jdbc:mysql://"+connection+"/cart_ASMS", "root", "student");
            System.out.println("Connection established.");
        } catch (Exception e){
            System.out.println(e);
        }
        return con;
    }
    
   public static ArrayList<CartItem> getCart(String user_id)  {
        ArrayList <CartItem> items= new ArrayList<CartItem>();
        
        try{
            Connection con= getCon();
            String q;
            
                //SELECT * FROM User_Item_Cart where user_id = 2 ORDER BY add_datetime;
                q = "select * from User_Item_Cart "
                    + "WHERE user_id = "+user_id+" ORDER BY add_datetime;";
            
            //System.out.println("Made SQL command: " + q);
            PreparedStatement ps=con.prepareStatement(q);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){

                int itemquantity = rs.getInt("cart_quantity");
                int userid = rs.getInt("user_id");
                int itemid = rs.getInt("item_id");
                //System.out.println("in Cart CRUD userid" + userid + "itemid" + itemid + "quantity:" + itemquantity);
                items.add(new CartItem(itemquantity, userid, itemid));
                                
            }	
            con.close();

	}catch(Exception e){
            System.out.println(e);
        }
        
        return items;
        
    }
   
    public static CartItem getCartItem(int user_id, int item_id)  {
       
       CartItem item = null;
       
       try {
            Connection con=getCon();
            
            //select * from User_Item_Cart WHERE user_id = 1 AND item_id = 2;
            String q = "SELECT * from User_Item_Cart WHERE user_id = "+ user_id +" AND item_id = "+ item_id + ";";
            //System.out.println("Made SQL command: " + q);
            PreparedStatement ps=con.prepareStatement(q);
            ResultSet rs=ps.executeQuery();
            
            if (rs.next()){  
                int itemquantity = rs.getInt("cart_quantity");
                int userid = rs.getInt("user_id");
                int itemid = rs.getInt("item_id");
                //System.out.println("in Card CRUD for" + itemname + "item quantity" + itemquantity);
                item = new CartItem(itemquantity, userid, itemid);
            }
            
            con.close();
            
        } catch (Exception e) {
            System.out.println(e);
            
        }
       
       return item;
    }
    
    public static int addCart(int user_id, int item_id, int quantity, boolean inCart)  {
        
       try {
           
            Connection con=getCon();
            //if item is already in cart
            if (inCart){
                //UPDATE User_Item_Cart
                //SET cart_quantity = 10
                //WHERE user_id = 5111 AND item_id = 101;
                String q = "UPDATE User_Item_Cart SET cart_quantity = " + quantity 
                    + " WHERE user_id = " + user_id 
                    + " AND item_id = " + item_id + ";";
            
                //System.out.println("Made SQL command: " + q);
                PreparedStatement ps=con.prepareStatement(q);
                int result = ps.executeUpdate();
             
                con.close();
            
                //System.out.print("result from sql" + result);
                return result;
            
            } else {
                //add new item to cart
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime date = LocalDateTime.now();
                
                //INSERT INTO User_Item_Cart VALUES (1, 2, 1, "2024-01-01 10:12:33");
                String q = "INSERT INTO User_Item_Cart VALUES ("+user_id+", "+item_id+", "+quantity+", "+"'"+date.format(formatter)+"');";
            
                System.out.println("Made SQL command: " + q);
                
                PreparedStatement ps=con.prepareStatement(q);
                int result = ps.executeUpdate();
                
                con.close();
            
                //System.out.print("result from sql" + result);
                return result;
            }
            
            
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }

    }
    
    public static int removeCart (int user_id, int item_id) {
        
        try {
           
            Connection con=getCon();
            
                //DELETE FROM User_Item_Cart WHERE user_id=2 and item_id=4;
                String q = "DELETE from User_Item_Cart "
                    + " WHERE user_id = " + user_id 
                    + " AND item_id = " + item_id + ";";
            
                //System.out.println("Made SQL command: " + q);
                PreparedStatement ps=con.prepareStatement(q);
                int result = ps.executeUpdate();
             
                con.close();
            
                //System.out.print("result from sql" + result);
                return result;

        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
        
    }
    

}

