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
@WebServlet(name = "Checkout", urlPatterns = {"/Checkout"})
public class Checkout extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
             ArrayList <Cart_Item> items = (ArrayList) request.getSession().getAttribute("cartinfo");
        
             ArrayList <Cart_Item> out_of_stock;
            
            //for testing
            
            int index = 0;
            boolean check_stock = true;
            
            for (Cart_Item i : items){
                String id = Integer.toString(i.getItemId());
                
                Item cart_item = Item_CRUD.read("id", id).getItems().get(0);
                
                //System.out.println("checkout check item "+ (index++)+": "+ i.getItemName());
                if (i.getItemQuantity() > cart_item.getItemStock()){
                    check_stock = false;
                }
                
            }
            //
            if (check_stock == false) {
                System.out.println("item not enough stock, go to checkoutFailed.jsp");
                RequestDispatcher rd= request.getRequestDispatcher("checkoutfailed.jsp");
                //to be completed
                rd.forward(request, response);
                 
            } else {
                 System.out.println("item has enough stock, proceed to checkout.jsp ");
                 
                 RequestDispatcher rd= request.getRequestDispatcher("checkout.jsp");
                rd.forward(request, response);
                 
            }

            RequestDispatcher rd= request.getRequestDispatcher("checkout.jsp");
                rd.forward(request, response);
            
        }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

          doPost(request, response);

        
    }
}
