/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.frontend;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.AbstractMap;
import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.NewCookie;

import com.mycompany.business.Business;
import com.mycompany.business.ItemsXML;
import com.mycompany.business.CartXML;
import com.mycompany.helper.Item;
import com.mycompany.helper.CartItem;
import java.util.stream.Collectors;

@WebServlet(name = "FrontEnd", urlPatterns = {"/FrontEnd"})
public class FrontEnd extends HttpServlet {

    Authenticate autho;

    public FrontEnd() {
        autho = new Authenticate();
    }
    private final String authenticationCookieName = "login_token";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Map.Entry<String, String> isAuthenticated(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = "";
        
        //System.out.println("TOKEN IS");
        try {
            for (Cookie cookie : cookies) {
                //System.out.println("Cookie Name: "+cookie.getName());
                if (cookie.getName().equals(authenticationCookieName)) {
                    token = cookie.getValue();
                }
            }
        } catch (Exception e) {

        }
        if (!token.isEmpty())
           try {
            if (this.autho.verify(token).getKey()) {
                  Map.Entry entry= new  AbstractMap.SimpleEntry<String, String>
                             (token,this.autho.verify(token).getValue());
            return entry;

            } else {
                 Map.Entry entry= new  AbstractMap.SimpleEntry<String, String>("","");
            return entry;
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
        }

       Map.Entry entry= new  AbstractMap.SimpleEntry<String, String>("","");
            return entry;

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String token = isAuthenticated(request).getKey();
        String uname = isAuthenticated(request).getValue();
        
        RequestDispatcher requestDispatcher = null;
        
        String hiddenParam = request.getParameter("pageName");
        request.setAttribute("pagename", hiddenParam);
        
        
        switch (hiddenParam) {
            
            case "login":
                
                String username = request.getParameter("username");
                String password = request.getParameter("psw");
                
                boolean isAuthenticated = Business.isAuthenticated(username, password);
                if (isAuthenticated) {
                       request.setAttribute("username", username);
                    token = autho.createJWT("FrontEnd", username, 100000);

                    Cookie newCookie = new Cookie(authenticationCookieName, token);
                    response.addCookie(newCookie);
                    
                    ItemsXML result = retreiveServicesFromBackend("all_items", "all_items", token);
                    
                    request.setAttribute("itemResults", result);
                    
                    requestDispatcher = request.
                            getRequestDispatcher("frontPage.jsp");

                    requestDispatcher.forward(request, response);

                } else {
                    requestDispatcher = request.
                            getRequestDispatcher("loginFailedPage.html");

                    requestDispatcher.forward(request, response);
                }
                break;
                
            case "redirectLogin":
                requestDispatcher = request.
                            getRequestDispatcher("loginPage.html");

                requestDispatcher.forward(request, response);
                
                break;
                
            case "search":

                ItemsXML result;
                String type = request.getParameter("type");
                String query = request.getParameter("query");
                
                
                request.setAttribute("query", query);
                
                //System.out.println("query is" + query);
                if (token.isEmpty()) {
                    request.setAttribute("username", null);
                    result = retreiveServicesFromBackend(type,query, null);
                    
                } else {
                    request.setAttribute("username", uname);
                    result = retreiveServicesFromBackend(type,query, token);
                }
                
                request.setAttribute("itemResults", result);
                
                requestDispatcher = request.getRequestDispatcher("frontPage.jsp");
                requestDispatcher.forward(request, response);

                break;
            case "viewCart":
                CartXML cart_result;
                cart_result = retreiveInCartServicesFromBackend (uname, token);
                
                String itemids = Integer.toString(cart_result.getCartItems().get(0).getItemId());
                for (int i = 1; i < cart_result.getCartItems().size(); i++){
                    itemids += ","+cart_result.getCartItems().get(i).getItemId();
                }
 
                //System.out.println("itemids: " + itemids);
                ItemsXML items_result;
                items_result = retreiveServicesFromBackend("multipleid",itemids, token);
                
                //testing
                /*
                System.out.println("items result");
                for (Item i : items_result.getItems())
                    System.out.println(i + " : " + i.getItemId());
                */
                request.setAttribute("username", uname);
                request.setAttribute("cartResults", cart_result);
                request.setAttribute("itemResults", items_result);
                
                request.getSession().setAttribute("cartsession", cart_result);
                request.getSession().setAttribute("itemsession", items_result);
                
                requestDispatcher = request.getRequestDispatcher("cartPage.jsp");
                requestDispatcher.forward(request, response);
                
                break;
            case "addcart":
                String itemid = request.getParameter("itemid");
                String quantity = request.getParameter("text_input-"+itemid);
                String itemstock = request.getParameter("item_stock-"+itemid);
               
                //System.out.println("frontend got itemid: " + itemid + "frontend got quantity: "+ quantity);
                
                int quantity_num = Integer.valueOf(quantity);
                int addedquantity = 0;

                addedquantity = retreiveAddCartServicesFromBackend(uname, itemid,quantity, itemstock, false, token);
                
                int success = 0;
                if (addedquantity == quantity_num){
                    //succesfully added the full quantity
                    success = 1;
                } else if (addedquantity > 0){
                    //user tried to add over stock
                    success = addedquantity;
                }
                request.setAttribute("username", uname);
                request.setAttribute("success", success);
                requestDispatcher = request.getRequestDispatcher("addCart.jsp");
                requestDispatcher.forward(request, response);
                break;
            case "removecart":
                String item_id = request.getParameter("itemid");
                int index = Integer.valueOf(request.getParameter("index"));
                CartXML cartinfo = null;
                cartinfo = (CartXML) request.getSession().getAttribute("cartsession");
                
                ItemsXML iteminfo = null;
                iteminfo = (ItemsXML) request.getSession().getAttribute("itemsession");
                
                int outcome = 0;
                outcome = retreiveRemoveCartServicesFromBackend (uname, item_id, token);
                
                if (cartinfo != null && iteminfo != null && outcome == 1) {
                    //System.out.println("cartinfo2: "+ cartinfo.getCartItems().get(0).getItemId());
                    cartinfo.getCartItems().remove(index);
                    iteminfo.getItems().remove(index);
                    
                } 
                
                request.setAttribute("cartResults", cartinfo);
                request.setAttribute("itemResults", iteminfo);
                request.getSession().setAttribute("cartsession", cartinfo);
                request.getSession().setAttribute("itemsession", iteminfo);
                
                request.setAttribute("username", uname);
                request.setAttribute("outcome", outcome);
                requestDispatcher = request.getRequestDispatcher("cartPage.jsp");
                requestDispatcher.forward(request, response);
                break;
            
            case "updatecart": 
                String updateitem_id = request.getParameter("itemid");
                String updatequantity = request.getParameter("text_input-"+updateitem_id);
                String updateitemstock = request.getParameter("item_stock-"+updateitem_id);
                
                ItemsXML updateiteminfo = null;
                updateiteminfo = (ItemsXML) request.getSession().getAttribute("itemsession");
                
                int updateindex = Integer.valueOf(request.getParameter("index"));
                CartXML updatecartinfo = null;
                updatecartinfo = (CartXML) request.getSession().getAttribute("cartsession");

                int updateoutcome = 0;
                updateoutcome = retreiveAddCartServicesFromBackend(uname, updateitem_id,updatequantity, updateitemstock, true, token);
                
                if (updatecartinfo != null && updateoutcome != 0) {
                    //System.out.println("cartinfo2: "+ cartinfo.getCartItems().get(0).getItemId());
                    updatecartinfo.getCartItems().get(updateindex).setItemQuantity(Integer.valueOf(updatequantity));

                } 
                
                request.setAttribute("cartResults", updatecartinfo);
                request.getSession().setAttribute("cartsession", updatecartinfo);
                
                request.setAttribute("itemResults", updateiteminfo);
                
                request.setAttribute("username", uname);
                request.setAttribute("outcome", updateoutcome);
                requestDispatcher = request.getRequestDispatcher("cartPage.jsp");
                requestDispatcher.forward(request, response);
                break;
                
                
        }
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private ItemsXML retreiveServicesFromBackend(String type, String query, String token) {
        //System.out.println("--- retreiveServicesFromBackend has type: "+type+" query:" + query);
        try {
            return (Business.getServices(type, query, token));
        } catch (IOException ex) {
            Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
            return (null);
        }

    }
    
    private CartXML retreiveInCartServicesFromBackend(String username,String token){
        try {
            return (Business.getInCartServices(username, token));
        } catch (IOException ex) {
            Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
            return (null);
        }
    }
    
    private int retreiveAddCartServicesFromBackend (String username, String itemid, String quantity, String itemStock, Boolean isUpdate, String token){
        return (Business.getAddCartServices(username, itemid, quantity, itemStock, isUpdate, token));
    }
    
    private int retreiveRemoveCartServicesFromBackend (String username, String itemid, String token){
        return (Business.getRemoveCartServices(username, itemid, token));
    }
    
}

