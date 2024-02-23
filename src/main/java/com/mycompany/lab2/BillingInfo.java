/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab2;

import java.util.Date;
/**
 *
 * @author student
 */
public class BillingInfo {
    
    public int getBillingId () {
        return this.billing_id;
    }
    
    
    public Date getBillingDate() {
        return this.billing_date;
    }
    
    public String getCardNum() {
        return this.card_num;
    }
    
     public int getItemId() {
        return this.item_id;
    }
    
    int billing_id, item_id;
    Date billing_date;
    String card_num;
    
    public BillingInfo(int billing_id, Date billing_date, String card_num, int item_id) {

        this.billing_id = billing_id;
        this.billing_date = billing_date;
        this.card_num = card_num;
        this.item_id = item_id;
    }
}
