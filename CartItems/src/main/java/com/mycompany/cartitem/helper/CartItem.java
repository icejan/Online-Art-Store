/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cartitem.helper;


public class CartItem {
    
    public int getItemId() {
        return item_id;
    }
    
    public void setItemId(int item_id){
        this.item_id =item_id;
    }
    
    public int getUserId() {
        return user_id;
    }
    
    public void setUserId(int user_id) {
        this.user_id = user_id;

    }
    
    public int getItemQuantity() {
        return item_quantity;
    }
    
    public void setItemQuantity(int item_quantity){
        this.item_quantity = item_quantity;
    }
    
    int item_id;
    int user_id;
    int item_quantity;
    
    public CartItem(int item_quantity, int user_id, int item_id) {
        this.item_id =item_id;
        this.user_id = user_id;
        this.item_quantity = item_quantity;
    }
}

