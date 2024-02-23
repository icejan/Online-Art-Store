/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "Order", urlPatterns = {"/Order"})
public class Order extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
            int userid = (Integer) request.getSession().getAttribute("userid");
            
            String shimentpaddress =(String) request.getParameter("shipment_address");
            String billingaddress =(String) request.getParameter("billing_address");
            String cardnum =(String) request.getParameter("card_num");
            
            ArrayList <Cart_Item> items = (ArrayList) request.getSession().getAttribute("cartinfo");
            
            //make new row into shipment company table
            //eg. 6xxx, UPS, "999888777"
            //return shipment id
            
            //make new row into billing table
            //eg. 3xxx, todays date, billingaddress from above, cardnum from above, userid from above
            //return billing id
            
            //make new row into checkout table
            //eg 4xxx, todays date, shipping address from above, userid from above, shipment id from above, billing id from above
            
            //for EACH item in cart
                //make new row into purchased items table 
                // 7xxx, quantity from cart info, purchase price=price*quantity, item id from cart, checkout id from above
                //update item stock using item_CRUD update(itemid, stock-quantity)
                //clear the user's cart by using user_cart_CRUD delete(item_id, user_id)

            
            RequestDispatcher rd= request.getRequestDispatcher("checkoutreceipt.jsp");
            rd.forward(request, response);
            
            
        }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

          doPost(request, response);

        
    }

}
