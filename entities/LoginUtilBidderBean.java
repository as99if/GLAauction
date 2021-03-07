/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
@Stateless
public class LoginUtilBidderBean implements LoginUtilBidder {

    @EJB
    private BidderManager bidderManager;
    
    @Override
    public Bidder getAuthenticatedBidder(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            Long userId = (Long) session.getAttribute("idUser");
            Bidder bidder = bidderManager.findBidderById(userId);
            
            request.setAttribute("user", bidder);
            
            return bidder;
        } catch(Exception exc) {
            return null;
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
