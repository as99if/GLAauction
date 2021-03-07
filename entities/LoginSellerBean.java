/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 *
 * @author User
 */
@Stateful
@LocalBean
public class LoginSellerBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    private Seller seller;
    
    @EJB
    private SellerManager sellerManager;
    
    @Remove
    public void logout() {
        System.out.println("logout");
        seller = null;
    }

    public void login(String name, String password) {
        seller = sellerManager.findSellerByFirstname(name);
        
    }

    public boolean isAuthenticated() {
        return (seller != null);
    }

    public Seller getSeller() {
        return seller;
    }
    
    
}
