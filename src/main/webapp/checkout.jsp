<%-- 
    Document   : checkout
    Created on : Feb 21, 2024, 12:47:39 AM
    Author     : student
--%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Checkout</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <%
            double subtotal = (double) session.getAttribute("cartsubtotal");
            double tax = (double) session.getAttribute("carttax");
            double shipping = (double) session.getAttribute("cartshipping");
            double total = (double) session.getAttribute("carttotal");
        %>
        <br/>
        <div class="cart_row">
            <div class="cart_row">
                <div class="">
                    <form action="Order" method="post">
                        <table>
                            <tr>
                                <td>
                                    <h2>Shipping information</h2>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <h5 class="login_label">Delivery Address</h5>
                                <input type="text" class="login--txtfield" name="shipment_address" size="40"> 
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <h2>Payment Details</h2>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <h5 class="login_label">Billing Address</h5>
                                    <input type="text" class="login--txtfield" name="billing_address" size="40"> 
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <h5 class="login_label">Card Number</h5>
                                    <input type="text" class="login--txtfield" name="card_num" size="40">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="submit" class="sign_in--btn" value="Confirm Order" >
                                </td>
                            </tr>
                        </table>
    
                    </form>
                </div>
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
                                        total--td">
                                 
                                $<%=subtotal%>

                            </td>
                        </tr>
                        <tr>
                            <td class="total--td">

                                Estimated Tax
                            </td>
                            <td class="align--right
                                        total--td">
                                 
                                $<%=new DecimalFormat("#.##").format(tax)%>

                            </td>
                        </tr>
                        <tr>
                            <td class="total--td">
                                    Estimated Shipping
                            </td>
                            <td class="align--right
                                        total--td">
                                 
                                $<%=new DecimalFormat("#.##").format(shipping)%>
                                
                            </td>
                        </tr>
                        <tr class = total--txt>
                            <td class="total--td">
                                    Total
                            </td>
                            <td class="align--right
                                        total--td
                                       ">
                                
                                $<%=new DecimalFormat("#.##").format(total)%>
                               
                            </td>
                        </tr>
                    </table>
                </div>   
            </div>
        </div>
        
    </body>
</html>

