package com.ttn.ecommerce.admin.adminmicroservice.Resource;



import com.ttn.ecommerce.admin.adminmicroservice.Service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetail implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserServices userServices;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Trying to authenticate user ::" + username);
        UserDetails userDetails = userServices.loadUserByUsername(username);
        return userDetails;
    }
}
