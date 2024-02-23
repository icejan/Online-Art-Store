/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab2;

import java.util.ArrayList;

/**
 *
 * @author student
 */
public class CartInfo {
    
    //private ArrayList[][] cart_items = newArrayList[][];
    private ArrayList <Cart_Item> cart_items = new ArrayList<>();
    
    public void addCartItem(Cart_Item cart_item){
        cart_items.add(cart_item);
    }
    public ArrayList<Cart_Item> getCartItems(){
        return cart_items;
    }
}
