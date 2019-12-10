<%-- 
    Document   : newOffenseRecord
    Created on : Apr 29, 2017, 2:03:32 PM
    Author     : virajee
--%>

<%@page import="java.util.Set"%>
<%@page import="business.CrimeType"%>
<%@page import="dao.CrimeTypeDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.CriminalDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dao.CrimeDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Offense Record Page</title>
        <link rel="stylesheet" href="formatHomePage.css">
        <link rel="stylesheet" href="formatNewOffense.css"> 
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
                <li><a href="criminalRegistration.jsp">NEW CRIMINAL REGISTRATION</a></li>
                <li><a href="#" class="thisPage">NEW OFFENSE RECORD</a></li>
                <li><a href="searchImage.jsp">SEARCH CRIMINAL</a></li>
                <li><a href="newCrimeType.jsp">NEW CRIME TYPE</a></li>
            </ul>
        </div>
        <%
            CrimeDAO cDAO = new CrimeDAO();
            int crimeID = cDAO.getNxtId();

            CriminalDAO criminalDAO = new CriminalDAO();
            Set<Integer> criminalIDs = criminalDAO.getAllCriminalID();

            request.setAttribute("criminalList", criminalIDs);

            CrimeTypeDAO ctypeDAO = new CrimeTypeDAO();
            ArrayList<CrimeType> cTypeList = ctypeDAO.getAllCrimeTypes();

            request.setAttribute("criminalTypeList", cTypeList);
        %>
        <div id="registration_form" class="registration_form">
            <div class="page_header">
                <h2>NEW CRIME REGISTRATION</h2>
            </div>
            <div id="registration_form_area">
                <div id="message_div">${message}</div>                
                <form method="post" name="registration_form" action="ManageCrime">                    
                    <table>
                        <tr>
                            <td class="headline">Crime ID* : </td>
                            <td class="valueFields"><input type="text" name="txtId" id="txtId" value="<%=crimeID%>" class="fields" readonly="readonly"></td>
                            <td><input type="submit" name="btnSave" value="Save" class="buttons"></td>
                        </tr>
                        <tr>
                            <td class="headline">Criminal ID(s)* : </td>                                    
                            <td class="valueFields"><select name="criminalList" multiple="multiple" size="2" class="fields_2"><c:forEach var="item" items="${criminalList}">
                                        <option value="${item}">${item}</option>
                                    </c:forEach></select></td>  
                            <td><input type="submit" name="btnReset" value="Reset" class="buttons"></td>
                        </tr>
                        <tr>
                            <td class="headline">Description* : </td>                                    
                            <td class="valueFields"><textarea name="txtDes" id="txtDes" rows="5" value="<% if (request.getParameter("txtDes") == null || request.getParameter("btnReset") != null) {
                                    out.print("");
                                } else {
                                    out.print(request.getParameter("txtDes"));
                                }%>" class="fields_3"></textarea></td>    
                            <td></td>
                        </tr>
                        <tr>
                            <td class="headline">Date* : </td>                                    
                            <td class="valueFields"><input type="date" name="txtDate" id="txtDate" value="<% if (request.getParameter("txtDate") == null || request.getParameter("btnReset") != null) {
                                    out.print("");
                                } else {
                                    out.print(request.getParameter("txtDate"));
                                }%>" class="fields" placeholder="YYYY-MM-DD"></td>                            
                        </tr>
                        <tr>
                            <td class="headline">Time* : </td>                                    
                            <td class="valueFields"><input type="time" name="txtTime" id="txtTime" value="<% if (request.getParameter("txtTime") == null || request.getParameter("btnReset") != null) {
                                    out.print("");
                                } else {
                                    out.print(request.getParameter("txtTime"));
                                }%>" class="fields" placeholder="HH:MM:SS"></td>                            
                        </tr>                                 
                        <tr>
                            <td class="headline">Criminal Type(s)* : </td>                                  
                            <td class="valueFields"><select name="criminalTypeList" multiple="multiple" size="2" class="fields_2">
                                    <c:forEach var="item" items="${criminalTypeList}">
                                        <option value="${item.getCrimeId()}">${item.getCrimeType()}</option>
                                    </c:forEach></select></td></td>
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
