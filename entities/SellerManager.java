/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface SellerManager {
    
    Seller findSellerByFirstname(String firstname);
    
    Seller findSellerByPhoneNumber(String phoneNumber);
    
    Seller findSellerById(Long id);
    
}
