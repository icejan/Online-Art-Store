

<%@page import="java.text.DecimalFormat"%>
<%@page import="com.mycompany.helper.CartItem"%>
<%@page import="com.mycompany.helper.Item"%>
<%@page import="com.mycompany.business.CartXML"%>
<%@page import="com.mycompany.business.ItemsXML"%>
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
            String username = (String) request.getAttribute("username");
            CartXML items = (CartXML)request.getAttribute("cartResults");
            ItemsXML iteminfos = (ItemsXML)request.getAttribute("itemResults");
        //ArrayList<Item> items = (ArrayList)session.getAttribute("item_cart_info");
        %>                            
        <section id="nav-store"> 
        <nav>
            <div class="search__bar--form">
                <form action="FrontEnd" method="post">
                    <input type="hidden" name="pageName" value="search">
                    <input type="hidden" name="type" value="keyword">
                    <input type="text" name="query" class="search--txtbox">
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
                <%if (username != null) {%>
                <li class="nav__link">
                    Welcome, <%=username%>  
                </li>
                <li class="nav__link"> 
                    <form action="FrontEnd" method="post">
                        <input type="hidden" name="pageName" value="viewCart"/>
                        <input type="submit" class="
                                                    nav__link--anchor
                                                    nav--btn" value="Cart">
                    </form>
                </li>
                <%} else {%>
                <li class="nav__link"> 
                    <form action="FrontEnd" method="post">
                        <input type="hidden" name="pageName" value="redirectLogin"/>
                        <input type="submit" class="
                                                    nav__link--anchor
                                                    nav--btn" value="Welcome, Sign in">
                    </form>
                </li>
                <%}%>
            </ul>
        </nav>
        </section>
        <section id="menu">
            <div class = "menu-container">
                <li class="menu--list">
                    <form action="FrontEnd" method="post">
                        <input type="hidden" name="pageName" value="category">
                        <input type="submit" class="
                                                   menu--anchor
                                                  nav--btn" value="Browse Categories">
                    </form>
                </li>
                <li class="menu--list">
                    <form action="FrontEnd" method="post">
                        <input type="hidden" name="pageName" value="search">
                        <input type="hidden" name="type" value="best_sellers">
                        <input type="hidden" name="query" value="best_sellers">
                        <input type="submit" class="
                                                    menu--anchor
                                                    nav--btn" value="Best Sellers">
                    </form>
                </li>
                <li class="menu--list">
                    <form action="FrontEnd" method="post">
                        <input type="hidden" name="pageName" value="search">
                        <input type="hidden" name="type" value="new_arrivals">
                        <input type="hidden" name="query" value="new_arrivals">
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
                    
                        
                        <% 
                            
                            double total_item_price = 0;
                            double total_num_items = 0;
                            double subtotal = 0;
                            String quantity_textfield, stock_textfield;
                            int i = 0;
                            if (iteminfos.getItems() != null && !iteminfos.getItems().isEmpty()){
                                for(Item item: iteminfos.getItems()){
                                CartItem cart_item = items.getCartItems().get(i);
                                String item_img_src = "resources/"+item.getItemId() + ".jpg";
                                
                                total_item_price = item.getItemPrice()*cart_item.getItemQuantity();
                                 quantity_textfield = "text_input-"+ item.getItemId();
                                 stock_textfield = "item_stock-"+item.getItemId();
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
                                            
                                            <%=item.getItemName()%>
                                            
                                        </li>
                                        <li class="cart--li">
                                            
                                            <b>Item #: </b><%=item.getItemId()%>
                                            
                                        </li>
                                        <li class="cart--li">
                                            <form action="FrontEnd" method="post">
                                                <input type="hidden" name="pageName" value="removecart">
                                                <input type="hidden" name="index" value="<%=i%>">
                                                <input type="hidden" name="itemid" value="<%=item.getItemId()%>"/>
                                                <input type="submit" class="nav--btn" value="Remove">
                                            </form>
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
                                        <form action="FrontEnd" method="post">
                                        <li class="cart_price--li">
                                            
                                            <input type="button" class="change_quantity--btn" onclick="decreaseValue()" value="-" />
                                            <input type="text" id="addCart_quantity" name="<%=quantity_textfield%>" class="quantity--txtbox" value="<%=cart_item.getItemQuantity()%>">
                                            <input type="button" class="change_quantity--btn" onclick="incrementValue()" value="+" />
                                        </li>
                                        <li class="cart_price--li">
                                            
                                                <input type="hidden" name="pageName" value="updatecart">
                                                <input type="hidden" name="itemid" value="<%=item.getItemId()%>"/>
                                                <input type="hidden" name="<%=stock_textfield%>" value="<%=item.getItemStock()%>"/>
                                                <input type="hidden" name="index" value="<%=i%>">
                                                <input type="submit" class="update--btn" value="Update">
                                            
                                        </li>
                                        </form>   
                                    </td>
                                </tr>  
                            </table>
            
                        </div>
                         
                        <%
                            subtotal += total_item_price;
                            total_num_items += cart_item.getItemQuantity();
                              i++;
                            } //end for loop
                        }//end if statement
                        %>
                            
                        <%

                            double shipping = 10 + total_num_items*2;
                            double tax = (subtotal+shipping)*0.13;
                            double total = subtotal+shipping+tax;
                            
                        %>           
                         
                         
                    
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
                                    
                                    <%=new DecimalFormat("#.##").format(subtotal)%>
                                    
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
