/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Item;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asifshuvo
 */
@Local
public interface ItemDAOLocal {

    void addItem(Item item);

    public void editItem(Item item);

    public List<Item> getAllItem();

    public Item getItem(Integer itemId);

    public void deleteItem(Integer itemId);
    
}
