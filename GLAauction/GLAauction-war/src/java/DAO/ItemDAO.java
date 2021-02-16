/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Item;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author asifshuvo
 */
@Stateless
public class ItemDAO implements ItemDAOLocal {
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    // import library
    // select implement from local 
    
    @PersistenceContext
    private EntityManager entitymanager;       // EntityManager to manage 
                                               // entity in DB 
    
    @Override
    public void addItem(Item item) {
        entitymanager.persist(item);    // persist -> add
    }
    
    @Override
    public void editItem(Item item) {
        entitymanager.merge(item);      // merge -> edited Item add
    }

    @Override
    public Item getItem(Long itemId) {
        return  entitymanager.find(Item.class, itemId);
    }

    @Override
    public void deleteItem(Long itemId) {
        entitymanager.remove(getItem(itemId));
    }

    @Override
    public List<Item> getAllItem() {
        return entitymanager.createNamedQuery("Item.getAll").getResultList();
                // excecut query with a name 
    }
    
    
    
}
