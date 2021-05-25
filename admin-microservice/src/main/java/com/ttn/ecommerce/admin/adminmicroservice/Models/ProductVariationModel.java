package com.ttn.ecommerce.admin.adminmicroservice.Models;



import com.ttn.ecommerce.admin.adminmicroservice.validations.ValidRegex;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Map;

public class ProductVariationModel {

    @NotNull
    private int quantity;

    @Pattern(regexp = ValidRegex.IMAGE,message = "png, jpg,jpeg,gif are only supported")
    private String imageName;

    @Min(0)
    private double price;

    private long productId;
    private Map<String, Object> productsMetadata;
    private Boolean isActive;

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Map<String, Object> getProductsMetadata() {
        return productsMetadata;
    }

    public void setProductsMetadata(Map<String, Object> productsMetadata) {
        this.productsMetadata = productsMetadata;
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

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
