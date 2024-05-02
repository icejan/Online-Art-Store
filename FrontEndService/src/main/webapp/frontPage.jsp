<%-- 
    Document   : usersearch
    Created on : Feb 5, 2024, 12:48:27 PM
    Author     : student
--%>
<!--
<h1>You successfully searched for: <%//=session.getAttribute("uprompt")%></h1>
-->
<%@page import="java.util.Arrays"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.helper.Item"%>
<%@page import="com.mycompany.business.ItemsXML"%>
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
        String pagename = (String) request.getAttribute("pagename");
        String query = (String) request.getAttribute("query");
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
        <section id="items">
        <div class="container">
            <h2 class="items_title">
                    <% 
                        ItemsXML items = (ItemsXML) request.getAttribute("itemResults");
                        
                        if (pagename.equals("search")) {%>
                        <%
                            if (query.equals("new_arrivals")) {%>
                              New Arrivals
                        <%
                            } else if (query.equals("best_sellers")){
                        %>
                            Best Sellers
                        <%
                            } else if (query.equals("browse_categories")) {
                        %>
                            Browse Categories
                        <% } else if (items.getItems() == null || items.getItems().isEmpty()){
                        %>
                            We couldn't find a match for '<%=query%>'. Please try another search.
                        <%
                            } else {
                        %>
                            Search results for '<%=query%>'
                        <%  }%>
                        
                        <%} else if (pagename.equals("login")) {%>
                        Browse Items
                        <%}%>
                </h2>
                
            <div class="row">
                
                <div class="items__list">
                    
                    <% 
                    if (items.getItems() != null && !items.getItems().isEmpty()){
                        for(Item item: items.getItems()){
                            String item_img_src = "resources/"+item.getItemId() + ".jpg";
                            //String item_id_name = "text_input-"+item.getItemId();
                            String text_field_name = "text_input-"+item.getItemId();
                            String stock_field_name = "item_stock-"+item.getItemId();
                        %>
                    <li class = "items_form--list">
                        <%if (username != null) {%>
                        <form action="FrontEnd" class="items--form" method="post">
                            <input type="hidden" name="pageName" value="addcart">
                         <%} else {%>
                        <form action="FrontEnd" method="post">
                        <input type="hidden" name="pageName" value="redirectLogin"/>
                         <%}%>
                        <div class="item">
                            <table class="itmes--table">
                                <tr>
                                    <td class="items--td">
                                        <li class="item--list">
                                            <img src= "<%=item_img_src%>" class = "item_picture">
                                            
                                        </li>
                                        <li class="item--list">

                                            <%=item.getItemName()%>
                                     
                                        </li>
                                        <li class="item--list 
                                                    price--txt">
                                            
                                            $<%=new DecimalFormat("#.##").format(item.getItemPrice())%>
                                            
                                            
                                        </li>
                                    </td>
                                </tr>
                                <tr>
                                    <td> 
                                        <%if (item.getItemStock() > 0) {%>
                                        <div class="items_quantity--items">
                                            <input type="hidden" name="itemid" value="<%=item.getItemId()%>"/>
                                            <input type="button" class="change_quantity--btn" onclick="decreaseValue()" value="-" />
                                            <input type="text" name="<%=text_field_name%>" class="quantity--txtbox" value="1">
                                            <input type="button" class="change_quantity--btn" onclick="incrementValue()" value="+" />
                                            <input type="hidden" name="<%=stock_field_name%>" value="<%=item.getItemStock()%>"/>
                                            <input type="submit" class="add_cart--btn" value="Add to Cart" >
                                        </div>
                                        <%} else {%>
                                            <p>Out of Stock</p>
                                        <%}%>
                                    </td>
                                    
                                </tr>
                                
                            </table>
                        </div>
                    </form>
                            
                    </li>

                        
                     <%} //end bracket of for loop
                    } //end if statement%> 
                </div>

            </div>
        </div>
        
    </section>
        
    </body>
</html>


