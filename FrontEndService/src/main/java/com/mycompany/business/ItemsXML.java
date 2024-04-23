package com.mycompany.business;

import com.mycompany.helper.Item;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "items")
@XmlAccessorType (XmlAccessType.FIELD)
public class ItemsXML {
    
    @XmlElement(name = "item")
    private ArrayList<Item> items;
    
    
    public List<Item>getItems(){
        return items;
               
    }
    public ItemsXML(){
               
    }
    public void setItems(ArrayList<Item> item_list){
        items=item_list;
               
    }   
}
