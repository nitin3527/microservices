package com.ttn.ecommerce.seller.sellermicroservice.Entity.products;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.ttn.ecommerce.seller.sellermicroservice.utilites.HashMapConverter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

@Entity
@JsonFilter("productVariationFilter")
public class ProductVariation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int quantity;
    private String imageName;
    private double price;
    private Boolean isActive;

    @Column
    @CreatedDate
    private Date createdDate;

    @Column
    @LastModifiedDate
    private Date modifiedDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    @Convert(converter = HashMapConverter.class)
    private Map<String, Object> productsMetadata;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Map<String, Object> getProductsMetadata() {
        return productsMetadata;
    }

    public void setProductsMetadata(Map<String, Object> productsMetadata) {
        this.productsMetadata = productsMetadata;
    }

    @Override
    public String toString() {
        return "ProductVariation{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", imageName='" + imageName + '\'' +
                ", price=" + price +
                ", isActive=" + isActive +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", product=" + product +
                ", productsMetadata=" + productsMetadata +
                '}';
    }
}
