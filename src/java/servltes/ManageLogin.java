/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servltes;

import business.Login;
import business.Validator;
import dao.LoginDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author virajee
 */
@WebServlet(name = "ManageLogin", urlPatterns = {"/ManageLogin"})
public class ManageLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/login.jsp";
        String message = "";

        Validator v = new Validator();

        String email = request.getParameter("txtEmail");
        String pass = request.getParameter("txtPass");

        if (request.getParameter("btn_log") != null) {
            try {
                if (v.isPresent(email) || v.isPresent(pass)) {
                    if (v.isValidEmail(email)) {
                        Login l = new Login();
                        LoginDAO d = new LoginDAO();

                        l = d.getLoginData(email);

                        if (l == null) {
                            message = "Email or Password is incorrect";
                        } else if (request.getParameter("txtPass").equals(l.getPass())) {
                            HttpSession session = request.getSession(true);
                            session.setAttribute("userName", l.getUserName());
                            session.setAttribute("email", email);
                            url = "/homePage.jsp";
                        } else {
                            message = "Email or Password is incorrect";
                        }
                    } else {
                        message = "Enter Valid Email";
                    }
                } else {
                    message = "Please fill out all the text boxes";
                }
            } catch (Exception e) {
                message = "Error in accessing login data";
                url = "/error.jsp";
            }
        } else if (request.getParameter("btn_cancel") != null) {
            url = "/homePage.jsp";
        }

        request.setAttribute("message", message);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
