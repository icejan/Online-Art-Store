<%-- 
    Document   : usersearch
    Created on : Feb 5, 2024, 12:48:27 PM
    Author     : student
--%>
<!--
<h1>You successfully searched for: <%//=session.getAttribute("uprompt")%></h1>
-->
<%@page import="java.text.DecimalFormat"%>
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
        ArrayList<Item> items= (ArrayList)session.getAttribute("itemsInfo");

        String searchtype = (String) session.getAttribute("search_type");
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
                    
                    Welcome, <%=session.getAttribute("uname")%> 
                    
                    
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
        <section id="items">
        <div class="container">
            <div class="row">
                <h2 class="items_title">
                    <% if (searchtype.equals("keyword")) {%>
                        <%
                            if (items.isEmpty()){
                        %>
                            We couldn't find a match for '<%=session.getAttribute("uprompt")%>'. Please try another search.
                        <%
                            } else {
                        %>
                            Search results for '<%=session.getAttribute("uprompt")%>'
                        <%  }
                    } else if (searchtype.equals("new_arrivals")) {%>
                        New Arrivals
                  <%}%>
                </h2>
                
                <div class="items__list">
                    
                    <% for(Item item: items){
                            String item_img_src = "resources/"+item.getItemId() + ".jpg";
                            //String item_id_name = "text_input-"+item.getItemId();
                            String text_field_name = "text_input-"+item.getItemId();
                        %>
                    <li class = "items_form--list">
                        <form action="addCart" class="items--form" method="post">
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
                                        <div class="items_quantity--items">
                                            <input type="hidden" name="item_id_name" value="<%=item.getItemId()%>"/>
                                            <input type="button" class="change_quantity--btn" onclick="decreaseValue()" value="-" />
                                            <input type="text" name="<%=text_field_name%>" class="quantity--txtbox" value="1">
                                            <input type="button" class="change_quantity--btn" onclick="incrementValue()" value="+" />
                                            <input type="submit" class="add_cart--btn" value="Add to Cart" >
                                        </div>
                                        
                                    </td>
                                    
                                </tr>
                                
                            </table>
                        </div>
                    </form>
                            
                    </li>

                        
                     <%} //end bracket of for loop%> 
                </div>

            </div>
        </div>
        
    </section>
        
    </body>
</html>


