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

import com.mycompany.lab2.BillingInfo;

/**
 *
 * @author student
 */
public class Billing_CRUD {
    
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
    
   public static BillingInfo read(int user_id) {
       //get all billing records of user
       
       return null;
   }
   
   public static BillingInfo create(String billing_address, String card_num, int user_id) {
       //todays date, billingaddress from above, cardnum from above, userid from above
       
       //INSERT INTO User_Billing (billing_date, billing_address, billing_card_num, user_id)
        //VALUES ('2024-01-01', "billingaddress", "billingcardnum", 5333);
        
        
       return null;
   }
}
