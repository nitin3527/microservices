package com.ttn.project.customermicroservice.Model;



import com.ttn.project.customermicroservice.Entity.Address;
import com.ttn.project.customermicroservice.Validation.FieldMatch;
import com.ttn.project.customermicroservice.Validation.ValidRegex;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@FieldMatch
public class CustomerRegistrationModel {

    @NotEmpty
    @Pattern(regexp = ValidRegex.EMAIL, message = "Invalid email")
    @NotNull
    private String email;

    @NotNull
    private String username;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Pattern(regexp = ValidRegex.PASSWORD)
    private String password;

    @NotNull
    @Pattern(regexp = ValidRegex.PASSWORD)
    private String confirmPassword;

    @Pattern(regexp = ValidRegex.PHONE, message = "10 digit number is allowed")
    private String contact;

    private Set<Address> address;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
    }
}
