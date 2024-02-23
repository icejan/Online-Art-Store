/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab2;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
/**
 *
 * @author student
 */
@WebServlet(name = "addCart", urlPatterns = {"/addCart"})
public class AddCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String itemid =(String) request.getParameter("item_id_name");
        
        String textfield_name = "text_input-"+itemid;
        String quantity = (String) request.getParameter(textfield_name);
        
        System.out.println("item id you want to add to cart: " + itemid);
        System.out.println("quantity you want to add to cart: " + quantity);
        
        int userid = (Integer) request.getSession().getAttribute("userid");
        //ArrayList <Item> browse_items = (ArrayList) request.getSession().getAttribute("itemsInfo");
        
        ItemInfo iteminfo = Item_CRUD.read("id", itemid);
        
        try {
            
            int item_id = Integer.parseInt(itemid);
            int input_quantity = Integer.parseInt(quantity);
            boolean output = false;
            
            CartInfo cartinfo = User_Cart_CRUD.read(userid, item_id);
            
            int itemstock = iteminfo.getItems().get(0).getItemStock();
            
            
            if(cartinfo == null) {
                //new item to be added in cart
                System.out.println("its a new item to be added to cart");
                
                if (itemstock >= input_quantity){
                    //public static boolean create(int user_id, int item_id, int quantity)
                    output = User_Cart_CRUD.create(userid, item_id, input_quantity);
                } else {
                    //user tried to add more than stock
                    System.out.println("You tried to add " + input_quantity + " but theres only stock of" + itemstock);
                }
                
            } else {
                //already in cart
                System.out.println("item exists in cart, add to quantity");
                
                
                int init_quantity = cartinfo.getCartItems().get(0).getItemQuantity();
                int total_quantity = init_quantity + input_quantity;
                
                System.out.println("total quantity:" + total_quantity);
                
                if (itemstock >= total_quantity) {
                    //public static boolean update(int user_id, int item_id, int quantity)
                    output = User_Cart_CRUD.update(userid, item_id, total_quantity);
                } else {
                    //user tried to add more than stock
                    System.out.println("You tried to add " + input_quantity 
                            + " to your quantity in cart "+ init_quantity
                            + " but theres only stock of" + itemstock);
                }
 
            }
            
            if (output = false){
                System.out.println("Couldnt add item to cart...");
            } else {
                System.out.println("Succesfully added to cart.");
            }
            
        }catch (NumberFormatException e) {
            System.out.println(e);
        }
        
        //show items
        ItemInfo all_item = Item_CRUD.read(null, null);
        
        
        request.setAttribute("addeditem", iteminfo.getItems().get(0).getItemName());
        //request.setAttribute("itemsInfo", browse_items);
        
        
        RequestDispatcher rd= request.getRequestDispatcher("addcart.jsp");
        rd.forward(request, response);
        /*
        int userid = (int) request.getSession().getAttribute("userid");
        //UserInfo uinfo=getUserInfo(username, password);
        
        CartInfo cart_item = User_Cart_CRUD.read(userid);
        
        if (cart_item==null){
            //create a new row in cart database table
            RequestDispatcher rd= request.getRequestDispatcher("loginfailed.jsp");
            rd.forward(request, response);
        }
        else{
            //item exists in cart, so just add to quantity

            ItemInfo item_info = Item_CRUD.read(null, null);
            
            request.setAttribute("itemsInfo", item_info.getItems());
            
            //for testing
            int index = 0;
            for (Item i : item_info.getItems()){
                System.out.println((index++)+": "+ i.getItemName());
            }
            //

            RequestDispatcher rd= request.getRequestDispatcher("useritems.jsp");
            rd.forward(request, response);
            
        }  
*/
     
    }
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

          doPost(request, response);

        
    }

}
