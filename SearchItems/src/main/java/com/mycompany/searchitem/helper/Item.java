/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.searchitem.helper;

import java.util.Date;

public class Item {

    public int getItemId() {
        return item_id;
    }

    public void setItemId(int item_id) {
        this.item_id = item_id;
    }
    
    public String getItemName() {
        return item_name;
    }

    public void setItemName(String item_name) {
        this.item_name = item_name;
    }

    public double getItemPrice() {
        return item_price;
    }

    public void setItemPrice(double item_price) {
        this.item_price = item_price;
    }
    
    public int getItemStock() {
        return item_stock;
    }

    public void setItemStock(int item_stock) {
        this.item_stock = item_stock;
    }
    
    public Date getItemListDate() {
        return item_list_date;
    }

    public void setItemListDate(Date item_list_date) {
        this.item_list_date = item_list_date;
    }
    
    public int getItemBrandId() {
        return item_id;
    }

    public void setItemBrandId(int brand_id) {
        this.item_brand_id = item_brand_id;
    }
    
    int item_id;
    String item_name;
    double item_price;
    int item_stock;
    Date item_list_date;
    int item_brand_id;
    
    public Item(int item_id, String item_name, double item_price, int item_stock, Date item_list_date, int item_brand_id) {
        this.item_id =item_id;
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_stock = item_stock;
        this.item_list_date = item_list_date;
        this.item_brand_id = item_brand_id;
    }

    
}

