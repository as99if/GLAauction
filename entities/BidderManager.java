/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface BidderManager {
    
    Bidder findBidderByFirstname(String firstname);
    
    Bidder findBidderByPhoneNumber(String phoneNumber);
    
    Bidder findBidderById(Long id);
    
    //public boolean bid(Long itemId, String username, Double bidValue);
    
}
