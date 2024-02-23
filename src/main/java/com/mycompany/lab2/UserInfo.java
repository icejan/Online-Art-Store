/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab2;

import com.mycompany.lab2.Item;
import java.util.ArrayList;

/**
 *
 * @author student
 */
public class UserInfo {
    /*to be completed
    For now, we just add book info to make the example work. This class must be completed in future
    */
    
    public int getUserId () {
        return this.user_id;
    }
    
    public String getFirstName() {
        return this.first_name;
    }
    
    

    int user_id;
    String first_name, last_name, email, phone, username, password;
    
    public UserInfo(int user_id, String username, String password, String first_name, String last_name, String phone, String email) {
        //bean = new UserInfo(fname, lname, adr, phone, username, password);
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
    }
    
}
