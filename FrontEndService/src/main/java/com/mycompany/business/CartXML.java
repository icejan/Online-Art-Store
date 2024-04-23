/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.business;

import com.mycompany.helper.CartItem;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cartitems")
@XmlAccessorType (XmlAccessType.FIELD)
public class CartXML {
    @XmlElement(name = "cartitem")
    private ArrayList<CartItem> items;

    
    public List<CartItem>getCartItems(){
        return items;
               
    }
    public CartXML(){
               
    }

    public void setCartItems(ArrayList<CartItem> item_list){
        items=item_list;
               
    }   
}
