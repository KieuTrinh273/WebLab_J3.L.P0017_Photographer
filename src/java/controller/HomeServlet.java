/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.ContactDAO;
import dal.DBContext;
import dal.GalleryDAO;
import dal.ViewDAO;
import entity.Contact;
import entity.Gallery;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class HomeServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try {
                ContactDAO contactDAO = new ContactDAO();
                GalleryDAO galleryDAO = new GalleryDAO();

                request.setAttribute("activeNow", "0");
                request.setAttribute("top3Galleries", galleryDAO.getTop3Galleries());

                request.setAttribute("contact", contactDAO.getContact());

                //each page contain 3 galleries
                List<Gallery> galleries = galleryDAO.getAllGalleries();
                int numOfGalleries = galleries.size();
                int numOfGalPerPage = 3;

                //if the number of galeries is divisible by 3 => numOfPage = numOfGalleries/3
                // else => numOfPage = numOfGalleries/3 +1
                int numOfPage = numOfGalleries / numOfGalPerPage + (numOfGalleries % numOfGalPerPage == 0 ? 0 : 1);

                String pageRaw = request.getParameter("page");
                int page;
                try {
                    if (pageRaw == null) {
                        page = 1;
                    } else {
                        page = Integer.parseInt(pageRaw);
                    }
                } catch (Exception e) {
                    page = numOfPage + 1;
                }
                
                List<Gallery> galleriesOnPage = galleryDAO.pagingForGalleries(page, numOfGalPerPage);
                //get view
                ViewDAO viewDAO = new ViewDAO();
                HttpSession session = request.getSession();
                if(session.isNew()){
                    viewDAO.updateView();
                }
                int view = viewDAO.getViews();
                String formatted = String.format("%06d", view);
                String[] parts = formatted.split("");
                
                request.setAttribute("view", parts);
                request.setAttribute("galleriesOnPage", galleriesOnPage);
                request.setAttribute("page", page);
                request.setAttribute("numOfPage", numOfPage);
                request.setAttribute("allGalleries", galleries);

                request.getRequestDispatcher("home.jsp").forward(request, response);
            } catch (Exception ex) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
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
