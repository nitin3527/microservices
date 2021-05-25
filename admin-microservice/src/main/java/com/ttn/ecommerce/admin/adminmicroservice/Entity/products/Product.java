package com.ttn.ecommerce.admin.adminmicroservice.Entity.products;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.ttn.ecommerce.admin.adminmicroservice.Entity.Seller;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@JsonFilter("productFilter")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private Boolean isCancellable;
    private Boolean isReturnable;
    private String brand;
    private Boolean isActive;
    private Boolean isDeleted;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")
    private Set<ProductVariation> variations;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id")
    Seller seller;

    @OneToMany(mappedBy ="product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ProductReview> reviews;

    public long getId() {
        return id;
    }

    public void setId(int id) {
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

    public Boolean getCancellable() {
        return isCancellable;
    }

    public void setCancellable(Boolean cancellable) {
        isCancellable = cancellable;
    }

    public Boolean getReturnable() {
        return isReturnable;
    }

    public void setReturnable(Boolean returnable) {
        isReturnable = returnable;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return "Product{" +
                "p_id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isCancellable=" + isCancellable +
                ", isReturnable=" + isReturnable +
                ", brand='" + brand + '\'' +
                ", isActive=" + isActive +
                ", isDeleted=" + isDeleted +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", category=" + category +
                ", variations=" + variations +
                ", seller=" + seller +
                ", reviews=" + reviews +
                '}';
    }
}
