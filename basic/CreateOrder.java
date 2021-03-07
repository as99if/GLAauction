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
import entities.Orders;
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
@WebServlet(name = "CreateOrder", urlPatterns = {"/createOrder"})
public class CreateOrder extends HttpServlet {
    
    @EJB
    private BidderManager bidderManager;
    
    @EJB
    private ItemManager itemManager;
    
    @EJB
    private LoginUtilBidder loginUtil;
    
    @EJB
    private OrderManager orderManager;
    
    private Bidder bidder;
    private Orders order;
    private Item item;
    
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
            out.println("<title>Servlet CreateOrder</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateOrder at " + request.getContextPath() + "</h1>");
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
        
        String id = request.getParameter("itemId");
        System.out.println("ITEM ID STRING === " + id);
        long idItem = Long.parseLong(id);
        System.out.println("ITEM ID converted to LONG === " + idItem);
        item = itemManager.findItemById(idItem);
        
        request.setAttribute("item", item);
        request.setAttribute("bidder", bidder);
        request.getRequestDispatcher("createOrder.jsp").forward(request, response);

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
            System.out.println("Bidder is not authenticated in the post method - ORDERS");
            response.sendRedirect("login.jsp");
            return;
        }
        
        try {
            System.out.println("Bidder is authenticated in the post method - ORDERS");
            order = new Orders();
            order.setBidder(bidder);
            List<Item> items = new ArrayList<>();
            items.add(item);
            order.setItems(items);
            
            itemManager.setWinner(items);
            
            String deliveryAddress = request.getParameter("delivery");
            System.out.println("Delivery address : " + deliveryAddress);
            
            System.out.println("ITEM in OrderServlet -----  " + item);
            orderManager.addOrder(items.get(0), bidder);
            order.setDeliveryAddress(deliveryAddress);
            System.out.println("Added order to order manager");
        } catch (Exception exc) {
            System.out.println(exc);
        }
        response.sendRedirect("bidderItems");
        //request.getRequestDispatcher("orders.jsp").forward(request, response);
        //response.sendRedirect("createOrder");
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
