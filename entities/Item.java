/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author User
 */
@Entity
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String itemName;
    private String description;
    private String status;
    private Integer duration;
    
    @Temporal(TemporalType.DATE)
    private Date bidEndDate;
    
    @Temporal(TemporalType.DATE)
    private Date bidStartDate;
    
    private int initialPrice;
    
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Bid> bids;
    
    @ManyToOne
    private Seller seller;
    
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "items")
    private List<Category> categories;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Orders order;
    
    public static final String ON_SALE = "ON_SALE";
    public static final String SALE_CLOSED = "SALE_CLOSED";
    public static final String ON_DELIVERY = "ON_DELIVERY";
    public static final String DELIVERED = "DELIVERED";
    
    private Integer maxBid;
    
    transient Integer myMaxBid;
    
    @OneToOne
    private Bidder winner;
    
    public Item() {}
    
    public Item(Long itemId) {
        this.id = itemId;
    }
    
    public Item(Long id, String itemName, Date bidStartDate, Date endDate, Integer duration, int initialPrice, Seller seller, String status, List<Category> Categories) {
        this.id = id;
        this.itemName = itemName;
        this.bidStartDate = bidStartDate;
        this.initialPrice = initialPrice;
        this.bidEndDate = endDate;
        this.categories = Categories;
        this.status = status;
        this.seller = seller;
        this.duration = duration;
    }
    
    public Item(String itemName, Date bidStartDate, Integer duration, Date endDate, Integer initialPrice) {
        this.itemName = itemName;
        this.bidStartDate = bidStartDate;
        this.initialPrice = initialPrice;
        this.bidEndDate = endDate;
        this.duration = duration;
    }
    
    public Item(String name, String description, Integer initialPrice, Integer duration) {
        this.itemName = name;
        this.description = description;
        this.initialPrice = initialPrice;
        this.duration = duration;
    }
    
    public Item(String name, String description, Integer startPrice, Integer duration, Integer myMaxBid) {
        this.itemName = name;
        this.description = description;
        this.initialPrice = startPrice;
        this.duration = duration;
        this.myMaxBid = myMaxBid;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public Date getBidEndDate() {
        return bidEndDate;
    }

    public void setBidEndDate(Date bidEndDate) {
        this.bidEndDate = bidEndDate;
    }

    public Date getBidStartDate() {
        return bidStartDate;
    }

    public void setBidStartDate(Date bidStartDate) {
        this.bidStartDate = bidStartDate;
    }

    public Integer getInitialPrice() {
        return initialPrice;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public void setInitialPrice(Integer initialPrice) {
        this.initialPrice = initialPrice;
    }
    
    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }
    
    public Bid addBid(Bid bid) {
        getBids().add(bid);
        return bid;
    }

    public Bid removeBid(Bid bid) {
        getBids().remove(bid);
        return bid;
    }
    
    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }
    
    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Seller getSeller() {
        return seller;
    }
    
    public List<Category> getCategories() {
        return categories;
    }
    
    public List<String> getCategoryNames() {
        List<String> categoryNames = new ArrayList<>();
        for (Category cat : categories) {
            categoryNames.add(cat.getName());
        }
        return categoryNames;
    }
    
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    
    public void addCategory(Category category) {
        categories.add(category);
    }
    
    public Bidder getWinner() {
        return winner;
    }

    public void setWinner(Bidder winner) {
        this.winner = winner;
    }
    
    public Integer getMaxBid() {
       if (maxBid == null)
           return 0;
       
        return maxBid;
    }

    public void setMaxBid(Integer myMaxBid) {
        this.maxBid = myMaxBid;
    }
    
    public Integer getMyMaxBid() {
        if (myMaxBid == null)
            return 0;
        return myMaxBid;
    }

    public void setMyMaxBid(Integer myMaxBid) {
        this.myMaxBid = myMaxBid;
    }
    
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
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
