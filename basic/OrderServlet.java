/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic;

import entities.Bidder;
import entities.Item;
import entities.LoginUtilBidderBean;
import entities.OrderManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;
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
@WebServlet(name = "OrderServlet", urlPatterns = {"/orders"})
public class OrderServlet extends HttpServlet {

    @EJB
    private OrderManager orderManager;
    
    @EJB
    private Bidder bidder;
    
    @EJB
    private LoginUtilBidderBean loginUtil;
    
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
        
        List<Item> items = orderManager.findAllOrderedItems(bidder);
        System.out.println("ITEMS from bidder == " + items.size());
        request.setAttribute("items", items);
        
        if (items == null || items.size() == 0) {
            response.sendRedirect("AllItemsForBidder.jsp");
        } 
        
        Double totalOrder = orderManager.calculateAllOrders(items);
        
        request.setAttribute("address", bidder.getAddress());
        
        request.getRequestDispatcher("orders.jsp").forward(request, response);
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
        bidder = loginUtil.getAuthenticatedBidder(request);
        if (bidder == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        List<Item> items = orderManager.findAllOrderedItems(bidder);
        
        String totalOrderStr = request.getParameter("totalOrder");
        Double totalOrder = Double.parseDouble(totalOrderStr);
        String address = request.getParameter("address");
        Date today = (Date) Calendar.getInstance().getTime();
        Long userId = bidder.getId();
        String itemsStr= "";
        for (Item item: items) {
            itemsStr += item.getItemName()+", ";
        }
        
        orderManager.confirmOrder(bidder, address);
      
        response.sendRedirect("orders");
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
