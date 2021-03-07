/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author User
 */
@Stateless
@LocalBean
public class OrderManagerBean implements OrderManager {

    @PersistenceContext(unitName="SimpleWeb-ejbPU")
    private EntityManager em;
    
    @EJB
    private BidManager bidManager;
    
    @Override
    public void addOrder(Item item, Bidder bidder) {
        Orders order = bidder.getOrder();
        if (order == null) {
            order = new Orders();
            order.setBidder(bidder);
            em.persist(order);
        }
        
        item.setOrder(order);
        em.merge(item);
    }

    @Override
    public List<Item> findAllOrderedItems(Bidder bidder) {
        Orders order = bidder.getOrder();
        if (order == null || order.getItems() == null) {
            return new ArrayList<>();
        }
        
        List<Item> returnedItems = new ArrayList<>();
        List<Item> items = order.getItems();
        for (Item item : items) {
            Bid bid = bidManager.findMaxBid(item.getId());
            if (bid != null) 
                item.setMaxBid(bid.getBidPrice());
            else 
                item.setMaxBid(item.getInitialPrice());
            if (item.getStatus().equals(Item.SALE_CLOSED)) {
                returnedItems.add(item);
            }
        }
        
        return returnedItems;
    }

    @Override
    public void cancelOrder(Long itemId) {
        Item item = em.find(Item.class, itemId);
        if (item != null) {
            item.setOrder(null);
            item.setStatus(Item.ON_SALE);
            em.merge(item);
        }
    }

    @Override
    public Double calculateAllOrders(List<Item> items) {
        Double price = 0.0;
        for (Item item: items) {
            price += item.getMaxBid();
        }
        return price;
    }

    @Override
    public void confirmOrder(Bidder bidder, String address) {
        Orders order = bidder.getOrder();
        order.setDeliveryAddress(address);
        
        em.merge(order);
        
        List<Item> items = order.getItems();
        for (Item item: items) {
            item.setStatus(Item.ON_DELIVERY);
            em.merge(item);
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
