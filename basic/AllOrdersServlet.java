/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic;

import entities.Bidder;
import entities.BidderManager;
import entities.Category;
import entities.Item;
import entities.ItemManager;
import entities.LoginUtilBidder;
import entities.LoginUtilSeller;
import entities.OrderManager;
import entities.Seller;
import entities.SellerManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "AllOrdersServlet", urlPatterns = {"/allOrders"})
public class AllOrdersServlet extends HttpServlet {
    
    @EJB
    private BidderManager bidderManager;

    @EJB
    private ItemManager itemManager;

    @EJB
    private LoginUtilBidder loginUtil;

    @EJB
    private OrderManager orderManager;
    
    Bidder bidder;
    
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
            out.println("<title>Servlet AllOrdersServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AllOrdersServlet at " + request.getContextPath() + "</h1>");
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
        bidder = loginUtil.getAuthenticatedBidder(request);
        if (bidder == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        if (bidder != null) {
            System.out.println("not null bidder --- ALL ORDERS SERVLET");
            List<Item> items = orderManager.findAllOrderedItems(bidder);
            List<Category> cats = itemManager.getCategories();
            request.setAttribute("cats", cats);
            request.setAttribute("items", items);
        } else {
            request.setAttribute("items", new ArrayList<Item>());
        }
        
        request.getRequestDispatcher("allOrders.jsp").forward(request, response);
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
