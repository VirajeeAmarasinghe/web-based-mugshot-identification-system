package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import business.CrimeType;
import java.util.ArrayList;
import dao.CrimeTypeDAO;

public final class searchImage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Search Image Page</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"formatHomePage.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"formatSearchImage.css\">\n");
      out.write("        <link class=\"jsbin\" href=\"http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("        <script class=\"jsbin\" src=\"http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js\"></script>\n");
      out.write("        <script class=\"jsbin\" src=\"http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

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
        
      out.write("\n");
      out.write("        ");

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
        
      out.write("        \n");
      out.write("        <div id=\"first_div\">\n");
      out.write("            <ul>\n");
      out.write("                <li id=\"welcome_title\">WELCOME ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${user}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</li>\n");
      out.write("                <li><a href=\"adminReg.jsp\">REGISTER</a></li>\n");
      out.write("                <li><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageLink}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write('"');
      out.write('>');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${title}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</a></li>\n");
      out.write("            </ul>\n");
      out.write("        </div>\n");
      out.write("        <div id=\"second_div\">\n");
      out.write("            <ul>\n");
      out.write("                <li><a href=\"criminalRegistration.jsp\">NEW CRIMINAL REGISTRATION</a></li>\n");
      out.write("                <li><a href=\"newOffenseRecord.jsp\">NEW OFFENSE RECORD</a></li>\n");
      out.write("                <li><a href=\"#\" class=\"thisPage\">SEARCH CRIMINAL</a></li>\n");
      out.write("                <li><a href=\"newCrimeType.jsp\">NEW CRIME TYPE</a></li>\n");
      out.write("            </ul>\n");
      out.write("        </div>\n");
      out.write("        <div id=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${searching_form}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" class=\"searching_form\">  \n");
      out.write("            <div id=\"message_div\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${message}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</div>\n");
      out.write("            <form method=\"post\" name=\"upload_form\" action=\"ManageSearchImage\">\n");
      out.write("                <div id=\"select_option_area\" class=\"select_option_area\">Select a Crime Type :                \n");
      out.write("                    <select name=\"crimeTypeListOptions\" size=\"1\" id=\"select_option\">\n");
      out.write("                        <option value=\"0\">ALL</option>                       \n");
      out.write("                        ");
for (int i = 0; i < cTypeList.size(); i++) {
      out.write("\n");
      out.write("                        <option value=\"");
      out.print(cTypeList.get(i).getCrimeId());
      out.write('"');
      out.write('>');
      out.print(cTypeList.get(i).getCrimeType());
      out.write("</option>\n");
      out.write("                        ");
}
      out.write("\n");
      out.write("                    </select>\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("                <div id=\"uploading_form_area\">                               \n");
      out.write("\n");
      out.write("                    <div id=\"upload_section\">                        \n");
      out.write("                        <div class=\"image\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${manipulatedImagePath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" id=\"displayImage\" alt=\"Manipulated Image\" width=\"300\" height=\"400\"></div>\n");
      out.write("                        <div id=\"buttons_group_2\">\n");
      out.write("                            <input type=\"file\" name=\"uploadImage\" value=\"Browse\" onchange=\"readURL(this);\">                       \n");
      out.write("                        </div>\n");
      out.write("                    </div>                    \n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div id=\"matching_image\">\n");
      out.write("                    <div class=\"image\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${imagePath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" id=\"displayImage\" alt=\"Matching Image with the Highest Percentage\" width=\"300\" height=\"400\"></div>\n");
      out.write("                    <div id=\"buttons_group_3\">\n");
      out.write("                        <input type=\"submit\" name=\"btnCheck\" value=\"Check\" class=\"buttons\"> \n");
      out.write("                        <input type=\"submit\" name=\"btnMore\" value=\"Details\" class=\"buttons\">\n");
      out.write("                    </div>                \n");
      out.write("                    <div id=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${percentage_section}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("                        <label>Matching Percentage : ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${percentage}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("%</label>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div id=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${next_highest_matching}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" class=\"next\">\n");
      out.write("                    <div id=\"title\">\n");
      out.write("                        <p>NEXT THREE HIGHEST MATCHINGS</p>\n");
      out.write("                    </div>\n");
      out.write("                    <div id=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${displayMain}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("                        <div id=\"display_section_1\" class=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${display}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("                            <div id=\"second_highest\">\n");
      out.write("                                <img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${secondHighestImages}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" class=\"displayImages_2\" alt=\"No Matching Found\">                       \n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"percentage_display\">\n");
      out.write("                                <label>Matching Percentage : ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${percentage_2}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("%</label>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div id=\"display_section_2\" class=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${display}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("                            <div id=\"third_highest\">\n");
      out.write("                                <img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${thirdHighestImages}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" class=\"displayImages_2\" alt=\"No Matching Found\">                        \n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"percentage_display\">\n");
      out.write("                                <label>Matching Percentage : ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${percentage_3}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("%</label>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div id=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${display_section_3}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" class=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${display}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("                        <div id=\"forth_highest\">\n");
      out.write("                            <img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${forthHighestImages}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" class=\"displayImages_2\" alt=\"No Matching Found\">                        \n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"percentage_display\">\n");
      out.write("                            <label>Matching Percentage : ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${percentage_4}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("%</label>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("        <div id=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${footer}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("            <div id=\"footer_para\">\n");
      out.write("                <p>Copyright © 2017 Mugshot Identification. All Rights Reserved.</p>\n");
      out.write("            </div>\n");
      out.write("        </div> \n");
      out.write("        <script>\n");
      out.write("            function readURL(input) {\n");
      out.write("                document.getElementById('displayImage').value = '';\n");
      out.write("                if (input.files && input.files[0]) {\n");
      out.write("                    var reader = new FileReader();\n");
      out.write("\n");
      out.write("                    reader.onload = function(e) {\n");
      out.write("                        $('#displayImage')\n");
      out.write("                                .attr('src', e.target.result)\n");
      out.write("                                .width(300)\n");
      out.write("                                .height(400);\n");
      out.write("                    };\n");
      out.write("\n");
      out.write("                    reader.readAsDataURL(input.files[0]);\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
