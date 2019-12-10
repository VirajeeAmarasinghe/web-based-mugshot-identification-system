<%-- 
    Document   : studentResetPass
    Created on : 30-Dec-2016, 16:14:17
    Author     : Virajee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrator Registration</title>
        <link rel="stylesheet" href="formatAdminReg.css">
    </head>
    <body>       
        <div id="main_content">
            <div id="title">
                <a href="homePage.jsp"><h1 id="main_title">MUGSHOT IDENTIFICATION</h1></a>           
            </div>
            <div id="registration_form" class="registration_form">
                <div class="page_header">
                    <h2>Registration</h2>
                </div>
                <div id="registration_form_area">                    
                    <div id="message_div">${message}</div>
                    <form method="post" name="registration_form" action="RegiAdmin">                
                        <div class="control">                   
                            <input id="username" name="txtEmail" type="email" placeholder="Email" class="field" value="<% if (request.getParameter("txtEmail") == null) {
                                    out.print("");
                                } else {
                                    out.print(request.getParameter("txtEmail"));
                                }%>">
                        </div>
                        <div class="control">                   
                            <input id="username" name="txtUsername" type="text" placeholder="Username" class="field" value="<% if (request.getParameter("txtUsername") == null) {
                                    out.print("");
                                } else {
                                    out.print(request.getParameter("txtUsername"));
                                }%>">
                        </div>
                        <div class="control">                   
                            <input id="password" name="password" type="password" placeholder="Password" class="field" value="">
                        </div>
                        <div class="control">                   
                            <input id="confirmPass" name="confirmPass" type="password" placeholder="Confirm Password" class="field" value="">
                        </div>
                        <div class="form_action">
                            <button type="submit" name="btn_sub" id="log_button" class="buttons">Submit</button><br>
                            <button type="submit" name="btn_cancel" id="cancel_button" class="buttons">Cancel</button>
                        </div>                                        
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>


