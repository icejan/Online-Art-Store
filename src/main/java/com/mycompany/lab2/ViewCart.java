/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author student
 */
@WebServlet(name = "ViewCart", urlPatterns = {"/ViewCart"})
public class ViewCart extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    // to be completed. For now just using placeholders
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //String username=(String) request.getSession().getAttribute("uname");
        int userid = (Integer) request.getSession().getAttribute("userid");
        
        
        CartInfo cartinfo = User_Cart_CRUD.read(userid, 0);
        //UserInfo uinfo_cart = getUserInfo(username, password);
        
        //request.getSession().setAttribute("uname_cart", username);
            //request.getSession().setAttribute("userInfo_cart", uinfo_cart.getItems());
        
            
        request.getSession().setAttribute("cartinfo", cartinfo.getCartItems());
        
       
        
        double subtotal = 0;
        double tax = 0;
        double shipping = 8;
        double total = 0;
        
            /*
        ItemInfo item_info = Item_CRUD.read(null, null);
            
            request.setAttribute("itemsInfo", item_info.getItems());
            
            //for testing
            int index = 0;
            for (Item i : item_info.getItems()){
                System.out.println((index++)+": "+ i.getItemName());
            }
        
        */
            int index = 0;
            
            ItemInfo item_info = new ItemInfo();
             
            for (Cart_Item i : cartinfo.getCartItems()){
                //for each cart item
                
                String id = Integer.toString(i.getItemId());

                System.out.println("og item id is" +i.getItemId()+"\n id:" + id );
                
                Item cart_item = Item_CRUD.read("id", id).getItems().get(0);
                /*
                int new_id = cart_item.getItemId();
                String new_name = cart_item.getItemName();
                double new_price = cart_item.getItemPrice();
                int new_stock = cart_item.getItemStock();
                Date new_date = cart_item.getItemListDate();
                int new_brand = cart_item.getItemBrandId();
                */
                //public Item(int item_id, String item_name, double item_price, int item_stock, Date item_list_date, int item_brand_id) {
                item_info.addItem(cart_item);
                
                System.out.println((index++)+" of cart itemss: "+ cart_item.getItemName() + "quantity:"+ i.getItemQuantity()); //for testing
                
                subtotal += cart_item.getItemPrice()*i.getItemQuantity();
                shipping += i.getItemQuantity()*0.5; //a rate of 50 cents per item
            }
            
            tax = subtotal*0.13; //13% tax
            total = subtotal +tax+shipping;
            
            int count = 0;
            for (Item i : item_info.getItems()){
                System.out.println((count++)+" of item info: "+ i.getItemName());
            }
            
            request.getSession().setAttribute("item_cart_info", item_info.getItems());
            request.getSession().setAttribute("cartsubtotal", subtotal);
            request.getSession().setAttribute("carttax", tax);
            request.getSession().setAttribute("cartshipping", shipping);
            request.getSession().setAttribute("carttotal", total);
        
  
        RequestDispatcher rd= request.getRequestDispatcher("usercart.jsp");
        rd.forward(request, response);
        
        
     
    }
    
    
    private UserInfo getCartInfo(String uname, String password) {
        /**
         * to be completed. For now, we just return a user info object that has a default book in a default date;
         * This method must return null when user name and password is incorrect
         * otherwise it must return an object containing all books that have been borrowed by the user, in addition to user information like name, address, ...
         */
        
        
        //UserInfo uf = new UserInfo();
  
         //uf.addItem(new Item("Canson® Universal™ Sketch Pad", 111, 19.99, false));
        //uf.addItem(new Item("Zebra Mildliner™ Double Ended Creative Marker Set, 15ct.", 222, 39.99, true));
        //uf.addItem(new Item("Wirebound Notebook Plain Dark Grey", 333, 2.90, true));
        
        
       
        return null;
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

          doPost(request, response);

        
    }
}
