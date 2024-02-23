/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab2;

/**
 *
 * @author student
 */
public class Cart_Item {
    
    public int getItemId() {
        return item_id;
    }
    
    public int getUserId() {
        return user_id;
    }
    
    public int getItemQuantity() {
        return item_quantity;
    }

    
    int item_id;
    int user_id;
    int item_quantity;
    
    public Cart_Item(int item_quantity, int user_id, int item_id) {
        this.item_id =item_id;
        this.user_id = user_id;
        this.item_quantity = item_quantity;
    }
}
