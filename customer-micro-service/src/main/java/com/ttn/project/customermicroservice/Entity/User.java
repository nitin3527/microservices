package com.ttn.project.customermicroservice.Entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonFilter("userFilter")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private Boolean isActive;
    private Boolean isDeleted;
    private Boolean isNonLocked;
    private int attempts = 0;

    @JsonManagedReference
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Role role;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Address> addresses;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean iActive) {
        isActive = iActive;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Boolean getNonLocked() {
        return isNonLocked;
    }

    public void setNonLocked(Boolean nonLocked) {
        isNonLocked = nonLocked;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                ", isDeleted=" + isDeleted +
                ", isNonLocked=" + isNonLocked +
                ", Attempts=" + attempts +
                ", role=" + role +
                ", addresses=" + addresses +
                '}';
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }
    public void addAddress(Address address){
        if(address != null){
            if(addresses == null){
                addresses = new HashSet<>();
            }
            address.setUser(this);
            addresses.add(address);
        }
    }

    public void addRole(Role role){
        if(role != null){
            role.setUser(this);
        }
    }
}
