/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

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
public class BidderManagerBean implements BidderManager {

    @PersistenceContext(unitName="SimpleWeb-ejbPU")
    private EntityManager em;

    
    @Override
    public Bidder findBidderByFirstname(String firstname) {
        System.out.println("Asked thing: " + firstname);
        TypedQuery<Bidder> query = em.createQuery("select u from Bidder u where u.firstname = ?1", Bidder.class);
        query.setParameter(1, firstname);
        try {
            System.out.println("FInding bidder: found");
            System.out.println(query.getSingleResult());
            Bidder bidder = query.getSingleResult();
            return query.getSingleResult();
        } catch (Exception exc) {
            System.out.println("FInding bidder: return null");
            return null;
        }
    }
 

    @Override
    public Bidder findBidderByPhoneNumber(String phoneNumber) {
        TypedQuery<Bidder> query = em.createQuery("select u from Bidder u where u.phoneNumber = ?1", Bidder.class);
        query.setParameter(1, phoneNumber);
        try {
            return query.getSingleResult();
        } catch (Exception exc) {
            return null;
        }
    }

    @Override
    public Bidder findBidderById(Long id) {
        return em.find(Bidder.class, id);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
    
}
