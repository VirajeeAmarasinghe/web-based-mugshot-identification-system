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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author virajee
 */
public class RstPass extends HttpServlet {

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
        String message = "";
        String url = "/resetPass.jsp";

        Validator v = new Validator();
        Login l = null;
        LoginDAO lDAO = new LoginDAO();

        try {
            if (request.getParameter("btn_log") != null) {
                String email = request.getParameter("txtEmail");
                String pass = request.getParameter("password");
                String confirmPass = request.getParameter("confirmPass");

                if (v.isPresent(email) && v.isPresent(pass) && v.isPresent(confirmPass)) {
                    if (v.isValidEmail(email)) {
                        if (pass.equals(confirmPass)) {
                            if (v.isValidWithPasswordPolicy(pass)) {
                                if (lDAO.alreadyHasEmail(email)) {
                                    //update
                                    l = new Login(email, pass);
                                    int result = lDAO.update(l);
                                    if (result > 0) {
                                        message = "Successfully Updated";
                                        url = "/login.jsp";
                                    } else {
                                        message = "Error in Updating!!!!Contact System Administrator.";
                                        url = "error.jsp";
                                    }
                                } else {
                                    message = "There is no account for the email";
                                }
                            } else {
                                message = "Password must contain atleast one uppercase letter, one lowercase letter, one digit, one special character and length must be within 6 to 20 characters";
                            }
                        } else {
                            message = "Password and Confirm Password not match";
                        }
                    } else {
                        message = "Please enter valid email address";
                    }
                } else {
                    message = "Please fill out all the text boxes";
                }
            } else if (request.getParameter("btn_cancel") != null) {
                url = "/login.jsp";
            }

        } catch (Exception e) {
            message = "Error in Updating!!!!Contact System Administrator.";
            url = "/error.jsp";
        } finally {
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
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
