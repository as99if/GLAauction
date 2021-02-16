/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Category;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asifshuvo
 */
@Local
public interface CategoryDAOLocal {

    public void addCategory(Category category);

    public void editCategory(Category category);

    public void getCategory(long categoryId);

    List<Category> getAllCategory();
    
    
    
}
