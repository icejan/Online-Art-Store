
package com.mycompany.business;

import com.mycompany.helper.Account;
import com.mycompany.persistence.Account_CRUD;

import java.util.ArrayList;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Form;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.IOUtils;

import java.util.Arrays;

import com.mycompany.helper.Item;
import com.mycompany.business.ItemsXML;
import javax.ws.rs.client.Entity;

public class Business {
    public static boolean isAuthenticated(String username, String password) {
        //System.out.println("username:" + username);
        //System.out.println("password:" + password);
        Account uinfo = Account_CRUD.read(username, password);
        
        if (uinfo==null){
            System.out.println("username and password match do not match in business");
            return false;
        } else {
            System.out.println("username and password do match in business");
            return true;
        }
        
    }
    public static ItemsXML getServices(String type, String query, String token) throws IOException {
        Client searchclient = ClientBuilder.newClient();
        String searchService = System.getenv("searchService");//"localhost:8080";//
        WebTarget searchwebTarget
                = searchclient.target("http://"+searchService+"/SearchItems/webresources/search");
        InputStream is
                = searchwebTarget.path(query).queryParam("type", type).request(MediaType.APPLICATION_XML).get(InputStream.class);
        String xml = IOUtils.toString(is, "utf-8");
        //System.out.println("Business String xml is" + xml);
        ItemsXML items = itemxmltoObjects(xml);
        
        return (items);

    }
    
    public static CartXML getInCartServices(String username, String token) throws IOException {
        
        String userid = Account_CRUD.getId(username);
        //System.out.println("userid is" + userid);
        Client searchclient = ClientBuilder.newClient();
        String viewCartService = System.getenv("viewCartService");//"localhost:8080"; //
        WebTarget viewcartwebTarget
                = searchclient.target("http://"+viewCartService+"/CartItems/webresources/cart/inCart");
        InputStream is
                = viewcartwebTarget.path(userid).request(MediaType.APPLICATION_XML).get(InputStream.class);
        String xml = IOUtils.toString(is, "utf-8");
        //System.out.println("Business String xml is" + xml);
        CartXML cartitems = cartxmltoObjects(xml);
        /*
        for (int i = 0; i< cartitems.getCartItems().size(); i++){
            System.out.println("getincart services cartitems "+ i + " : " +cartitems.getCartItems().get(i).getItemId());
        }
        */
        return (cartitems);
    }
    
    public static int getAddCartServices(String username, String itemid, String quantity, String itemStock, Boolean isUpdate, String token) {
        System.out.println("getaddcartservices username:" + username+" itemid: "+itemid+" quantity: "+ quantity+" itemstock: "+ itemStock+" isuodate: "+ isUpdate);
        String userid = Account_CRUD.getId(username);
        
        //System.out.println("userid is" + userid);
        String action = "add";
        if (isUpdate){
            action = "update";
        }
        
        Client addcartclient = ClientBuilder.newClient();
        String addCartService = System.getenv("addCartService");//"localhost:8080";//
        WebTarget addcartwebTarget
                = addcartclient.target("http://"+addCartService+"/CartItems/webresources/cart/"+action);
        
        //@FormParam("itemid") int item_id, @FormParam("userid") int user_id, @FormParam("quantity")
        Form data = new Form();
        data.param("userid", userid);
        data.param("itemid", itemid);
        data.param("quantity", quantity);
        data.param("itemstock", itemStock);
        
        Response response = addcartwebTarget.request(MediaType.TEXT_HTML).post(Entity.form(data));
        String response_entity = response.readEntity(String.class);
        //System.out.println("response string: " + response_entity);
        
        return Integer.valueOf(response_entity);
        
    }
    
    public static int getRemoveCartServices(String username, String itemid, String token) {
        System.out.println("getaddcartservices username:" + username+" itemid: "+itemid);
        String userid = Account_CRUD.getId(username);
        
        //System.out.println("userid is" + userid);
        
        Client addcartclient = ClientBuilder.newClient();
        String removeCartService = System.getenv("addCartService");//"localhost:8080";//
        WebTarget removecartwebTarget
                = addcartclient.target("http://"+removeCartService+"/CartItems/webresources/cart/remove");
        
        //@FormParam("itemid") int item_id, @FormParam("userid") int user_id, @FormParam("quantity")
        Form data = new Form();
        data.param("userid", userid);
        data.param("itemid", itemid);
        
        Response response = removecartwebTarget.request(MediaType.TEXT_HTML).post(Entity.form(data));
        String response_entity = response.readEntity(String.class);
        //System.out.println("response string: " + response_entity);
        
        return Integer.valueOf(response_entity);
        
    }
    
    private static ItemsXML itemxmltoObjects(String xml) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(ItemsXML.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            ItemsXML items = (ItemsXML) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            
            return items;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static CartXML cartxmltoObjects(String xml) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(CartXML.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            CartXML items = (CartXML) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            
            return items;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    

}

