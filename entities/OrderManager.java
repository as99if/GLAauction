/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface OrderManager {
    
    void addOrder(Item item, Bidder bidder);

    List<Item> findAllOrderedItems(Bidder bidder);

    void cancelOrder(Long itemId);

    Double calculateAllOrders(List<Item> items);

    void confirmOrder(Bidder bidder, String address);
    
}
