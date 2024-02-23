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

/**
 *
 * @author student
 */
@WebServlet(name = "Search", urlPatterns = {"/Search"})
public class Search extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String search_prompt = (String) request.getParameter("search_prompt");
        String input_type = (String) request.getParameter("type");
        
        
        
        
        
        ItemInfo item_info = null;
        
        if (input_type.equals("keyword")){
            System.out.print("going to search keyword");
            
            request.getSession().setAttribute("uprompt", search_prompt);
            
            item_info = Item_CRUD.read("keyword", search_prompt);

            
        } else if (input_type.equals("browse_categories")){
            System.out.print("going to search categories");
            
            
            
        } else if (input_type.equals("best_sellers")){
            
           System.out.print("going to search best sellers");
           
           
        } else if (input_type.equals("new_arrivals")){
            System.out.print("going to search new arrivals");
            
            item_info = Item_CRUD.read("releasedate", search_prompt);
           
        } 
            request.getSession().setAttribute("search_type", input_type);
            request.getSession().setAttribute("itemsInfo", item_info.getItems());
            
            //for testing
            int index = 0;
            for (Item i : item_info.getItems()){
                System.out.println((index++)+": "+ i.getItemName());
            }
            //
            
            RequestDispatcher rd= request.getRequestDispatcher("usersearch.jsp");
            rd.forward(request, response);
        }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

          doPost(request, response);

        
    }
}

     
    


