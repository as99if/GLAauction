/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author User
 */
@Local
public interface LoginUtilBidder {
    
    Bidder getAuthenticatedBidder(HttpServletRequest request);
    
}
