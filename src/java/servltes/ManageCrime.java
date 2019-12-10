/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servltes;

import business.Crime;
import business.Validator;
import dao.CrimeDAO;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author virajee
 */
public class ManageCrime extends HttpServlet {

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
        String url = "/newOffenseRecord.jsp";

        try {
            if (request.getParameter("btnSave") != null) {
                String criminalIds[] = request.getParameterValues("criminalList");
                String description = request.getParameter("txtDes");
                String date = request.getParameter("txtDate");
                String time = request.getParameter("txtTime");
                String types[] = request.getParameterValues("criminalTypeList");

                Validator v = new Validator();

                if (v.isPresent(description) && v.isPresent(date) && v.isPresent(time)) {
                    if (criminalIds != null) {
                        if (types != null) {
                            if (v.isValidDate(date)) {
                                if (v.isPresent(time)) {
                                    Date crimeDate = Date.valueOf(date);
                                    Time crimeTime = Time.valueOf(time);

                                    Crime c = new Crime(description, crimeDate, crimeTime);

                                    CrimeDAO cDAO = new CrimeDAO();
                                    //insert into crime_record table
                                    int result_1 = cDAO.insertIntoCrimeRecordTable(c);

                                    //insert into crime_criminal table
                                    if (result_1 > 0) {
                                        String crimeID = request.getParameter("txtId");
                                        int intCrimeID = Integer.parseInt(crimeID);

                                        int[] intCriminalIds = new int[criminalIds.length];

                                        for (int a = 0; a < criminalIds.length; a++) {
                                            intCriminalIds[a] = Integer.parseInt(criminalIds[a]);
                                        }

                                        String success_1 = "true";

                                        for (int i = 0; i < intCriminalIds.length; i++) {
                                            c = new Crime(intCrimeID, intCriminalIds[i]);
                                            int result_2 = cDAO.insertIntoCrimeCriminalTable(c);
                                            if (result_2 == 0) {
                                                message = "Sorry!Error Occurred in Saving.";
                                                url = "/error.jsp";
                                                success_1 = "false";
                                                //delete entered record
                                                cDAO.deleteFromCrimeRecord(intCrimeID);
                                                break;
                                            }
                                        }
                                        //insert into crime_crimeType table
                                        if (success_1.equals("true")) {
                                            int[] intTypes = new int[types.length];
                                            for (int b = 0; b < types.length; b++) {
                                                intTypes[b] = Integer.parseInt(types[b]);
                                            }
                                            String success_2 = "true";
                                            for (int d = 0; d < intTypes.length; d++) {
                                                c = new Crime(intCrimeID, intTypes[d], "");
                                                int result_3 = cDAO.insertIntoCrimeCrimeTypeTable(c);
                                                if (result_3 == 0) {
                                                    message = "Sorry!Error Occurred in Saving.";
                                                    url = "/error.jsp";
                                                    success_2 = "false";
                                                    break;
                                                }
                                            }
                                            if (success_2.equals("true")) {
                                                message = "Saved Successfully";
                                            } else {
                                                message = "Sorry!Error Occurred in Saving.";
                                                url = "/error.jsp";
                                                cDAO.deleteFromCrimeCriminal(intCrimeID);
                                                cDAO.deleteFromCrimeRecord(intCrimeID);
                                            }
                                        } else {
                                            message = "Sorry!Error Occurred in Saving.";
                                            url = "/error.jsp";
                                        }
                                    } else {
                                        message = "Sorry!Error Occurred in Saving.";
                                        url = "/error.jsp";
                                    }
                                } else {
                                    message = "Enter Valid Time";
                                }
                            } else {
                                message = "Enter Valid Date";
                            }
                        } else {
                            message = "Select Crime Type(s)";
                        }
                    } else {
                        message = "Select Criminal Id(s)";
                    }
                } else {
                    message = "Fill All The Mandatory Fields";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            url = "/error.jsp";
            message = "Error.Contact System Admin";
            CrimeDAO cDAO = new CrimeDAO();
            String id = request.getParameter("txtId");
            int id_2 = Integer.parseInt(id);
            cDAO.deleteFromCrimeCrimeType(id_2);
            cDAO.deleteFromCrimeCriminal(id_2);
            cDAO.deleteFromCrimeRecord(id_2);

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
