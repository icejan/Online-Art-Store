/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab2;

import com.mycompany.lab2.Item;
import java.util.ArrayList;

/**
 *
 * @author student
 */
public class ItemInfo {
    
    private ArrayList <Item> items = new ArrayList<>();
    
    public void addItem(Item item){
        items.add(item);
    }
    public ArrayList<Item> getItems(){
        return items;
    }

   
    
}
