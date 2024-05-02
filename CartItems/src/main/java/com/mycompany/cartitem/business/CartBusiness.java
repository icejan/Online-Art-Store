/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cartitem.business;

import java.util.ArrayList;

import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;
import java.io.IOException;
import java.sql.SQLException;


import com.mycompany.cartitem.helper.CartItem;

import com.mycompany.cartitem.persistence.User_Cart_CRUD;
/**
 *
 * @author student
 */
public class CartBusiness {
    public CartXML getCartXML(String user_id){
        
       ArrayList<CartItem> items = User_Cart_CRUD.getCart(user_id);
       
       /*
       System.out.println("items in getcartitems:");
       //for testing
        int index=0;
        for (CartItem i : items){
                System.out.println((index++)+": "+ i.getItemId());
        }
        */
        
        CartXML cartinfo = new CartXML();
        cartinfo.setCartItems(items);
        return (cartinfo);
    }
    
    public int addCart (int user_id, int item_id, int quantity, int itemstock) throws ClassNotFoundException, SQLException, ServerAddressNotSuppliedException, 
                                                                                IOException, InterruptedException {
        int success = 0;
        int newQuantity = quantity;
        
        //first check if the item is already in their cart
        CartItem usercartitem = null;
        usercartitem = User_Cart_CRUD.getCartItem(user_id, item_id);
        
        boolean inCart = false;
        boolean overstock = false;
        
        if (usercartitem != null){
            //item exist in the cart
            inCart = true;
            newQuantity += usercartitem.getItemQuantity();
            
        }
        //check if there is enough stock
        if (newQuantity <= itemstock){
            success = User_Cart_CRUD.addCart(user_id,item_id, newQuantity ,inCart);
            
        } else {
            //adjust to max quantity, which is the full stock
            overstock = true;
            newQuantity = itemstock;
            success = User_Cart_CRUD.addCart(user_id,item_id, newQuantity ,inCart);
            
        }
        
        //check if it was successful
        if (success == 1) {
            if (overstock){
                return newQuantity;
            } else {
                return quantity;
            }
            
        } else {
            //nonsuccessful
            return 0;
        }
        //System.out.println("in cartbusiness, addcart is success: " + success);
        
    }
    
    public int updateCart (int user_id, int item_id, int quantity, int itemstock) throws ClassNotFoundException, SQLException, ServerAddressNotSuppliedException, 
                                                                                IOException, InterruptedException {
        int success = 0;

        //check if there is enough stock
        if (quantity >= itemstock){
            //adjust to max quantity, which is the full stock
            quantity = itemstock;
        }
        success = User_Cart_CRUD.addCart(user_id,item_id, quantity ,true);
        //check if it was successful
        if (success == 1) {
            return quantity;
        } else {
            //nonsuccessful
            return 0;
        }
        //System.out.println("in cartbusiness, addcart is success: " + success);
        
    }
    
    public int removeCart (int user_id, int item_id) throws ClassNotFoundException, SQLException, ServerAddressNotSuppliedException, 
                                                                                IOException, InterruptedException {
        
        int success = User_Cart_CRUD.removeCart(user_id, item_id);
        
        return success;
    }

}
