<%-- 
    Document   : uploadPhotoPage
    Created on : Apr 25, 2017, 12:35:54 AM
    Author     : virajee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload Photo Page</title>
        <link rel="stylesheet" href="formatHomePage.css">
        <link rel="stylesheet" href="formatUploadImage.css">
        <link class="jsbin" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
        <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
        <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
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
                <li><a href="criminalRegistration.jsp" class="thisPage">NEW CRIMINAL REGISTRATION</a></li>
                <li><a href="newOffenseRecord.jsp">NEW OFFENSE RECORD</a></li>
                <li><a href="searchImage.jsp">SEARCH CRIMINAL</a></li>
                <li><a href="newCrimeType.jsp">NEW CRIME TYPE</a></li>
            </ul>
        </div>
        <div id="registration_form" class="registration_form">
            <div class="page_header">
                <h2>PHOTO UPLOAD</h2>
            </div>
            <div id="uploading_form_area">
                <div id="message_div">${message}</div>                
                <form method="post" name="upload_form" enctype="multipart/form-data" action="ManageCriminalImageUpload">
                    <div id="upload_section">
                        <div id="image"><img src="https://st8.cannypic.com/thumbs/36/364597_632_canny_pic.jpg" id="displayImage" alt="Upload Image" width="300" height="400"></div>
                        <div id="buttons_group_2">
                            <input type="file" name="uploadImage" value="Browse" onchange="readURL(this);">                       
                        </div>
                    </div>
                    <div>
                        <div id="saving_section">
                            <label for="txtId">Enter Criminal ID : </label><input type="number" name="txtId" class="fields">
                            <input type="submit" value="Save" class="buttons" name="btnSave">
                        </div>
                    </div>
            </div>                    
        </form>
    </div>        
    <div id="footer">
        <div id="footer_para">
            <p>Copyright © 2017 Mugshot Identification. All Rights Reserved.</p>
        </div>
    </div>
    <script>
        function readURL(input) {
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
