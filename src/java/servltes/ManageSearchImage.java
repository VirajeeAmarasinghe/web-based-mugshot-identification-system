/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servltes;

import business.ComparingAlgorithm;
import dao.CriminalDAO;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.DMatch;

/**
 *
 * @author virajee
 */
public class ManageSearchImage extends HttpServlet {

    static Map<Integer, Double> percentages = new HashMap();

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
        String path = "";
        String url = "/searchImage.jsp";

        try {
            if (request.getParameter("btnCheck") != null) {
                if (request.getParameter("uploadImage") != null) {

                    String option[] = request.getParameterValues("crimeTypeListOptions");
                    System.out.println(option[0]);
                    int option_2 = Integer.parseInt(option[0]);

                    path = "D:\\Degree Subject Materials\\AI\\Assignments\\After\\" + request.getParameter("uploadImage");

                    ComparingAlgorithm cAlgo = new ComparingAlgorithm();

                    if (cAlgo.detectFace(path) > 0) {
                      //loading manipulated image to the Mat
                        //Mat matManipulatedImage = cAlgo.readImage(theFile.getAbsolutePath());
                        Mat matManipulatedImage = cAlgo.readImage(path);

                        //detecting keypoints of the manipulated image
                        MatOfKeyPoint matOfKeyPointManipulatedImage = cAlgo.detectKeyPoint(matManipulatedImage);

                        //computing descriptors of the manipulated image
                        MatOfKeyPoint manipulatedImageDescriptors = cAlgo.computeDescriptors(matManipulatedImage, matOfKeyPointManipulatedImage);

                        CriminalDAO cDAO = new CriminalDAO();
                        System.out.println(option_2);
                        Set<Integer> ids = null;
                        //if option=ALL,get all the criminals' ids
                        if (option_2 == 0) {
                            ids = cDAO.getAllCriminalID();
                            System.out.println("Comes Here");
                        } else {
                            ids = cDAO.getAllCriminalIDsAccordingToCrimeType(option_2);
                        }

                        String original_image_path = "D:\\Degree Subject Materials\\AI\\Assignments\\Before\\";
                        if (ids.size() != 0) {
                            for (Integer id : ids) {
                                String imagePath = original_image_path + "img-" + id + ".jpg";
                                Mat matOriginalImage = cAlgo.readImage(imagePath);
                                MatOfKeyPoint matOfKeyPointOriginalImage = cAlgo.detectKeyPoint(matOriginalImage);
                                MatOfKeyPoint originalImageDescriptors = cAlgo.computeDescriptors(matOriginalImage, matOfKeyPointOriginalImage);

                                //comparing original image and the manipulated image
                                LinkedList<DMatch> goodMatchesList = cAlgo.compareTwoImages(manipulatedImageDescriptors, originalImageDescriptors);
                                System.out.println("goodMatchList size=" + goodMatchesList.size());
                                //get the matching percentage
                                double percentage = cAlgo.getPercentage(matOfKeyPointManipulatedImage, goodMatchesList);
                                //System.out.println(percentage);
                                percentages.put(id, percentage);
                            }

                            double highestPercentage = cAlgo.getHighestPercentage(percentages)[0];
                            int higherstPercentageID = (int) cAlgo.getHighestPercentage(percentages)[1];

                            double highestPercentage_2 = cAlgo.getHighestPercentage(percentages)[2];
                            int higherstPercentageID_2 = (int) cAlgo.getHighestPercentage(percentages)[3];

                            double highestPercentage_3 = cAlgo.getHighestPercentage(percentages)[4];
                            int higherstPercentageID_3 = (int) cAlgo.getHighestPercentage(percentages)[5];

                            double highestPercentage_4 = cAlgo.getHighestPercentage(percentages)[6];
                            int higherstPercentageID_4 = (int) cAlgo.getHighestPercentage(percentages)[7];

                            System.out.println("Next percentage=" + cAlgo.getHighestPercentage2(percentages)[0] + " id=" + cAlgo.getHighestPercentage2(percentages)[1]);

                            System.out.println("percentage=" + highestPercentage + " ID=" + higherstPercentageID);
                            String original_path = "images/img-" + higherstPercentageID + ".jpg";
                            String manipulatedImagePath = "images/maniimg-" + higherstPercentageID + ".jpg";

                            String image_2 = "images/img-" + higherstPercentageID_2 + ".jpg";
                            String image_3 = "images/img-" + higherstPercentageID_3 + ".jpg";
                            String image_4 = "images/img-" + higherstPercentageID_4 + ".jpg";

                            request.setAttribute("manipulatedImagePath", manipulatedImagePath);
                            request.setAttribute("imagePath", original_path);
                            request.setAttribute("percentage", highestPercentage);
                            request.setAttribute("percentage_section", "percentage_section");
                            request.setAttribute("searching_form", "searching_form_after_click");
                            request.setAttribute("next_highest_matching", "next_highest_matching_after_click");
                            request.setAttribute("secondHighestImages", image_2);
                            request.setAttribute("percentage_2", highestPercentage_2);
                            request.setAttribute("thirdHighestImages", image_3);
                            request.setAttribute("percentage_3", highestPercentage_3);
                            request.setAttribute("forthHighestImages", image_4);
                            request.setAttribute("percentage_4", highestPercentage_4);
                            request.setAttribute("display", "display_after");
                            request.setAttribute("displayMain", "displayMain_after_click");
                            request.setAttribute("footer", "footer");
                            request.setAttribute("display_section_3", "display_section_3_after_click");

                            HttpSession session = request.getSession(true);
                            String hID = String.valueOf(higherstPercentageID);
                            session.setAttribute("highesId", hID);
                        } else {
                            message = "No Records Were Found";
                            request.setAttribute("noFace", "noFace");
                        }
                    } else {
                        message = "No Face was Found";
                        request.setAttribute("noFace", "noFace");
                    }
                } else {
                    message = "Upload a Manipulated Image";
                    request.setAttribute("noFace", "noFace");
                }
            } else if (request.getParameter("btnMore") != null) {
                if (request.getSession().getAttribute("highesId") != null) {
                    url = "/moreDetails.jsp";
                } else {
                    message = "Do the Checking First";
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
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
