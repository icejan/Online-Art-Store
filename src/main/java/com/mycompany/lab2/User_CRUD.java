/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab2;

import com.mycompany.lab2.UserInfo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

/**
 *
 * @author student
 */
public class User_CRUD {
    
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
    
    public static UserInfo read(String username, String password) {
        
        UserInfo bean = null;
        
        try {
            Connection con=getCon();
            //SELECT * FROM ASMS.`User` WHERE user_name LIKE "User123";
            String q = "SELECT * FROM User WHERE user_name LIKE \""+ username + "\"";
            //System.out.println("Made SQL command: " + q);
            PreparedStatement ps=con.prepareStatement(q);
            ResultSet rs=ps.executeQuery();
            
            if (rs.next()){
                int usrid = rs.getInt("user_id");
                String fname=rs.getString("user_first_name");
                String lname = rs.getString("user_last_name");
                String phone = rs.getString("user_phone_num");
                String email = rs.getString("user_email");
                String pass = rs.getString("user_password");
                
                if (pass.equals(password)){
                    bean = new UserInfo(usrid, username, pass, fname, lname, phone, email);
                }
            }
            
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return bean;
    }
}
