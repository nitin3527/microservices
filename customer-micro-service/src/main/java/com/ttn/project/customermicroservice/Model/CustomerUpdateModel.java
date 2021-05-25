package com.ttn.project.customermicroservice.Model;



import com.ttn.project.customermicroservice.Validation.ValidRegex;

import javax.validation.constraints.Pattern;

public class CustomerUpdateModel {

    private String username;


    private String firstName;


    private String lastName;

    @Pattern(regexp = ValidRegex.PHONE, message = "Invalid Contact")
    private String contact;

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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
