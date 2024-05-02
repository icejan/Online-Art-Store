<%-- 
    Document   : addCart
    Created on : Apr 9, 2024, 10:02:44 PM
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
        int success = (Integer) request.getAttribute("success");
        
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
            <h2 class="items_title">
            <% if (success == 1) {%>
                Successfully added item name to cart.
            <%} else if (success == 0) {%>
                Oops there was an error while adding the item to your cart.
            <%} else {%>
                There are only <%=success%> of item name left and they have been added to your cart. 
                Please checkout as soon as possible as there is a limited quantity.
            <%}%>
            </h2>
    </body>
</html>
