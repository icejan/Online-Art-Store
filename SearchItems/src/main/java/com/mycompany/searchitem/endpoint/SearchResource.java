/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.searchitem.endpoint;

import java.io.StringWriter;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.mycompany.searchitem.business.SearchBusiness;
import com.mycompany.searchitem.business.ItemsXML;
import com.mycompany.searchitem.helper.Item;

import com.mycompany.searchitem.business.Messaging; //fortestings
/**
 * REST Web Service
 *
 * @author student
 */
@Path("search/{query}")
public class SearchResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SearchResource
     */
    public SearchResource() {
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    public String getXml(@PathParam("query") String query) {

        //ADDCART:2:1 => id:quantity
        //Messaging.Receiving_Events_Store("ADDCART:2:1");
        //System.out.println("query:" + query);
        
        SearchBusiness search= new SearchBusiness();
        ItemsXML items;
        
        
        items = search.getItemsByKeyword(query);
        
        
        //System.out.println(">>>>>>>>>>>>>>>>>>" + items);
        
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(ItemsXML.class);
        
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
 
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
     
        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(items, sw);
     
        return (sw.toString());
    
        } catch (JAXBException ex) {
            Logger.getLogger(SearchResource.class.getName()).log(Level.SEVERE, null, ex);
            return("error happened");
        }
        
    }

    /**
     * PUT method for updating or creating an instance of SearchResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    
}
