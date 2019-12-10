/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servltes;

import business.CrimeType;
import business.Validator;
import dao.CrimeTypeDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author virajee
 */
public class ManageCrimeType extends HttpServlet {

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
        String url = "/newCrimeType.jsp";
        String message = "";
        try {
            if (request.getParameter("btnSave") != null) {
                String id = request.getParameter("txtId");
                int cId = Integer.parseInt(id);
                String type = request.getParameter("txtCrimeType");
                Validator v = new Validator();
                if (v.isPresent(id) && v.isPresent(type)) {
                    if (v.isValidNumber(id)) {
                        //save data 
                        CrimeType c = new CrimeType(cId, type);
                        CrimeTypeDAO cDAO = new CrimeTypeDAO();
                        int result = cDAO.insert(c);
                        if (result > 0) {
                            message = "Saved Successfully";
                        } else {
                            message = "Sorry.Error occurred";
                        }
                    } else {
                        message = "Enter Valid Number";
                    }
                } else {
                    message = "Fill All Mandatory Fields";
                }
            }
        } catch (Exception e) {
            message = "Sorry.Error Occurred.Contact System Administrator";
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
