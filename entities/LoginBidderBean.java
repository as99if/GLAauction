/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.Remove;

/**
 *
 * @author User
 */
@Stateless
@LocalBean
public class LoginBidderBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private Bidder bidder;
    
    @EJB
    private BidderManager bidderManager;
    
    @Remove
    public void logout() {
        System.out.println("logout");
        bidder = null;
    }

    public void login(String name, String password) {
        bidder = bidderManager.findBidderByFirstname(name);
        
    }

    public boolean isAuthenticated() {
        return (bidder != null);
    }

    public Bidder getUser() {
        return bidder;
    }
    
    
}
