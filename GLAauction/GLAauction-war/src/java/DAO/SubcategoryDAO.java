/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Subcategory;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author asifshuvo
 */
@Stateless
public class SubcategoryDAO implements SubcategoryDAOLocal {

    @Override
    public void addSubcategory(Subcategory subcategory) {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void editSubcategory(Subcategory subcategory) {
    }

    @Override
    public void getSubcategory(long subcategoryId) {
    }

    @Override
    public List<Subcategory> getAllSubcategory() {
        return null;
    }
    
    
}
