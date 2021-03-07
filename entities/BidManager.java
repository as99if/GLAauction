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
public interface BidManager {
    
    public Bid findMaxBid(Long itemId);

    public boolean bid(Long itemId, String username, int bidValue);

    Bid findMyMaxBid(Long itemId, Long userId);
    
    void deleteAllBids(Long idItem, String username);
    
    Bidder findWinner(Long itemId);
   
    
}
