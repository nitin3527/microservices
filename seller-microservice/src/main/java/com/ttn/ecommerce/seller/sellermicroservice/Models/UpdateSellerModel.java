package com.ttn.ecommerce.seller.sellermicroservice.Models;



import com.ttn.ecommerce.seller.sellermicroservice.validations.ValidRegex;

import javax.validation.constraints.Pattern;

public class UpdateSellerModel {
    private String username;
    private String firstName;
    private String lastName;

    @Pattern(regexp = ValidRegex.GST, message = "Invalid Gst number")
    private String gst;
    private String companyContact;
    private String companyName;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
