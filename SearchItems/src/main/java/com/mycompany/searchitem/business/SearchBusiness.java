package com.mycompany.searchitem.business;

import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.searchitem.helper.Item;
import com.mycompany.searchitem.persistence.Item_CRUD;
import com.mycompany.searchitem.business.ItemsXML;

public class SearchBusiness {
    
    public ItemsXML getItemsByKeyword(String keyword){
        //System.out.println("get items by keyword:" + keyword);
        ArrayList <Item> items;
        
        if (keyword.equals("all_items")){
            items = Item_CRUD.searchForItems(null, null);
        } else if (keyword.equals("new_arrivals")){
            items = Item_CRUD.searchForItems("releasedate", null);
        } else {
            items = Item_CRUD.searchForItems("keyword", keyword);
        }
        
        /*for testing
        int index=0;
        for (Item i : items){
                System.out.println((index++)+": "+ i.getItemName());
        }
        */
        ItemsXML itemInfos = new ItemsXML();
        itemInfos.setItems(items);
        
        return (itemInfos);
    }
}
