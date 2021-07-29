/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.DBContext;
import dal.GalleryDAO;
import dal.ImageDAO;
import dal.ViewDAO;
import entity.Gallery;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class GalleryDetaiServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            try {
                //get imagePath
                DBContext bContext = new DBContext();
                String imagePath = bContext.getImagePath();
                request.setAttribute("imagePath", imagePath);
            
                GalleryDAO galleryDAO = new GalleryDAO();
                
                //for header
                List<Gallery> top3Galleries;
                top3Galleries = galleryDAO.getTop3Galleries();
                request.setAttribute("top3Galleries", top3Galleries);
                
                //get information and all images of gallery by galleryID
                String idRaw = request.getParameter("id");
                int id;
                try {
                    id = Integer.parseInt(idRaw);
                } catch (Exception e) {
                    id=-1;
                }
                Gallery gallery = galleryDAO.getGalleryByID(id);
                
                ImageDAO imageDao = new ImageDAO();
                List<String> images = imageDao.getAllImageByGalleryID(id);
                
                //paging: each page has 8 images
                int numOfImg = images.size();
                int numPerPage = 8;
                
                int numOfPage = numOfImg/numPerPage + (numOfImg%numPerPage==0?0:1);
                
                //get index of page, images on each
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
                List<String> imgOnPage = imageDao.pagingForImg(id, page, numPerPage);
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
                request.setAttribute("id", id);
                request.setAttribute("activeNow", id);
                request.setAttribute("gallery", gallery);
                request.setAttribute("images", images);
                request.setAttribute("imgOnPage", imgOnPage);
                request.setAttribute("page", page);
                request.setAttribute("numOfPage", numOfPage);
                
                request.getRequestDispatcher("galleryDetail.jsp").forward(request, response);

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
