package com.Auth2.auth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    UserDetailsService userService;

    public ResourceServerConfiguration() {
        super();
    }

    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authenticationProvider;
    }

    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/confirm-account").permitAll()
                .antMatchers("/registerCustomer").anonymous()
                .antMatchers("/registerSeller").anonymous()
                .antMatchers("/resendConfirmationToken").anonymous()
                .antMatchers("/forgotPassword").anonymous()
                .antMatchers("/resetPassword").anonymous()
                .antMatchers("/v3/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()

                .antMatchers("/customer/**").hasAnyRole("CUSTOMER")
//                .antMatchers("/customer/updateCustomer").hasAnyRole("CUSTOMER")
//                .antMatchers("/customer/addCustomerAddress").hasAnyRole("CUSTOMER")
//                .antMatchers("/customer/showAllCustomerAddresses").hasAnyRole("CUSTOMER")
//                .antMatchers("/customer/updateCustomerAddress").hasAnyRole("CUSTOMER")
//                .antMatchers("/customer/deleteAddress").hasAnyRole("CUSTOMER")
//                .antMatchers("/customer/updateCustomerPassword").hasAnyRole("CUSTOMER")
//                .antMatchers("/customer/customerDetails").hasAnyRole("CUSTOMER")
//                .antMatchers("/customer/showAllCategory").hasAnyRole("CUSTOMER")
//                .antMatchers("/customer/getProduct").hasAnyRole("CUSTOMER")
//                .antMatchers("/customer/getAllProduct").hasAnyRole("CUSTOMER")
//                .antMatchers("/customer/showAllRelatedProducts").hasAnyRole("CUSTOMER")
//                .antMatchers("/customer/filterAllProducts").hasAnyRole("CUSTOMER")
//                  .antMatchers("/customer/AddToCart").hasAnyRole("CUSTOMER")

                .antMatchers("/seller/**").hasAnyRole("SELLER")
//                .antMatchers("/seller/details").hasAnyRole("SELLER")
//                .antMatchers("/seller/updatePassword").hasAnyRole("SELLER")
//                .antMatchers("/seller/updateProfile").hasAnyRole("SELLER")
//                .antMatchers("/seller/updateAddress").hasAnyRole("SELLER")
//                .antMatchers("/seller/showAllCategory").hasAnyRole("SELLER")
//                .antMatchers("/seller/product/addNewProduct").hasAnyRole("SELLER")
//                .antMatchers("/seller/createNewVariation").hasAnyRole("SELLER")
//                .antMatchers("/seller/getAllSellerProducts").hasAnyRole("SELLER")
//                .antMatchers("/seller/findProductVariation").hasAnyRole("SELLER")
//                .antMatchers("/seller/showAllProducts").hasAnyRole("SELLER")
//                .antMatchers("/seller/showAllProductVariation").hasAnyRole("SELLER")
//                .antMatchers("/seller/deleteProduct").hasAnyRole("SELLER")
//                .antMatchers("/seller/updateProduct").hasAnyRole("SELLER")
//                .antMatchers("/seller/updateProductVariation").hasAnyRole("SELLER")
//

                .antMatchers("/admin/**").hasAnyRole("ADMIN")
//                .antMatchers("/admin/showAllCustomers").hasAnyRole("ADMIN")
//                .antMatchers("/admin/addCategoryMetadataField").hasAnyRole("ADMIN")
//                .antMatchers("/admin/showAllSellers").hasAnyRole("ADMIN")
//                .antMatchers("/admin/activateCustomer/{id}").hasAnyRole("ADMIN")
//                .antMatchers("/admin/deActivateCustomer/{id}").hasAnyRole("ADMIN")
//                .antMatchers("/admin/showAllCategoryMetadataFields").hasAnyRole("ADMIN")
//                .antMatchers("/admin/addCategory").hasAnyRole("ADMIN")
//                .antMatchers("/admin/showCategory/{id}").hasAnyRole("ADMIN")
//                .antMatchers("/admin/showAllCategories").hasAnyRole("ADMIN")
//                .antMatchers("/admin/updateCategory").hasAnyRole("ADMIN")
//                .antMatchers("/admin/addMetaDataFieldValue").hasAnyRole("ADMIN")
//                .antMatchers("/admin/updateMetadataFieldValue").hasAnyRole("ADMIN")
//                .antMatchers("/admin/getProduct").hasAnyRole("ADMIN")
//                .antMatchers("/admin/getAllProduct").hasAnyRole("ADMIN")
//                .antMatchers("/admin/deactivateProduct").hasAnyRole("ADMIN")
//                .antMatchers("/admin/activateProduct").hasAnyRole("ADMIN")
//
                .antMatchers("/admin/deActivateSeller/{id}").hasAnyRole("ADMIN")
                .antMatchers("/admin/activateSeller/{id}").hasAnyRole("ADMIN")



                .antMatchers("doLogout").hasAnyRole(" CUSTOMER","ADMIN","SELLER")
                .antMatchers("forgotPassword").hasAnyRole(" CUSTOMER","ADMIN","SELLER")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .csrf().disable();
    }
}
