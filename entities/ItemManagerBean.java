/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author User
 */
@Stateless
@LocalBean
public class ItemManagerBean implements ItemManager {

    @PersistenceContext(unitName = "SimpleWeb-ejbPU")
    private EntityManager em;
    
    @EJB
    BidManager bidManager;
    
    @Override
    public void createAuction(Item item) {
        em.persist(item);
        if (item.getCategories() == null) {
            System.out.println("============== Could not get categories ============ ");
            return;
        }
         for (Category category: item.getCategories()) {
             System.out.println("Persisting category:  " + category);
            category.addItem(item);
            em.merge(category);
        }
         System.out.println("persisting cateogry end ================ ");
    }

    @Override
    public List<Category> getCategories() {
        TypedQuery<Category> query = em.createQuery("select c from Category c", Category.class);
        return query.getResultList();
    }

    @Override
    public Category findCategory(String id) {
        Long idCat = Long.parseLong(id);
        return em.find(Category.class, idCat);
    }

    @Override
    public List<Item> findItemsBySeller(Seller seller) {
        TypedQuery<Item> query = em.createQuery("select i from Item i where i.seller = ?1", Item.class);
        query.setParameter(1, seller);
        List<Item> items = query.getResultList();
        /*
        try {
            setWinner(items);
        } catch(Exception exc) {
            System.out.println("errorrr");
        } */
        return items;
    }

    @Override
    public void deleteItem(Long id) {
         em.remove(em.find(Item.class, id));
    }

    @Override
    public List<Item> findAllActiveItems() {
        TypedQuery<Item> query = em.createQuery("select i from Item i where i.bidEndDate >= ?1", Item.class);
        query.setParameter(1, Calendar.getInstance().getTime());
        return query.getResultList();
    }

    @Override
    public Item findItemById(Long id) {
        return em.find(Item.class, id);
    }

    @Override
    public List<Item> findItemsByName(String name) {
        TypedQuery<Item> query = em.createQuery("select distinct(i) from Item i where i.itemName like ?1 and i.bidEndDate >= ?2", Item.class);
        query.setParameter(1, "%"+name+"%");
        query.setParameter(2, Calendar.getInstance().getTime());
        return query.getResultList();
    }

    @Override
    public List<Item> findItemsByCategory(Long id) {
        TypedQuery<Item> query = em.createQuery("select distinct(i) from Item i join fetch i.categories c where c.id = ?1 and i.bidEndDate >= ?2", Item.class);
        query.setParameter(1, id);
        query.setParameter(2, Calendar.getInstance().getTime());
        return query.getResultList();
    }

    @Override
    public List<Item> findItemsByBidder(Long bidderId) {
        TypedQuery<Item> query = em.createQuery("select distinct(i) from Item i join fetch i.bids b where b.bidder.id=?1", Item.class);
        query.setParameter(1, bidderId);
        List<Item> items = query.getResultList();
        
        for (Item item:items) {
            Bid bid = bidManager.findMaxBid(item.getId());
            if (bid != null) 
                item.setMaxBid(bid.getBidPrice());
            else 
                item.setMaxBid(item.getInitialPrice());
            
            bid = bidManager.findMyMaxBid(item.getId(), bidderId);
            if (bid != null) 
                item.setMyMaxBid(bid.getBidPrice());
            else 
                item.setMyMaxBid(0);
        }
        setWinner(items);
        return items;
    }

    @Override
    public void setWinner(List<Item> items) {
        System.out.println("SET Winner method --- " + items.get(0));
        for (Item item : items) {
            if (item.getBidEndDate().before(Calendar.getInstance().getTime())) {
                System.out.println("INSIDE IF CONDITION SET WINNER METHOD");
                System.out.println("setWinner ItemManagerBean winner is ---- " + bidManager.findWinner(item.getId()));
                Bidder winner = bidManager.findWinner(item.getId());
                item.setWinner(winner);
                System.out.println("Winner: " + item.getItemName()+winner.getFirstname());
            }
        }
    }

    @Override
    public void changeStatus(Item item, String status) {
        item.setStatus(status);
        em.merge(item);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
