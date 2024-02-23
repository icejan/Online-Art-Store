
package com.mycompany.lab2;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import com.mycompany.lab2.Item;
/**
 *
 * @author student
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username=(String) request.getParameter("username");
        String password=(String) request.getParameter("password");
        
        //UserInfo uinfo=getUserInfo(username, password);
        UserInfo uinfo = User_CRUD.read(username, password);
        
        if (uinfo==null){
            RequestDispatcher rd= request.getRequestDispatcher("loginfailed.jsp");
            rd.forward(request, response);
        }
        else{
            //ItemInfo item_info = getItemInfo();
            
            request.getSession().setAttribute("uname", uinfo.getFirstName());
            request.getSession().setAttribute("userid", uinfo.getUserId());
            //request.setAttribute("uname", uinfo.getFirstName());
            
            //ArrayList <Item> items = new ArrayList<>();

            ItemInfo item_info = Item_CRUD.read(null, null);
            
            request.getSession().setAttribute("itemsInfo", item_info.getItems());
            
            //for testing
            int index = 0;
            for (Item i : item_info.getItems()){
                System.out.println((index++)+": "+ i.getItemName());
            }
            //

            RequestDispatcher rd= request.getRequestDispatcher("useritems.jsp");
            rd.forward(request, response);
            
        }   
     
    }
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

          doPost(request, response);

        
    }

}
