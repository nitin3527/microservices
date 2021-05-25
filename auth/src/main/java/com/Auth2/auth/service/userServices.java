package com.Auth2.auth.service;

import com.Auth2.auth.enitity.AppUser;
import com.Auth2.auth.enitity.Role;
import com.Auth2.auth.enitity.User;
import com.Auth2.auth.repository.UserRepository;
import com.Auth2.auth.security.GrantAuthorityImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class userServices {

    @Autowired
    UserRepository userRepository;
    public AppUser loadUserByUsername(String username){
        User user = userRepository.findAllByUsernameIgnoreCase(username);
        Role role = user.getRole();
        if(user != null){
            if(username != null){
                return new AppUser(user.getUser_id(),user.getFirstName(),
                        user.getUsername(),user.getPassword(),user.getNonLocked(),user.getActive(),
                        Arrays.asList(new GrantAuthorityImpl(role.getAuthority())));
            }else{
                throw new UsernameNotFoundException("Username does not exist");
            }
        }else{
            throw new RuntimeException("product not found");
        }
    }
}
