package com.ttn.ecommerce.seller.sellermicroservice.Models;



import com.ttn.ecommerce.seller.sellermicroservice.Entity.Address;
import com.ttn.ecommerce.seller.sellermicroservice.validations.FieldMatch;
import com.ttn.ecommerce.seller.sellermicroservice.validations.ValidRegex;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@FieldMatch
public class SellerModel {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String username;

    @Pattern(regexp = ValidRegex.PASSWORD)
    private String password;

    @Pattern(regexp = ValidRegex.PASSWORD)
    private String confirmPassword;

    @Pattern(regexp = ValidRegex.EMAIL, message = "invalid email address")
    private String email;

    @Pattern(regexp = ValidRegex.GST, message = "Invalid Gst number")
    private String gst;

    @NotNull
    private String companyName;

    @Pattern(regexp = ValidRegex.PHONE)
    private String contact;

    private Set<Address> addresses;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
