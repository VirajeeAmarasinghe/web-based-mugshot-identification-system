<%-- 
    Document   : moreDetails
    Created on : May 22, 2017, 7:50:08 PM
    Author     : virajee
--%>

<%@page import="dao.CrimeTypeDAO"%>
<%@page import="java.sql.Time"%>
<%@page import="business.Crime"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.CrimeDAO"%>
<%@page import="java.sql.Date"%>
<%@page import="business.Criminal"%>
<%@page import="dao.CriminalDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View More Page</title>
        <link rel="stylesheet" href="formatHomePage.css">
        <link rel="stylesheet" href="formatCriminalReg.css"> 
        <link rel="stylesheet" href="formatMoreDetails.css">
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
        <%
            int highestId = 0;
            String fName = "";
            String mName = "";
            String lName = "";
            String add = "";
            Date dob = null;
            String dobString = "";
            String nic = "";
            String gender = "";
            String nickName = "";
            ArrayList<Crime> a = null;

            if (request.getSession().getAttribute("highesId") != null) {
                String id = (String) request.getSession().getAttribute("highesId");
                highestId = Integer.parseInt(id);

                CriminalDAO cDAO = new CriminalDAO();
                Criminal c = cDAO.search(highestId);

                fName = c.getFirstName();
                mName = c.getMiddleName();
                lName = c.getLastName();
                add = c.getAddress();
                dob = c.getBirthDay();
                if (dob != null) {
                    dobString = String.valueOf(dob);
                } else {
                    dobString = "";
                }
                nic = c.getNic();
                gender = c.getGender();
                nickName = c.getNickName();

                //get crime details
                CrimeDAO crimeDAO = new CrimeDAO();
                a = crimeDAO.getCrime(highestId);

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
        <div id="registration_form" class="registration_form">
            <div class="page_header">
                <h2>CRIMINAL DETAILS</h2>
            </div>
            <div id="registration_form_area">
                <div id="message_div">${message}</div>                
                <form method="post" name="registration_form" action="ManageMoreData">                    
                    <table>
                        <tr>
                            <td class="headline">Criminal ID* : </td>
                            <td class="valueFields"><input type="text" name="txtId" id="txtId" value="<%=highestId%>" class="fields" readonly="readonly"></td>
                            <td><input type="submit" name="btnBack" value="Back" class="buttons"></td>
                        </tr>
                        <tr>
                            <td class="headline">First Name* : </td>                                    
                            <td class="valueFields"><input type="text" name="txtFName" id="txtFName" value="<%=fName%>" class="fields"></td>                            
                        </tr>
                        <tr>
                            <td class="headline">Middle Name : </td>                                    
                            <td class="valueFields"><input type="text" name="txtMName" id="txtMName" value="<%=mName%>" class="fields" readonly="readonly"></td>   
                        </tr>
                        <tr>
                            <td class="headline">Last Name : </td>                                    
                            <td class="valueFields"><input type="text" name="txtLName" id="txtLName" value="<%=lName%>" class="fields" readonly="readonly"></td>                            
                        </tr>
                        <tr>
                            <td class="headline">Address : </td>                                    
                            <td class="valueFields"><input type="text" name="txtCurrentAdd" id="txtCurrentAdd" value="<%=add%>" class="fields" readonly="readonly"></td>                            
                        </tr>                                 
                        <tr>
                            <td class="headline">Date of Birth* : </td>                                  
                            <td class="valueFields"><input type="date" name="txtBD" id="txtBD" value="<%=dobString%>" class="fields" readonly="readonly"></td>
                        </tr>
                        <tr>
                            <td class="headline">NIC* : </td>                                  
                            <td class="valueFields"><input type="text" name="txtNIC" id="txtNIC" value="<%=nic%>" class="fields" readonly="readonly"></td>
                        </tr>                        
                        <tr>
                            <td class="headline">Gender* : </td>                                  
                            <td class="valueFields"><input type="text" name="txtGender" id="txtGender" value="<%=gender%>" class="fields" readonly="readonly"></td>                            
                        </tr>                        
                        <tr>
                            <td class="headline">Nick Name : </td>                                  
                            <td class="valueFields"><input type="text" name="txtNick" id="txtNick" class="fields" value="<%=nickName%>" readonly="readonly"></td>
                        </tr>
                    </table> 
                    <h3>CRIME HISTORY</h3>
                    <table id="second_table">
                        <tr>
                            <td class="fields_heading_1">Crime ID</td>
                            <td class="fields_heading_1">Description</td>
                            <td class="fields_heading_1">Date</td>
                            <td class="fields_heading_1">Time</td>
                            <td class="fields_heading_1">Crime Type</td>
                        </tr>
                        <% for (int i = 0; i < a.size(); i++) {
                                CrimeTypeDAO cTypeDAO = new CrimeTypeDAO();
                                String s = cTypeDAO.getCrimeTypeAccordinToCrimeId(a.get(i).getCrime_id());
                        %>
                        <tr>
                            <td class="value_fields_1"><%=a.get(i).getCrime_id()%></td>
                            <td class="value_fields_1"><%=a.get(i).getDescription()%></td>
                            <td class="value_fields_1"><%=a.get(i).getCrimeDate()%></td>
                            <td class="value_fields_1"><%=a.get(i).getCrimeTime()%></td>
                            <td class="value_fields_1"><%=s%></td>
                        </tr>
                        <%}%>
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
