<%-- 
    Document   : LoginFailed
    Created on : Feb 20, 2024, 12:00:06 AM
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Sign In</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <section class="logo">
            <nav>
                <div class="personal_logo
                 centred">
                    <img src="resources/art_supply_store_logo.png" class="store_logo__picture">
                                                                
                </div>
            </nav>    
        </section>
        <section class="signin_form">
            <div class="container">
                <div class="login--items">
                    <h1>
                        Sign In
                    </h1>
                    <div style="margin: 10px">
                        <p style="color: red">The username and password you entered did not match our records. Please double-check and try again.</p>
                    </div>
                    <div class="login--form">
                        <form action="Login" method="post">
                            <table class="login--table">
                                 <tr>
                                    <td class="login--td">
                                        <h5 class="login_label">Username</h5>
                                    </td>
                                </tr>
                                <tr>
                                    <td> <input type="text" class="login--txtfield" name="username" size="30"> </td>
                                </tr>
                                <tr class="login--tr">
                                    <td class="login--td">
                                        <h5 class="login_label">Password</h5>
                                    </td>
                                </tr>
                                <tr>
                                    <td> <input type="password" class="login--txtfield" name="password" size="30"> </td>
                               </tr>
                                <tr>
                                    <td class="login--td">
                                        <input type="submit" class="sign_in--btn" value="Login" >
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
