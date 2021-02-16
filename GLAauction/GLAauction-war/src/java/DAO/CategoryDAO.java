/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Category;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author asifshuvo
 */
@Stateless
public class CategoryDAO implements CategoryDAOLocal {

    @Override
    public void addCategory(Category category) {
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void editCategory(Category category) {
    }

    @Override
    public void getCategory(long categoryId) {
    }

    @Override
    public List<Category> getAllCategory() {
        return null;
    }
    
    
    
}
