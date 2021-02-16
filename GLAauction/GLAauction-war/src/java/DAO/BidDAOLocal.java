/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Bid;
import Entity.Item;
import javax.ejb.Local;

/**
 *
 * @author asifshuvo
 */
@Local
public interface BidDAOLocal {

    public void addBid(Bid bid);
    
}
