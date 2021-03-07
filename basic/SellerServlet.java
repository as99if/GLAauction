/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic;

import entities.Category;
import entities.Item;
import entities.ItemManager;
import entities.LoginUtilSeller;
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
@WebServlet(name = "SellerServlet", urlPatterns = {"/sellerItems"})
public class SellerServlet extends HttpServlet {
    
    @EJB
    private SellerManager sellerManager;

    @EJB
    private ItemManager itemManager;

    @EJB
    private LoginUtilSeller loginUtil;

    Seller seller;
    
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
            out.println("<title>Servlet SellerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SellerServlet at " + request.getContextPath() + "</h1>");
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
        seller = loginUtil.getAuthenticatedSeller(request);
        if (seller == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        if (seller != null) {
            System.out.println("not null user");
            List<Item> items = itemManager.findItemsBySeller(seller);
            List<Category> cats = itemManager.getCategories();
            request.setAttribute("cats", cats);
            request.setAttribute("items", items);
        } else {
            request.setAttribute("items", new ArrayList<Item>());
        }
        

        request.setAttribute("user", seller);
        request.getRequestDispatcher("allItemsForSeller.jsp").forward(request, response);
    }
    


}
