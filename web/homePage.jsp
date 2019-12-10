<%-- 
    Document   : homePage
    Created on : May 20, 2017, 2:34:02 PM
    Author     : virajee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link rel="stylesheet" href="formatHomePage.css">
    </head>
    <body>
        <%
            if (session != null) {
                if (session.getAttribute("userName") != null) {
                    request.setAttribute("pageLink", "ManageLoginLogOut");
                    request.setAttribute("title", "LOGOUT");
                    String userName = (String) session.getAttribute("userName");
                    request.setAttribute("user", userName);
                } else {
                    request.setAttribute("pageLink", "login.jsp");
                    request.setAttribute("title", "LOGIN");
                    request.setAttribute("user", "USER");
                }
            }
        %>
        <div id="first_div">
            <ul>
                <li id="welcome_title">WELCOME ${user}</li>
                <li><a href="adminReg.jsp">REGISTER</a></li>
                <li><a href="${pageLink}">${title}</a></li>
            </ul>
        </div>
        <div id="second_div">
            <ul>
                <li><a href="criminalRegistration.jsp">NEW CRIMINAL REGISTRATION</a></li>
                <li><a href="newOffenseRecord.jsp">NEW OFFENSE RECORD</a></li>
                <li><a href="searchImage.jsp">SEARCH CRIMINAL</a></li>
                <li><a href="newCrimeType.jsp">NEW CRIME TYPE</a></li>
            </ul>
        </div>
        <div id="topBanner">
            <!--Image source->http://www.amc.com/shows/breaking-bad/extras/breaking-bad-mugshots-->
            <img src="images/top_banner.jpg">
        </div>
        <div id="footer">
            <div id="footer_para">
                <p>Copyright © 2017 Mugshot Identification. All Rights Reserved.</p>
            </div>
        </div>
    </body>
</html>
