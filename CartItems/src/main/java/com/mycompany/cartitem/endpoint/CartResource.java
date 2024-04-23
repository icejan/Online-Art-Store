/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cartitem.endpoint;

import java.util.List;
import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.mycompany.cartitem.business.CartBusiness;

import com.mycompany.cartitem.helper.CartItem;
import com.mycompany.cartitem.business.CartXML;

/**
 * REST Web Service
 *
 * @author students
 */
@Path("cart")
public class CartResource {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SearchResource
     */
    public CartResource() {
    }
    
    @Path("inCart/{userid}")
    @GET
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    public String getXml(@PathParam("userid") String user_id) {
        //System.out.println("cartResource userid: " + user_id);
        
        CartBusiness cart = new CartBusiness();
        CartXML cartInfo = cart.getCartXML(user_id);
        //System.out.println(">>>>>>>>>>>>>>>>>>" + cartInfo);
        /*
        List<CartItem> test = cartInfo.getCartItems();
        
        //for testing
        int index=0;
        for (CartItem i : test){
                System.out.println((index++)+" user: "+ i.getUserId() + " item: "+ i.getItemId() + " quantity: " + i.getItemQuantity());
        }
        */

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(CartXML.class);
        
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
 
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
     
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(cartInfo, sw);
            
            //System.out.println("sw: " + sw.toString());
            return (sw.toString());
    
        } catch (JAXBException ex) {
            Logger.getLogger(CartResource.class.getName()).log(Level.SEVERE, null, ex);
            return("error happened");
        }
    }
    
    /**
     * PUT method for updating or creating an instance of CartResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path("update")
    public String updateCart(@FormParam("itemid") int item_id, @FormParam("userid") int user_id, @FormParam("quantity") int quantity) throws InterruptedException {
        CartBusiness cart = new CartBusiness();
        int bs;
        try {
            bs = cart.addCart (user_id, item_id, quantity);
            return (String.valueOf(bs));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CartResource.class.getName()).log(Level.SEVERE, null, ex);
            return (ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(CartResource.class.getName()).log(Level.SEVERE, null, ex);
               return (ex.getMessage());
        } catch (ServerAddressNotSuppliedException ex) {
            Logger.getLogger(CartResource.class.getName()).log(Level.SEVERE, null, ex);
               return (ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(CartResource.class.getName()).log(Level.SEVERE, null, ex);
               return (ex.getMessage());
        }
        

    }


    
}
