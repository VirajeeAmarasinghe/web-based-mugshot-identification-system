<%-- 
    Document   : newCrimeType
    Created on : May 24, 2017, 7:59:51 PM
    Author     : virajee
--%>

<%@page import="dao.CrimeTypeDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enter new crime type page</title>
        <title>Criminal Registration Page</title>
        <link rel="stylesheet" href="formatHomePage.css">
        <link rel="stylesheet" href="formatCriminalReg.css"> 
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
                    request.setAttribute("message", "First LogIn");
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
                    dispatcher.forward(request, response);
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
                <li><a href="criminalRegistration.jsp" >NEW CRIMINAL REGISTRATION</a></li>
                <li><a href="newOffenseRecord.jsp">NEW OFFENSE RECORD</a></li>
                <li><a href="searchImage.jsp">SEARCH CRIMINAL</a></li>
                <li><a href="#" class="thisPage">NEW CRIME TYPE</a></li>
            </ul>
        </div>
        <%
            CrimeTypeDAO cTypeDAO = new CrimeTypeDAO();
            int cID = cTypeDAO.getNxtId();
        %>
        <div id="registration_form" class="registration_form">
            <div class="page_header">
                <h2>NEW CRIME TYPE ENTERING</h2>
            </div>
            <div id="registration_form_area">
                <div id="message_div">${message}</div>                
                <form method="post" name="registration_form" action="ManageCrimeType">                    
                    <table>
                        <tr>
                            <td class="headline">Crime Type ID* : </td>
                            <td class="valueFields"><input type="text" name="txtId" id="txtId" value="<%=cID%>" class="fields" readonly="readonly"></td>
                            <td><input type="submit" name="btnSave" value="Save" class="buttons"></td>
                        </tr>
                        <tr>
                            <td class="headline">Crime Type* : </td>                                    
                            <td class="valueFields"><input type="text" name="txtCrimeType" id="txtCrimeType" class="fields"></td> 
                        </tr>                      
                    </table> 
            </div>                    
        </form>
    </div>        
    <div id="footer">
        <div id="footer_para">
            <p>Copyright © 2017 Mugshot Identification. All Rights Reserved.</p>
        </div>
    </div>
</body>
</html>
