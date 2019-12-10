<%-- 
    Document   : adminLog
    Created on : 29-Dec-2016, 20:50:03
    Author     : Virajee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="formatLogin.css">
    </head>
    <body>        
        <div id="main_content">
            <div id="title">
                <a href="homePage.jsp"><h1 id="main_title">MUGSHOT IDENTIFICATION</h1></a>           
            </div>
            <div id="registration_form" class="registration_form">
                <div class="page_header">
                    <h2>Log In</h2>
                </div>
                <div id="registration_form_area">                    
                    <div id="message_div">${message}</div>
                    <form method="post" name="registration_form" action="ManageLogin">                
                        <div class="control">                   
                            <input id="username" name="txtEmail" type="email" placeholder="Email" class="field" value="<% if (request.getParameter("txtEmail") == null) {
                                    out.print("");
                                } else {
                                    out.print(request.getParameter("txtEmail"));
                                }%>">
                        </div>
                        <div class="control">                   
                            <input id="password" name="txtPass" type="password" placeholder="Password" class="field" value="">
                        </div>

                        <div class="form_action">
                            <button type="submit" name="btn_log" id="log_button" class="buttons">Login</button><br>
                            <button type="submit" name="btn_cancel" id="cancel_button" class="buttons">Cancel</button>
                        </div>
                        <div class="action">
                            <a id="account" href="adminReg.jsp">Need an Account?</a>
                            <a id="forget_pass" href="resetPass.jsp">Forgot your Password?</a>
                        </div>                
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
