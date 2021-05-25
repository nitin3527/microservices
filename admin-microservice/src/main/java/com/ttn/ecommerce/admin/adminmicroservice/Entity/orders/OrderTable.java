package com.ttn.ecommerce.admin.adminmicroservice.Entity.orders;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.ttn.ecommerce.admin.adminmicroservice.Entity.Customer;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonFilter("orderFilter")
public class OrderTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long order_id;
    private double amountPaid;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id")
    Customer customer;

    private String paymentMethod;
    private String customerAddressCity;
    private String customerAddressState;
    private String customerAddressCountry;
    private String customerAddressLine;
    private String customerAddressZipcode;
    private String customerAddressLabel;

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCustomerAddressCity() {
        return customerAddressCity;
    }

    public void setCustomerAddressCity(String customerAddressCity) {
        this.customerAddressCity = customerAddressCity;
    }

    public String getCustomerAddressState() {
        return customerAddressState;
    }

    public void setCustomerAddressState(String customerAddressState) {
        this.customerAddressState = customerAddressState;
    }

    public String getCustomerAddressCountry() {
        return customerAddressCountry;
    }

    public void setCustomerAddressCountry(String customerAddressCountry) {
        this.customerAddressCountry = customerAddressCountry;
    }

    public String getCustomerAddressLine() {
        return customerAddressLine;
    }

    public void setCustomerAddressLine(String customerAddressLine) {
        this.customerAddressLine = customerAddressLine;
    }

    public String getCustomerAddressZipcode() {
        return customerAddressZipcode;
    }

    public void setCustomerAddressZipcode(String customerAddressZipcode) {
        this.customerAddressZipcode = customerAddressZipcode;
    }

    public String getCustomerAddressLabel() {
        return customerAddressLabel;
    }

    public void setCustomerAddressLabel(String customerAddressLabel) {
        this.customerAddressLabel = customerAddressLabel;
    }
}
