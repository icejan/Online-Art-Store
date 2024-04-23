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
            
                //SET GLOBAL time_zone = '+3:00'; select * from User_Item_Cart WHERE user_id = 2;
                q = "select * from User_Item_Cart "
                    + "WHERE user_id = "+user_id+";";
            
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
    
    public static int getCartItemStock (int item_id){
        int total_quantity = 0;
        
        try {
            Connection con=getCon();
            
            //select cart_quantity from User_Item_Cart WHERE item_id = 2;
            String q = "SELECT cart_quantity from User_Item_Cart WHERE item_id = "+ item_id + ";";
            //System.out.println("Made SQL command: " + q);
            PreparedStatement ps=con.prepareStatement(q);
            ResultSet rs=ps.executeQuery();
            
            while (rs.next()){  
                int itemquantity = rs.getInt("cart_quantity");
                
                //System.out.println("in Card CRUD for" + itemname + "item quantity" + itemquantity);
                total_quantity += itemquantity ;
            }
            
           con.close(); 
        } catch (Exception e) {
            System.out.println(e);
            
        }
        
        return total_quantity;
    }
    
    public static boolean addCart(int user_id, int item_id, int quantity, boolean inCart)  {
        
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
                return true;
            
            } else {
                //add new item to cart
                //INSERT INTO User_Item_Cart VALUES (1, 5111, 101);
                String q = "INSERT INTO User_Item_Cart VALUES ("+quantity+", "+user_id+","+item_id+");";
            
                //System.out.println("Made SQL command: " + q);
                PreparedStatement ps=con.prepareStatement(q);
                int result = ps.executeUpdate();
             
                con.close();
            
                //System.out.print("result from sql" + result);
                return true;
            }
            
            
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

   }
    

}

