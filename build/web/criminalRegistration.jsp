<%-- 
    Document   : criminalRegistration
    Created on : Apr 24, 2017, 8:19:21 PM
    Author     : virajee
--%>

<%@page import="dao.CriminalDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                <li><a href="#" class="thisPage">NEW CRIMINAL REGISTRATION</a></li>
                <li><a href="newOffenseRecord.jsp">NEW OFFENSE RECORD</a></li>
                <li><a href="searchImage.jsp">SEARCH CRIMINAL</a></li>
                <li><a href="newCrimeType.jsp">NEW CRIME TYPE</a></li>
            </ul>
        </div>
        <%
            CriminalDAO cDAO = new CriminalDAO();
            int cID = cDAO.getNxtId();
        %>
        <div id="registration_form" class="registration_form">
            <div class="page_header">
                <h2>NEW CRIMINAL REGISTRATION</h2>
            </div>
            <div id="registration_form_area">
                <div id="message_div">${message}</div>                
                <form method="post" name="registration_form" action="ManageCriminal">                    
                    <table>
                        <tr>
                            <td class="headline">Criminal ID* : </td>
                            <td class="valueFields"><input type="text" name="txtId" id="txtId" value="<%=cID%>" class="fields" readonly="readonly"></td>
                            <td><input type="submit" name="btnSubmit" value="Submit" class="buttons"></td>
                        </tr>
                        <tr>
                            <td class="headline">First Name* : </td>                                    
                            <td class="valueFields"><input type="text" name="txtFName" id="txtFName" value="<% if ((request.getParameter("txtFName") == null) || request.getParameter("btnReset") != null) {
                                    out.print("");
                                } else {
                                    out.print(request.getParameter("txtFName"));
                                }%>" class="fields"></td>  
                            <td><input type="submit" name="btnUpload" value="Upload Image" class="buttons"></td>
                        </tr>
                        <tr>
                            <td class="headline">Middle Name : </td>                                    
                            <td class="valueFields"><input type="text" name="txtMName" id="txtMName" value="<% if (request.getParameter("txtMName") == null || request.getParameter("btnReset") != null) {
                                    out.print("");
                                } else {
                                    out.print(request.getParameter("txtMName"));
                                }%>" class="fields"></td>    
                            <td><input type="submit" name="btnReset" value="Reset" class="buttons"></td>
                        </tr>
                        <tr>
                            <td class="headline">Last Name : </td>                                    
                            <td class="valueFields"><input type="text" name="txtLName" id="txtLName" value="<% if (request.getParameter("txtLName") == null || request.getParameter("btnReset") != null) {
                                    out.print("");
                                } else {
                                    out.print(request.getParameter("txtLName"));
                                }%>" class="fields"></td>                            
                        </tr>
                        <tr>
                            <td class="headline">Address : </td>                                    
                            <td class="valueFields"><input type="text" name="txtCurrentAdd" id="txtCurrentAdd" value="<% if (request.getParameter("txtCurrentAdd") == null || request.getParameter("btnReset") != null) {
                                    out.print("");
                                } else {
                                    out.print(request.getParameter("txtCurrentAdd"));
                                }%>" class="fields"></td>                            
                        </tr>                                 
                        <tr>
                            <td class="headline">Date of Birth* : </td>                                  
                            <td class="valueFields"><input type="date" name="txtBD" id="txtBD" placeholder="YYYY-MM-DD" value="<% if (request.getParameter("txtBD") == null || request.getParameter("btnReset") != null) {
                                    out.print("");
                                } else {
                                    out.print(request.getParameter("txtBD"));
                                }%>" class="fields"></td>
                        </tr>
                        <tr>
                            <td class="headline">NIC* : </td>                                  
                            <td class="valueFields"><input type="text" name="txtNIC" id="txtNIC" placeholder="XXXXXXXXXV" value="<% if (request.getParameter("txtNIC") == null || request.getParameter("btnReset") != null) {
                                    out.print("");
                                } else {
                                    out.print(request.getParameter("txtNIC"));
                                }%>" class="fields"></td>
                        </tr>                        
                        <tr>
                            <td class="headline">Gender* : </td>                                  
                            <td class="valueFields"></td>                            
                        </tr>
                        <tr>                                                              
                            <td class="headline"><label for="rMale">Male</label></td>
                            <td><input type="radio" name="radioGender" id="rMale" value="M" class="fields" ${checkedMale}></td>                            
                        </tr>
                        <tr>                                          
                            <td class="headline"><label for="rFemale">Female</label></td>
                            <td><input type="radio" name="radioGender" id="rFemale" value="F" class="fields" ${checkedFemale}></td>
                        </tr>
                        <tr>
                            <td class="headline">Nick Name : </td>                                  
                            <td class="valueFields"><input type="text" name="txtNick" id="txtNick" class="fields" value="<% if (request.getParameter("txtNick") == null || request.getParameter("btnReset") != null) {
                                    out.print("");
                                } else {
                                    out.print(request.getParameter("txtNick"));
                                }%>"></td>
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
