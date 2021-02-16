/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Subcategory;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asifshuvo
 */
@Local
public interface SubcategoryDAOLocal {

    public void addSubcategory(Subcategory subcategory);

    public void editSubcategory(Subcategory subcategory);

    public void getSubcategory(long subcategoryId);

    public List<Subcategory> getAllSubcategory();
    
}
