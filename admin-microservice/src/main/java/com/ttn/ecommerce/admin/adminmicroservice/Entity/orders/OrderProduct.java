package com.ttn.ecommerce.admin.adminmicroservice.Entity.orders;



import com.ttn.ecommerce.admin.adminmicroservice.Entity.products.ProductVariation;

import javax.persistence.*;

@Entity
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long order_product_id;
    private int quantity;
    private double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    OrderTable orderTable;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_variation_id")
    ProductVariation productVariation;

    public long getOrder_product_id() {
        return order_product_id;
    }

    public void setOrder_product_id(long order_product_id) {
        this.order_product_id = order_product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OrderTable getOrder() {
        return orderTable;
    }

    public void setOrder(OrderTable orderTable) {
        this.orderTable = orderTable;
    }

    public ProductVariation getProductVariation() {
        return productVariation;
    }

    public void setProductVariation(ProductVariation productVariation) {
        this.productVariation = productVariation;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "order_product_id=" + order_product_id +
                ", quantity=" + quantity +
                ", price=" + price +
                ", orderTable=" + orderTable +
                ", productVariation=" + productVariation +
                '}';
    }
}
