<!--

    Document   : usercart
    Created on : Feb 4, 2024, 10:55:49 PM
    Author     : student
-->

<%@page import="java.text.DecimalFormat"%>
<%@page import="com.mycompany.lab2.Cart_Item"%>
<%@page import="com.mycompany.lab2.Item"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<!DOCTYPE html
<html>
    <head>
        <title>Art Supply Store</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css">
        <script src="quantity.js"></script>
    </head>
    <body>
        
        <% 
        ArrayList<Cart_Item> cart_items = (ArrayList)session.getAttribute("cartinfo");
        ArrayList<Item> items = (ArrayList)session.getAttribute("item_cart_info");
        %>                            
        
        
        <section id="nav-store"> 
        <nav>
            <div class="search__bar--form">
                <form action="Search" method="post">
                    <input type="hidden" name="type" value="keyword">
                    <input type="text" name="search_prompt" class="search--txtbox" id="search">
                    <input type="submit" class="search--btn" value="search">
                </form>
            </div>
            <div class="
            personal_logo
            centred
            ">
                <img src="resources/art_supply_store_logo.png" class="store_logo__picture">
            </div>
            <ul class="nav__link--list">
                <li class="nav__link">
                    
                    Welcome,
                    
                    <%=session.getAttribute("uname")%>
                    
                </li>
                <li class="nav__link"> 
                    <form action="ViewCart" method="post">
                        <input type="submit" class="
                                                    nav__link--anchor
                                                    nav--btn" value="Cart">
                    </form>
                </li>
            </ul>
        </nav>
        </section>
        <section id="menu">
            <div class = "menu-container">
                <li class="menu--list">
                    <form action="=Search" method="post">
                        <input type="hidden" name="type" value="browse_categories">
                        <input type="submit" class="
                                                   menu--anchor
                                                  nav--btn" value="Browse Categories">
                    </form>
                </li>
                <li class="menu--list">
                    <form action="Search" method="post">
                        <input type="hidden" name="type" value="best_sellers">
                        <input type="submit" class="
                                                    menu--anchor
                                                    nav--btn" value="Best Sellers">
                    </form>
                </li>
                <li class="menu--list">
                    <form action="Search" method="post">
                        <input type="hidden" name="type" value="new_arrivals">
                        <input type="submit" class="
                                                    menu--anchor
                                                    nav--btn" value="New Arrivals">
                    </form>
                </li>
            </div>
        </section>
        <section class="cart">
        <div class="container">
            <h2 class="items_title">
                Your Cart
            </h2>
            <div class="cart_row">
                <div class="cart__list">
                    <form action="removeCart" method="post">
                        
                        <% 
                            
                            double total_item_price = 0;
                            //double total_num_items = 0;
                            
                            //int i = 0;
                            for(int i = 0; i < cart_items.size(); i++){
                                Cart_Item cart_item = cart_items.get(i);
                                Item item = items.get(i);
                                String item_img_src = "resources/"+item.getItemId() + ".jpg";
                                total_item_price = item.getItemPrice()*cart_item.getItemQuantity();
                                
                        %>
                        <% 
                            
                        %>            
                        
                        
                        <div class="cart_items">
                            <table class="cart_item">
                                <tr class="cart--tr">
                                    <td class="">
                                        <img src="<%=item_img_src%>" class = "item_picture">
                                        
                                    </td>
                                    <td class="cart--td">
                                        <li class="cart--li">
                                            
                                            <%=items.get(i).getItemName()%>
                                            
                                        </li>
                                        <li class="cart--li">
                                            
                                            <b>Item #: </b><%=item.getItemId()%>
                                            
                                        </li>
                                        <li class="cart--li">
                                            <input type="submit" class="nav--btn" value="Remove">
                                        </li>
                                            
                                    </td>
                                    <td class="cart--td 
                                                price--txt">
                                        <li class="cart--li">
                                            <h4 class="checkout_price--txt">$
                                                
                                                <%=new DecimalFormat("#.##").format(total_item_price)%>
                                                
                                            </h4>
                                            <h5 class="each_price--txt">Each: $
                                                
                                                <%=new DecimalFormat("#.##").format(item.getItemPrice())%>
                                                
                                            </h5>

                                        </li>
                                        <li class="cart_price--li">
                                            
                                            <input type="button" class="change_quantity--btn" onclick="decreaseValue()" value="-" />
                                            <input type="text" id="addCart_quantity" name="quantity" class="quantity--txtbox" id="quantity" value="<%=cart_item.getItemQuantity()%>">
                                            <input type="button" class="change_quantity--btn" onclick="incrementValue()" value="+" />
                                        </li>
                                        <li class="cart_price--li">
                                            <input type="submit" class="update--btn" value="Update">
                                        </li>
                                    </td>
                                </tr>  
                            </table>
            
                        </div>
                         
                        <%
                            //subtotal += total_item_price;
                            //total_num_items += item.getItemQuantity();
                              
                            } //end for loop%>
                        <%
                            double subtotal = (double) session.getAttribute("cartsubtotal");
                            double tax = (double) session.getAttribute("carttax");
                            double shipping = (double) session.getAttribute("cartshipping");
                            double total = (double) session.getAttribute("carttotal");
                        %>           
                         
                         
                    </form>
                </div>
                <div class="order_summary">
                    <div class="order_total">
                        <h2 class="items_title">
                            Order Summary
                        </h2>
                        <table class="total--table">
                            <tr >
                                <td class="total--td">
                                    Subtotal
                                </td>
                                <td class="align--right
                                            total--td">$
                                    
                                    <%=subtotal%>
                                    
                                </td>
                            </tr>
                            <tr>
                                <td class="total--td">
                                     
                                    Estimated Tax
                                </td>
                                <td class="align--right
                                            total--td">$
                                    
                                    <%=new DecimalFormat("#.##").format(tax)%>
                                    
                                </td>
                            </tr>
                            <tr>
                                <td class="total--td">
                                        Estimated Shipping
                                </td>
                                <td class="align--right
                                            total--td">$
                                     
                                    <%=new DecimalFormat("#.##").format(shipping)%>
                                    
                                </td>
                            </tr>
                            <tr class = total--txt>
                                <td class="total--td">
                                        Total
                                </td>
                                <td class="align--right
                                            total--td
                                           ">$
                                    
                                    
                                    <%=new DecimalFormat("#.##").format(total)%>
                                    
                                    
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="order_checkout">
                        <form action="Checkout" method="post">
                            <input type="submit" class="checkout--btn" value="Checkout">
                        </form>
                    </div>        
                </div>
            </div>
        </div>
    </section>
   

    </body>
</html>
