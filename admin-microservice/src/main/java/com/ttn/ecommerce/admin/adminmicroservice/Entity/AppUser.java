package com.ttn.ecommerce.admin.adminmicroservice.Entity;



import com.ttn.ecommerce.admin.adminmicroservice.Resource.GrantAuthorityImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AppUser implements UserDetails {
    private long uid;
    private String name;
    private String username;
    private String password;
    private Boolean isNonLocked;
    private Boolean isActive;
    List<GrantAuthorityImpl> grantAuthorities;

    public AppUser(long uid, String name, String username, String password, Boolean isNonLocked, Boolean isActive, List<GrantAuthorityImpl> grantAuthorities) {
        this.uid = uid;
        this.name = name;
        this.username = username;
        this.password = password;
        this.isNonLocked = isNonLocked;
        this.isActive = isActive;
        this.grantAuthorities = grantAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isNonLocked=" + isNonLocked +
                ", isActive=" + isActive +
                ", grantAuthorities=" + grantAuthorities +
                '}';
    }
}
