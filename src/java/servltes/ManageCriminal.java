/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servltes;

import business.Criminal;
import business.Validator;
import dao.CriminalDAO;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author virajee
 */
public class ManageCriminal extends HttpServlet {

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
        String url = "";
        String message = "";
        String checkedMale = "";
        String checkedFemale = "";

        if (request.getParameter("btnUpload") != null) {
            url = "/uploadPhotoPage.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else if (request.getParameter("btnSubmit") != null) {
            try {
                String id = request.getParameter("txtId");
                int cID = Integer.parseInt(id);
                String fName = request.getParameter("txtFName");
                String mName = request.getParameter("txtMName");
                String lName = request.getParameter("txtLName");
                String bd = request.getParameter("txtBD");
                String nic = request.getParameter("txtNIC");
                String address = request.getParameter("txtCurrentAdd");
                String nickName = request.getParameter("txtNick");
                String gender = request.getParameter("radioGender");

                CriminalDAO cDAO = new CriminalDAO();
                Criminal c = null;

                Validator v = new Validator();

                if (gender != null) {
                    if (gender.equals("M")) {
                        if (v.isPresent(fName) && v.isPresent(bd) && v.isPresent(nic)) {
                            if (v.isValidDate(bd)) {
                                if (v.isValidNic(nic)) {
                                    if (!v.isPresent(mName)) {
                                        mName = "";
                                    }
                                    if (!v.isPresent(lName)) {
                                        lName = "";
                                    }
                                    if (!v.isPresent(address)) {
                                        address = "";
                                    }
                                    if (!v.isPresent(nickName)) {
                                        nickName = "";
                                    }
                                    Date birthD = Date.valueOf(bd);
                                    c = new Criminal(cID, fName, mName, lName, gender, birthD, nic, address, nickName);
                                    int result = cDAO.insert(c);
                                    if (result > 0) {
                                        message = "Saved Successfully";
                                        url = "/criminalRegistration.jsp";
                                        checkedMale = "checked";
                                    }
                                } else {
                                    message = "Enter Valid NIC";
                                    url = "/criminalRegistration.jsp";
                                    checkedMale = "checked";
                                }
                            } else {
                                message = "Enter Valid Date";
                                url = "/criminalRegistration.jsp";
                                checkedMale = "checked";
                            }
                        } else {
                            message = "Please fill out all the required fields with * mark";
                            url = "/criminalRegistration.jsp";
                            checkedMale = "checked";
                        }
                    } else if (gender.equals("F")) {
                        if (v.isPresent(fName) && v.isPresent(bd) && v.isPresent(nic)) {
                            if (v.isValidDate(bd)) {
                                if (v.isValidNic(nic)) {
                                    if (!v.isPresent(mName)) {
                                        mName = "";
                                    }
                                    if (!v.isPresent(lName)) {
                                        lName = "";
                                    }
                                    if (!v.isPresent(address)) {
                                        address = "";
                                    }
                                    if (!v.isPresent(nickName)) {
                                        nickName = "";
                                    }
                                    Date birthD = Date.valueOf(bd);
                                    c = new Criminal(fName, mName, lName, gender, birthD, nic, address, nickName);
                                    int result = cDAO.insert(c);
                                    if (result > 0) {
                                        message = "Saved Successfully";
                                        url = "/criminalRegistration.jsp";
                                        checkedMale = "checked";
                                    }
                                } else {
                                    message = "Enter Valid NIC";
                                    url = "/criminalRegistration.jsp";
                                    checkedMale = "checked";
                                }
                            } else {
                                message = "Enter Valid Date";
                                url = "/criminalRegistration.jsp";
                                checkedMale = "checked";
                            }
                        } else {
                            message = "Please fill out all the required fields with * mark";
                            url = "/criminalRegistration.jsp";
                            checkedMale = "checked";
                        }
                    }
                } else {
                    url = "/criminalRegistration.jsp";
                    message = "Please select a gender";
                }
            } catch (Exception e) {
                message = "Error in Inserting!!!!Contact System Administrator.";
                url = "/error.jsp";
            } finally {
                if (checkedMale.equals("checked")) {
                    request.setAttribute("checkedMale", "checked");
                } else if (checkedFemale.equals("checked")) {
                    request.setAttribute("checkedFemale", "checked");
                }
                request.setAttribute("message", message);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }
        } else if (request.getParameter("btnReset") != null) {
            request.setAttribute("checkedMale", "");
            request.setAttribute("checkedFemale", "");
            url = "/criminalRegistration.jsp";
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
