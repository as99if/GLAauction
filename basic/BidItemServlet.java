/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic;

import entities.Bid;
import entities.BidManager;
import entities.Bidder;
import entities.BidderManager;
import entities.Item;
import entities.ItemManager;
import entities.LoginUtilBidder;
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
@WebServlet(name = "BidItemServlet", urlPatterns = {"/bidItem"})
public class BidItemServlet extends HttpServlet {
    
    @EJB
    private BidManager bidManager;
    
    @EJB
    private ItemManager itemManager;
    
    @EJB
    private BidderManager bidderManager;
    
    @EJB
    private LoginUtilBidder loginUtil;
    
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
            out.println("<title>Servlet BidItemServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BidItemServlet at " + request.getContextPath() + "</h1>");
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
       Bidder bidder = loginUtil.getAuthenticatedBidder(request);
       if (bidder == null) {
           response.sendRedirect("login.jsp");
           return;
       }
        
        String itemId = request.getParameter("itemId");
        Bid bid = null;
        Item item = null;
        try {
            Long id = Long.parseLong(itemId);
            bid = bidManager.findMaxBid(id);
            
            item = itemManager.findItemById(id);
        } catch (Exception exc) {
            System.out.println("error -======= ");
        }

        Integer value = 0;
        if (bid == null || bid.getBidPrice() == null || bid.getBidPrice()==0) {
            value = item.getInitialPrice();
        } else {
            value = bid.getBidPrice();
        }
          
        request.setAttribute("item", item);
        request.setAttribute("bid", bid);
        request.setAttribute("bidValue", value);
        request.getRequestDispatcher("bidItem.jsp").forward(request, response);
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
        Bidder bidder = loginUtil.getAuthenticatedBidder(request);
       if (bidder == null) {
           response.sendRedirect("login.jsp");
           return;
       }
        
        String itemIdStr = request.getParameter("itemId");
        
        String priceStr = request.getParameter("price");
        String name = bidder.getFirstname();
        boolean result = false;
        try {
            Long itemId = Long.parseLong(itemIdStr);
            Integer price = Integer.parseInt(priceStr);
            result = bidManager.bid(itemId, name, price);
        } catch (Exception exc) {
            System.out.println(exc);
        }
        
        if (result) {
            System.out.println("bid hhave");
            Item item = itemManager.findItemById(Long.parseLong(itemIdStr));
            item.setWinner(bidder);
            response.sendRedirect("bidderItems");
        } else {
            System.out.println("error while bidding");
            response.sendRedirect("bidItem?itemId="+itemIdStr);
        }
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
