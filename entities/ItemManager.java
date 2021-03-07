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
public interface ItemManager {
    
    void createAuction(Item item);

    List<Category> getCategories();

    Category findCategory(String id);

    List<Item> findItemsBySeller(Seller user);

    void deleteItem(Long id);

    List<Item> findAllActiveItems();

    Item findItemById(Long id);
    
    List<Item> findItemsByName(String name);
    
    List<Item> findItemsByCategory(Long id);

    List<Item> findItemsByBidder(Long userId);

    void setWinner(List<Item> items);

    void changeStatus(Item item, String status);
    
}
