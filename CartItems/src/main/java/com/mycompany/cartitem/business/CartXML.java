
package com.mycompany.cartitem.business;

import com.mycompany.cartitem.helper.CartItem;

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
    @XmlElement
    public void setCartItems(ArrayList<CartItem> item_list){
        items=item_list;
               
    }   
}

