 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ItemDAOLocal;
import Entity.Item;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author asifshuvo
 */
@WebServlet(name = "ItemServlet", urlPatterns = {"/ItemServlet"})   // declare servlet mapping name
public class ItemServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB
    private ItemDAOLocal itemDao;
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        
        //- id, name, description, starting price, end date
        String action = request.getParameter("action");
        
        // have to addcheck null property
        Integer id = Integer.parseInt(request.getParameter("id")); // id auto generated
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Double startingPrice = Double.parseDouble(request.getParameter("startingPrice"));
        
        /*
        Date format : "7-Jun-2013";
        */
        //SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        //Date itemEndDate = formatter.parse(request.getParameter("itemEndDate"));
        String endDate = request.getParameter("endDate");
        // category -> need query
        // subcategory -> need query
        
        
        Item item = new Item(id, name, description, startingPrice, endDate);
        
        
         
        if( "Add".equalsIgnoreCase(action)){
            itemDao.addItem(item);
        } else if( "Edit".equalsIgnoreCase(action)){
            itemDao.editItem(item);
        } else if( "Delete".equalsIgnoreCase(action)){
            itemDao.deleteItem(id);
        } else if( "Search".equalsIgnoreCase(action)){
            item = itemDao.getItem(id);
        }
        
        request.setAttribute("item", item);
        request.setAttribute("allItem", itemDao.getAllItem());
        request.getRequestDispatcher("index.jsp").forward(request, response);
        /*response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ItemServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ItemServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ItemServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ItemServlet.class.getName()).log(Level.SEVERE, null, ex);
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
