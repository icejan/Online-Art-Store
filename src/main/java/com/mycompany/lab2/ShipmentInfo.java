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
public class ShipmentInfo {
    
    public int getShipmentId () {
        return this.shipment_id;
    }
    
    public String getCompanyName() {
        return this.company_name;
    }
    
    public String getCompanyPhone() {
        return this.company_phone;
    }

    int shipment_id;
    String company_name, company_phone;
    
    public ShipmentInfo(int shipment_id, String company_name, String company_phone) {
        //bean = new UserInfo(fname, lname, adr, phone, username, password);
        this.shipment_id = shipment_id;
        this.company_name = company_name;
        this.company_phone = company_phone;
    }
}
