/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic;

import entities.BidManager;
import entities.LoginUtilSeller;
import entities.Seller;
import entities.SellerManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(name = "CancelAllBidsServlet", urlPatterns = {"/cancelAllBids"})
public class CancelAllBidsServlet extends HttpServlet {
    
    @EJB
    BidManager bidManager;

    @EJB
    SellerManager sellerManager;

    @EJB
    LoginUtilSeller loginUtil;
    
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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CancelAllBidsServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CancelAllBidsServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String itemId = request.getParameter("itemId");
        Seller seller = loginUtil.getAuthenticatedSeller(request);
        if (seller == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        String username = seller.getFirstname();

        if ((itemId != null && itemId.length() > 0) && (username != null && username.length() > 0)) {
            try {
                Long idItem = Long.parseLong(itemId);
                bidManager.deleteAllBids(idItem, username);
            } catch (Exception exc) {
                System.out.println(exc);
            }

        }

        response.sendRedirect("bidderItems?firstname=" + request.getParameter("firstname"));
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
