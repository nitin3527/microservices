package com.ttn.ecommerce.admin.adminmicroservice.Entity.orders;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.ttn.ecommerce.admin.adminmicroservice.Entity.Customer;
import com.ttn.ecommerce.admin.adminmicroservice.Entity.products.ProductVariation;

import javax.persistence.*;

@Entity
@JsonFilter("cartFilter")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private int quantity;
    @ManyToOne
    @JoinColumn(name = "product_variation_id")
    private ProductVariation productVariation;

    private Boolean isWishListItem;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductVariation getProductVariation() {
        return productVariation;
    }

    public void setProductVariation(ProductVariation productVariation) {
        this.productVariation = productVariation;
    }

    public Boolean getWishListItem() {
        return isWishListItem;
    }

    public void setWishListItem(Boolean wishListItem) {
        isWishListItem = wishListItem;
    }
}
