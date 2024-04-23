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
import com.mycompany.cartitem.persistence.Item_CRUD;
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
    
    public int addCart (int user_id, int item_id, int quantity) throws ClassNotFoundException, SQLException, ServerAddressNotSuppliedException, 
                                                                                IOException, InterruptedException {
        //user can only add an item to cart if the quantity they add does not overexceed the stock
        //the available stock is shared between users
        //System.out.println("in cartbusiness, userid: " + user_id +" quantity: " + quantity + " itemid: " + item_id);

        boolean success = false;
        int newQuantity = quantity;
        
        //first check if the item is already in their cart
        CartItem usercartitem = null;
        usercartitem = User_Cart_CRUD.getCartItem(user_id, item_id);
        
        boolean inCart = false;
        int userCartStock = 0;
        
        if (usercartitem != null){
            //item exist in the cart
            inCart = true;
            userCartStock = usercartitem.getItemQuantity();
            
        }
        
        //check how much stock is available for that item for all users carts
        int takenStock = User_Cart_CRUD.getCartItemStock(item_id);
        int itemStock = Item_CRUD.getItemStock(item_id);
        int availableStock = itemStock - takenStock;
        
        //if users wants to add more than whats available, set their quantity to the available amount
        if (newQuantity > availableStock){
            
            newQuantity = availableStock + userCartStock;
        } else {
            newQuantity += userCartStock;
        }
        /*
        System.out.println("in cartbusiness, userid: " + user_id +" has itemid: " + item_id + "in cart of quantity " + userCartStock);
        System.out.println("in cartbusiness, itemStock "+ itemStock +" - takenStock: " + takenStock + " = availableStock" + availableStock);
        System.out.println("in cartbusiness, userid " + user_id +" will have new quantity of " + newQuantity + " of itemid " + item_id);
        */
        success = User_Cart_CRUD.addCart(user_id,item_id, newQuantity ,inCart);
        
        if(success){
            
            int addedQuantity = newQuantity-userCartStock;
            
            if (addedQuantity != 0){
                String msg = "ADDCART:"+item_id+":"+addedQuantity; //ADDCART:2:1
                //System.out.println(msg); 
                Messaging.sendmessage(msg);
            }
            
            
        }
        
        //System.out.println("in cartbusiness, addcart is success: " + success);
        return newQuantity - userCartStock;
    }

}
