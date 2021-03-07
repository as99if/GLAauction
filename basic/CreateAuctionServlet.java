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
import java.util.Calendar;
import java.util.Date;
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
@WebServlet(name = "CreateAuctionServlet", urlPatterns = {"/createAuction"})
public class CreateAuctionServlet extends HttpServlet {

    @EJB
    private SellerManager sellerManager;
    
    @EJB
    private ItemManager itemManager;
    
    @EJB
    private LoginUtilSeller loginUtil;
    
    private String name;
    private String description;
    private Integer startPrice;
    private Integer duration; // days
    private String[] category;
    private List<Category> categories;
    
    private Seller seller; //user

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
        //System.out.println("werwerwe rwe rw ew rwe ");
        seller = loginUtil.getAuthenticatedSeller(request);
        List<Category> categories = itemManager.getCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("createAuction.jsp").forward(request, response);
        
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
        
        seller = loginUtil.getAuthenticatedSeller(request);
        if (seller == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        try {
            name = request.getParameter("itemName");
            description = request.getParameter("description");
            startPrice = Integer.parseInt(request.getParameter("initialPrice"));
            duration = Integer.parseInt(request.getParameter("duration"));
            category = request.getParameterValues("category");
            
            createAuction();
        } catch (Exception exc) {
            System.out.println(exc);
        }
        response.sendRedirect("createAuction");
    }
    
    public void createAuction() {
        
        Item item = new Item();
        item.setItemName(name);
        item.setDescription(description);
        item.setInitialPrice(startPrice);
        item.setSeller(seller);
        
        categories = new ArrayList<>();

        for(String cat : category) {
            categories.add(itemManager.findCategory(cat));
        }
        System.out.println(categories.toString());
        item.setCategories(categories);

        item.setDuration(duration);
        
        Date startDate = Calendar.getInstance().getTime();
        item.setBidStartDate(startDate);
        
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, duration);
        item.setBidEndDate(c.getTime());
        item.setStatus(Item.ON_SALE);
        itemManager.createAuction(item);
        
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
