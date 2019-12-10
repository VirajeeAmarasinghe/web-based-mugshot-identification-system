<%-- 
    Document   : searchImage
    Created on : Apr 25, 2017, 7:04:42 PM
    Author     : virajee
--%>

<%@page import="business.CrimeType"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.CrimeTypeDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Image Page</title>
        <link rel="stylesheet" href="formatHomePage.css">
        <link rel="stylesheet" href="formatSearchImage.css">
        <link class="jsbin" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
        <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
        <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
    </head>
    <body>
        <%
            if (request.getParameter("btnCheck") == null) {
                request.setAttribute("percentage_section", "visible_sec");
                request.setAttribute("searching_form", "searching_form");
                request.setAttribute("next_highest_matching", "next_highest_matching_before_click");
                request.setAttribute("display", "display_before");
                request.setAttribute("displayMain", "displayMain_before_click");
                request.setAttribute("display_section_3", "display_section_3_before_click");
                request.setAttribute("footer", "footer_before_click");
            }
            if (request.getAttribute("noFace") == "noFace") {
                request.setAttribute("percentage_section", "visible_sec");
                request.setAttribute("searching_form", "searching_form");
                request.setAttribute("next_highest_matching", "next_highest_matching_before_click");
            }

            CrimeTypeDAO ctypeDAO = new CrimeTypeDAO();
            ArrayList<CrimeType> cTypeList = ctypeDAO.getAllCrimeTypes();
            request.setAttribute("crimeTypeList", cTypeList);
        %>
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
                <li><a href="newOffenseRecord.jsp">NEW OFFENSE RECORD</a></li>
                <li><a href="#" class="thisPage">SEARCH CRIMINAL</a></li>
                <li><a href="newCrimeType.jsp">NEW CRIME TYPE</a></li>
            </ul>
        </div>
        <div id="${searching_form}" class="searching_form">  
            <div id="message_div">${message}</div>
            <form method="post" name="upload_form" action="ManageSearchImage">
                <div id="select_option_area" class="select_option_area">Select a Crime Type :                
                    <select name="crimeTypeListOptions" size="1" id="select_option">
                        <option value="0">ALL</option>                       
                        <%for (int i = 0; i < cTypeList.size(); i++) {%>
                        <option value="<%=cTypeList.get(i).getCrimeId()%>"><%=cTypeList.get(i).getCrimeType()%></option>
                        <%}%>
                    </select>

                </div>
                <div id="uploading_form_area">                               

                    <div id="upload_section">                        
                        <div class="image"><img src="${manipulatedImagePath}" id="displayImage" alt="Manipulated Image" width="300" height="400"></div>
                        <div id="buttons_group_2">
                            <input type="file" name="uploadImage" value="Browse" onchange="readURL(this);">                       
                        </div>
                    </div>                    
                </div>

                <div id="matching_image">
                    <div class="image"><img src="${imagePath}" id="displayImage" alt="Matching Image with the Highest Percentage" width="300" height="400"></div>
                    <div id="buttons_group_3">
                        <input type="submit" name="btnCheck" value="Check" class="buttons"> 
                        <input type="submit" name="btnMore" value="Details" class="buttons">
                    </div>                
                    <div id="${percentage_section}">
                        <label>Matching Percentage : ${percentage}%</label>
                    </div>
                </div>

                <div id="${next_highest_matching}" class="next">
                    <div id="title">
                        <p>NEXT THREE HIGHEST MATCHINGS</p>
                    </div>
                    <div id="${displayMain}">
                        <div id="display_section_1" class="${display}">
                            <div id="second_highest">
                                <img src="${secondHighestImages}" class="displayImages_2" alt="No Matching Found">                       
                            </div>
                            <div class="percentage_display">
                                <label>Matching Percentage : ${percentage_2}%</label>
                            </div>
                        </div>
                        <div id="display_section_2" class="${display}">
                            <div id="third_highest">
                                <img src="${thirdHighestImages}" class="displayImages_2" alt="No Matching Found">                        
                            </div>
                            <div class="percentage_display">
                                <label>Matching Percentage : ${percentage_3}%</label>
                            </div>
                        </div>
                    </div>
                    <div id="${display_section_3}" class="${display}">
                        <div id="forth_highest">
                            <img src="${forthHighestImages}" class="displayImages_2" alt="No Matching Found">                        
                        </div>
                        <div class="percentage_display">
                            <label>Matching Percentage : ${percentage_4}%</label>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div id="${footer}">
            <div id="footer_para">
                <p>Copyright © 2017 Mugshot Identification. All Rights Reserved.</p>
            </div>
        </div> 
        <script>
            function readURL(input) {
                document.getElementById('displayImage').value = '';
                if (input.files && input.files[0]) {
                    var reader = new FileReader();

                    reader.onload = function(e) {
                        $('#displayImage')
                                .attr('src', e.target.result)
                                .width(300)
                                .height(400);
                    };

                    reader.readAsDataURL(input.files[0]);
                }
            }
        </script>
    </body>
</html>
