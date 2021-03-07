/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

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
public class SellerManagerBean implements SellerManager {

    @PersistenceContext(unitName="SimpleWeb-ejbPU")
    private EntityManager em;
    
    @Override
    public Seller findSellerByFirstname(String firstname) {
        TypedQuery<Seller> query = em.createQuery("select u from Seller u where u.firstname = ?1", Seller.class);
        query.setParameter(1, firstname);
        try {
            return query.getSingleResult();
        } catch (Exception exc) {
            return null;
        }
    }

    @Override
    public Seller findSellerByPhoneNumber(String phoneNumber) {
        TypedQuery<Seller> query = em.createQuery("select u from Seller u where u.phoneNumber = ?1", Seller.class);
        query.setParameter(1, phoneNumber);
        try {
            return query.getSingleResult();
        } catch (Exception exc) {
            return null;
        }
    }

    @Override
    public Seller findSellerById(Long id) {
        return em.find(Seller.class, id);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
