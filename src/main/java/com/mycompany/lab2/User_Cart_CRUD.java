/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author student
 */
public class User_Cart_CRUD {
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
    
   public static CartInfo read(int user_id, int item_id) {
       CartInfo cart_info = null;

        try {
            Connection con=getCon();
            
            String q = "";
            
            if (item_id == 0) {
                System.out.println("list all cart items");
                //SELECT * from User_Item_Cart WHERE user_id = 5333;
                q = "SELECT * from User_Item_Cart WHERE user_id = "+ user_id + ";";

                
            } else {
                System.out.println("list cart item with id:" + item_id);
                //SELECT * from User_Item_Cart WHERE user_id = 5333 AND item_id = 100;
                
                q = "SELECT * from User_Item_Cart WHERE user_id = "+ user_id +" AND item_id = "+ item_id + ";";
                
 
            }
            
                System.out.println("Made SQL command: " + q);
                PreparedStatement ps=con.prepareStatement(q);
                ResultSet rs=ps.executeQuery();
                
                if (rs.next()){
                    cart_info = new CartInfo();
                    
                    int itemquantity = rs.getInt("cart_quantity");
                    int userid = rs.getInt("user_id");
                    int itemid = rs.getInt("item_id");
                    //System.out.println("in Card CRUD for" + itemname + "item quantity" + itemquantity);
                    cart_info.addCartItem(new Cart_Item(itemquantity, userid, itemid));
                }
                
                while (rs.next()){

                    int itemquantity = rs.getInt("cart_quantity");
                    int userid = rs.getInt("user_id");
                    int itemid = rs.getInt("item_id");
                    //System.out.println("in Card CRUD for" + itemname + "item quantity" + itemquantity);
                    cart_info.addCartItem(new Cart_Item(itemquantity, userid, itemid));
                }
            
            
            con.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }

       return cart_info;
   }
   
   
   public static boolean update(int user_id, int item_id, int quantity) {

       try {
            Connection con=getCon();
            //UPDATE User_Item_Cart
            //SET cart_quantity = 10
           //WHERE user_id = 5111 AND item_id = 101;
            String q = "UPDATE User_Item_Cart SET cart_quantity = " + quantity 
                    + " WHERE user_id = " + user_id 
                    + " AND item_id = " + item_id + ";";
            
            System.out.println("Made SQL command: " + q);
            PreparedStatement ps=con.prepareStatement(q);
            int result = ps.executeUpdate();
             
            con.close();
            
            System.out.print("result from sql" + result);
            return true;
            
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

   }
   
   public static boolean create(int user_id, int item_id, int quantity) {
       
       try {
            Connection con=getCon();
            //INSERT INTO User_Item_Cart VALUES (1, 5111, 101);
            String q = "INSERT INTO User_Item_Cart VALUES ("+quantity+", "+user_id+","+item_id+");";
            
            System.out.println("Made SQL command: " + q);
            PreparedStatement ps=con.prepareStatement(q);
            int result = ps.executeUpdate();
             
            con.close();
            
            System.out.print("result from sql" + result);
            return true;
            
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

   }
   
   public static boolean delete(int user_id, int item_id) {
       
       try {
            Connection con=getCon();
            //DELETE FROM User_Item_Cart WHERE user_id=5333 AND item_id=104;
            String q = "DELETE FROM User_Item_Cart WHERE user_id="+user_id+" AND item_id="+item_id+";";
            
            System.out.println("Made SQL command: " + q);
            PreparedStatement ps=con.prepareStatement(q);
            int result = ps.executeUpdate();
             
            con.close();
            System.out.print("result from sql" + result);
            return true;
            
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

   }
   
   

}
