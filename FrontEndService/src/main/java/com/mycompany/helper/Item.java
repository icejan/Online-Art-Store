/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.helper;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import javax.xml.bind.annotation.*;

   @XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
   
public class Item {
   
    int itemId;
    String itemName;
    double itemPrice;
    int itemStock;
    Date itemListDate;
    int itemBrandId;
    
    
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int item_id) {
        this.itemId = item_id;
    }
    
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
    
    public int getItemStock() {
        return itemStock;
    }

    public void setItemStock(int itemStock) {
        this.itemStock = itemStock;
    }
    
    public Date getItemListDate() {
        return itemListDate;
    }

    public void setItemListDate(Date itemListDate) {
        this.itemListDate = itemListDate;
    }
    
    public int getItemBrandId() {
        return itemId;
    }

    public void setItemBrandId(int brand_id) {
        this.itemBrandId = itemBrandId;
    }
    
    
    public Item() {
        
    }
    public Item(int itemBrandId, int item_id, Date itemListDate, String itemName, double itemPrice, int itemStock) {
        this.itemId =item_id;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemStock = itemStock;
        this.itemListDate = itemListDate;
        this.itemBrandId = itemBrandId;
    }

    
}

