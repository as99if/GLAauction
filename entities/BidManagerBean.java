/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author User
 */
@Stateless
@LocalBean
public class BidManagerBean implements BidManager {
    
    @PersistenceContext(unitName = "SimpleWeb-ejbPU")
    private EntityManager em;
    
    @EJB
    private ItemManager itemManager;
    
    @EJB
    private BidderManager bidderManager;
    
    @Override
    public Bid findMaxBid(Long itemId) {
        TypedQuery<Bid> query = em.createQuery("select d from Bid d join fetch d.item where d.item.id = ?1 ORDER BY d.bidPrice DESC", Bid.class);
        query.setParameter(1, itemId);
        List<Bid> bids = query.getResultList();
        if (bids != null && bids.size() > 0) {
            return bids.get(0);
        }
        return null;
    }
    

    @Override
    public boolean bid(Long itemId, String firstname, int bidPrice) {
        Bid bid = new Bid();
        
        Item item = itemManager.findItemById(itemId);
        Bidder bidder = bidderManager.findBidderByFirstname(firstname);
        
        bid.setItem(item);
        bid.setBidder(bidder);
        bid.setBidPrice(bidPrice);
        
        Bid maxBid = findMaxBid(itemId);
        if ((maxBid == null && bidPrice > item.getInitialPrice()) || bidPrice > maxBid.getBidPrice()) {
            em.persist(bid);
            return true;
        }
        
        return false;
    }

    @Override
    public Bid findMyMaxBid(Long itemId, Long bidderId) {
        TypedQuery<Bid> query = em.createQuery("select d from Bid d join fetch d.item where d.item.id = ?1 and d.bidder.id=?2 ORDER BY d.bidPrice DESC", Bid.class);
        query.setParameter(1, itemId);
        query.setParameter(2, bidderId);
        List<Bid> bids = query.getResultList();
        if (bids != null && bids.size() > 0) {
            return bids.get(0);
        }
        return null;
    }

    @Override
    public void deleteAllBids(Long itemId, String firstname) {
        Query query = em.createQuery("delete from Bid b where b.bidder.firstname=?1 and b.item.id=?2");
        query.setParameter(1, firstname);
        query.setParameter(2, itemId);
        query.executeUpdate();
    }

    @Override
    public Bidder findWinner(Long itemId) {
        TypedQuery<Bid> query = em.createQuery("select d from Bid d join fetch d.item where d.item.id = ?1 ORDER BY d.bidPrice DESC", Bid.class);
        query.setParameter(1, itemId);
        List<Bid> bids = query.getResultList();
        if (bids != null && bids.size() > 0) {
            System.out.println("Winner from findWinner method BidManagerBean ---- " + bids.get(0).getBidder());
            return bids.get(0).getBidder();
        }
        return null;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
