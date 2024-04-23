package com.mycompany.helper;

public class Account {
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
    
    public Account(int user_id, String username, String password, String first_name, String last_name, String phone, String email) {
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