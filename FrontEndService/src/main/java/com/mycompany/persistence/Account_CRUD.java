/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.persistence;

import com.mycompany.helper.Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

import java.util.ArrayList;
/**
 *
 * @author student
 */
public class Account_CRUD {
    
    private static Connection getCon() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connection = System.getenv("DB_URL"); //"localhost:3306";
            con = DriverManager.getConnection("jdbc:mysql://"+connection+"/frontend_ASMS", "root", "student");
            System.out.println("Connection established.");
        } catch (Exception e){
            System.out.println(e);
        }
        return con;
    }
    
    public static Account read(String username, String password) {
        
        Account bean = null;
        
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
                    bean = new Account(usrid, username, pass, fname, lname, phone, email);
                }
            }
            
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return bean;
    }
    
    public static String getId(String username) {
        int userid = 0;
        try {
            Connection con=getCon();
            //select user_id from User where user_name = "Jane123";
            String q = "select user_id from User where user_name = \""+username+"\";";
            //System.out.println("Made SQL command: " + q);
            PreparedStatement ps=con.prepareStatement(q);
            ResultSet rs=ps.executeQuery();
            
            if (rs.next()){
                userid = rs.getInt("user_id");

            }
            //System.out.println("getid userid is" + userid);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return Integer.toString(userid);
    }
}
