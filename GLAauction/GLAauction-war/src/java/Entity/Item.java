/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
//import java.time.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author asifshuvo
 */
@Entity
@Table                              // as a table in db
@NamedQueries(@NamedQuery(name = "Item.getAll", query = " SELECT e FROM Item e"))   // NamedQuery, set up a query under a 'name'
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column 
    private Long id;
    
    @Column                         // as a column in db
    private String name;
    @Column
    private String description;
    @Column
    private Double starting_price;
    @Column
    private String endDate; 

    public Item() {
    }
    
    public Item(String name, String description, Double starting_price, String endDate) {
        this.name = name;
        this.description = description;
        this.starting_price = starting_price;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getStarting_price() {
        return starting_price;
    }

    public void setStarting_price(double starting_price) {
        this.starting_price = starting_price;
    }


    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String duration_of_action) {
        this.endDate = duration_of_action;
    }
    
    
   
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Item[ id=" + id + " ]";
    }
    
}
