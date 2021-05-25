package com.ttn.ecommerce.seller.sellermicroservice.Service;



import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import com.ttn.ecommerce.seller.sellermicroservice.Entity.*;
import com.ttn.ecommerce.seller.sellermicroservice.Entity.products.Product;
import com.ttn.ecommerce.seller.sellermicroservice.Models.SellerModel;
import com.ttn.ecommerce.seller.sellermicroservice.Models.UpdatePasswordModel;
import com.ttn.ecommerce.seller.sellermicroservice.Repository.AddressRepository;
import com.ttn.ecommerce.seller.sellermicroservice.Repository.ProductRepository;
import com.ttn.ecommerce.seller.sellermicroservice.Repository.SellerRepository;
import com.ttn.ecommerce.seller.sellermicroservice.Repository.UserRepository;
import com.ttn.ecommerce.seller.sellermicroservice.Resource.GrantAuthorityImpl;
import com.ttn.ecommerce.seller.sellermicroservice.exceptions.IdNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    Date date = new Date();

    @Autowired
    EmailSendService emailSendService;

//    @Autowired
//    CustomerRepo customerRepo;

    @Autowired
    SellerRepository sellerRepository;

//    @Autowired
//    ConfirmationTokenRepository confirmationTokenRepository;

//    @Autowired
//    ForgetPasswordTokenRepository forgetPasswordTokenRepository;
//
    @Autowired
    ProductRepository productRepository;
//
//    @Autowired
//    ProductVariationRepository productVariationRepository;

    @Autowired
    AddressRepository addressRepository;

    public List<User> findAllUsers(){
        return userRepository.findAllUsers();
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }
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
            throw new IdNotFoundException("product not found");
        }
    }

    public User registerUser(User user){
        String pass = user.getPassword();
        user.setPassword(passwordEncoder.encode(pass));
        Role role = new Role();
        role.setAuthority("ROLE_ADMIN");
        user.setRole(role);
        user.setActive(true);
        user.setDeleted(false);
        user.setNonLocked(true);
        userRepository.save(user);
        return user;
    }

//    @Transactional
//    public String registerNewCustomer(CustomerRegistrationModel customerModel){
//        User existingUsername = userRepository.findAllByUsernameIgnoreCase(customerModel.getUsername());
//        User existingEmail = userRepository.findAllByEmailIgnoreCase(customerModel.getEmail());
//
//        if(existingEmail != null){
//            return "This Email already exist";
//        }else if(existingUsername != null){
//            return "Username already exist";
//        }
//        else {
//            ModelMapper modelMapper = new ModelMapper();
//            Customer customer1 = modelMapper.map(customerModel, Customer.class);
//            Role role = new Role();
//            role.setAuthority("ROLE_CUSTOMER");
//            customer1.addRole(role);
//            customer1.setRole(role);
//            customer1.setActive(false);
//            customer1.setDeleted(false);
//            customer1.setNonLocked(true);
//            customer1.setAttempts(0);
//            customer1.setPassword(passwordEncoder.encode(customer1.getPassword()));
//
//            userRepository.save(customer1);
//
//            ConfirmationToken confirmationToken = new ConfirmationToken(customer1);
//            confirmationTokenRepository.save(confirmationToken);
//
//                SimpleMailMessage mail = new SimpleMailMessage();
//                mail.setTo(customer1.getEmail());
//                mail.setSubject("Complete your registration");
//                mail.setText("Click here to complete registration " +
//                        "localhost:8080/confirm-account?token=" +
//                        confirmationToken.getConformationToken());
//                mail.setFrom("khandelwal.nitin0001@gmail.com");
//                emailSendService.sendEmail(mail);
//
//            return "Customer successfully registered";
//        }
//
//
//    }
//    @Transactional
//    public String confirmAccount(String token){
//        ConfirmationToken confirmationToken = confirmationTokenRepository.findByConfirmationToken(token);
//        if(confirmationToken != null){
//            if(confirmationToken.getExpiryDate().getTime() - date.getTime() <= 0){
//                return "token is being expired regenerate the token";
//            }else{
//                User user = userRepository.findAllByEmailIgnoreCase(confirmationToken.getUser().getEmail());
//                user.setActive(true);
//                userRepository.save(user);
//                return "Account activated";
//            }
//        }
//        return "This token doesn't exist in database";
//    }

    @Transactional
    public String registerNewSeller(SellerModel sellerModel) {
        User existingUsername = userRepository.findAllByUsernameIgnoreCase(sellerModel.getUsername());
        User existingEmail = userRepository.findAllByEmailIgnoreCase(sellerModel.getEmail());

        if (existingEmail != null) {
            return "This Email already exist";
        } else if (existingUsername != null) {
            return "Username already exist";
        } else if (sellerRepository.findByGst(sellerModel.getGst()) != null) {
            return "gst number already exist";
        } else if (sellerRepository.findByCompanyName(sellerModel.getCompanyName()) != null) {
            return "company name already exist";
        } else {
            Seller seller1 = new Seller();
            Role role = new Role();
            role.setAuthority("ROLE_SELLER");
            seller1.addRole(role);
            seller1.setRole(role);
            seller1.setActive(false);
            seller1.setDeleted(false);
            seller1.setNonLocked(true);
            seller1.setAttempts(0);

            seller1.setCompanyName(sellerModel.getCompanyName());
            seller1.setGst(sellerModel.getGst());
            seller1.setCompanyContact(sellerModel.getContact());
            seller1.setFirstName(sellerModel.getFirstName());
            seller1.setLastName(sellerModel.getLastName());
            seller1.setUsername(sellerModel.getUsername());
            seller1.setEmail(sellerModel.getEmail());

            Address address = new Address();
            Address iterator = sellerModel.getAddresses().iterator().next();

            address.setAddressLine(iterator.getAddressLine());
            address.setCity(iterator.getCity());
            address.setCountry(iterator.getCountry());
            address.setZipCode(iterator.getZipCode());
            address.setState(iterator.getState());
            address.setLabel(iterator.getLabel());

            seller1.addAddress(address);
            seller1.setPassword(passwordEncoder.encode(sellerModel.getPassword()));
            addressRepository.save(address);
            userRepository.save(seller1);
            return "Seller account is been created please wait for approval";
        }
    }

//    @Transactional
//    public String reSendConfirmationToken(String email) {
//        User user = userRepository.findAllByEmailIgnoreCase(email);
//        if (user != null) {
//            ConfirmationToken token = confirmationTokenRepository.findByUser(user);
//            if (token != null) {
//                if (token.getExpiryDate().getTime() - date.getTime() <= 0) {
//                    if(user.getActive() == false){
//                        confirmationTokenRepository.deleteByConfirmationToken(token.getConformationToken());
//                        ConfirmationToken confirmationToken = new ConfirmationToken(user);
//                        confirmationTokenRepository.save(confirmationToken);
//
//                        SimpleMailMessage mail = new SimpleMailMessage();
//                        mail.setTo(user.getEmail());
//                        mail.setSubject("Complete your registration");
//                        mail.setText("Click here to complete registration " +
//                                "localhost:8080/confirm-account?token=" +
//                                confirmationToken.getConformationToken());
//                        mail.setFrom("khandelwal.nitin0001@gmail.com");
//                        emailSendService.sendEmail(mail);
//                        return "Token is successfully resent";
//                    }
//                    return "Customer is already active";
//                }
//                return "Your token is expired";
//            }
//            throw new IdNotFoundException("Token does not exist");
//        }
//        throw new IdNotFoundException("email not found");
//    }

    @Transactional
    public void manageAttempts(String username){
        User user = userRepository.findAllByUsernameIgnoreCase(username);
        if(user!=null) {
            if (user.getAttempts() > 2) {
                if(user.getNonLocked() == true){
                    user.setNonLocked(false);
                    SimpleMailMessage mail = new SimpleMailMessage();
                    mail.setTo(user.getEmail());
                    mail.setSubject("Account locked");
                    mail.setText("Your account is Locked");
                    emailSendService.sendEmail(mail);
                }
            }
            user.setAttempts(user.getAttempts() + 1);
            userRepository.save(user);
        }
    }

//    public String forgotPassword(String email){
//        User user = userRepository.findAllByEmailIgnoreCase(email);
//        if(user != null){
//            if(user.getActive() == true){
//                ForgotPasswordToken forgotPasswordToken = new ForgotPasswordToken(user);
//                SimpleMailMessage mail = new SimpleMailMessage();
//                mail.setTo(user.getEmail());
//                mail.setSubject("Change password request");
//                mail.setText("Click here to reset password " +
//                        "localhost:8080/forgotPassword?token=" +
//                                forgotPasswordToken.getForgotPasswordToken());
//                emailSendService.sendEmail(mail);
//                forgetPasswordTokenRepository.save(forgotPasswordToken);
//                return "Reset password link sent..";
//            }
//            return "This user is not active";
//        }
//        throw new IdNotFoundException("user does not exist");
//    }

//    @Transactional
//    public String setPassword(String forgotPasswordToken, UpdatePasswordModel updatePasswordModel){
//        if(updatePasswordModel.getPassword().equals(updatePasswordModel.getConfirmPassword())){
//            ForgotPasswordToken tokenObj = forgetPasswordTokenRepository.findByForgotPasswordToken(forgotPasswordToken);
//            if(tokenObj != null){
//                if(tokenObj.getExpiryDate().getTime() - date.getTime() >= 0){
//                    User user = userRepository.findAllByEmailIgnoreCase(tokenObj.getUser().getEmail());
//                    user.setPassword(passwordEncoder.encode(updatePasswordModel.getPassword()));
//                    userRepository.save(user);
//                    forgetPasswordTokenRepository.deleteByForgotPasswordToken(forgotPasswordToken);
//                    SimpleMailMessage mail = new SimpleMailMessage();
//                    mail.setTo(user.getEmail());
//                    mail.setSubject("Password reset");
//                    mail.setText("Your Password is updated successfully");
//                    emailSendService.sendEmail(mail);
//                    return "Password is updated successfully";
//                }
//                return "token is expired";
//            }
//            throw new IdNotFoundException("Token does not exist");
//        }
//        return "password and confirm password does not match";
//    }

//    public MappingJacksonValue showAllRegisteredCustomers(String size, String page, String sort){
//        Pageable pageable = PageRequest.of(Integer.parseInt(page),Integer.parseInt(size), Sort.Direction.ASC, sort);
//        List<Customer> customers = (List<Customer>) customerRepo.findAllCustomers(pageable);
//        if(customers != null){
//            SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("firstName", "email", "lastName","active");
//            FilterProvider filters = new SimpleFilterProvider().addFilter("userFilter", filter);
//            MappingJacksonValue mapping = new MappingJacksonValue(customers);
//            mapping.setFilters(filters);
//            return mapping;
//        }else{
//            throw  new IdNotFoundException("No customer found");
//        }
//
//    }

    public MappingJacksonValue showAllRegisteredSellers(String size, String page, String sort){
        Pageable pageable = PageRequest.of(Integer.parseInt(page),Integer.parseInt(size), Sort.Direction.ASC, sort);
        List<Seller> sellers = (List<Seller>) sellerRepository.findAll(pageable);
        if(!sellers.isEmpty()){
            SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("firstName","email","active","lastName");
            FilterProvider filters = new SimpleFilterProvider().addFilter("userFilter", filter);
            MappingJacksonValue mapping = new MappingJacksonValue(sellers);
            mapping.setFilters(filters);
            return mapping;
        }else {
            throw  new IdNotFoundException("No seller found");
        }

    }

    public MappingJacksonValue getProduct(long productId){
        Optional<Product> product = productRepository.findProductById(productId);
        if(product != null){
            SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","brand","createdDate","sellerId","name","category","isActive");
            FilterProvider filters = new SimpleFilterProvider().addFilter("productFilter", filter);
            MappingJacksonValue mapping = new MappingJacksonValue(product.get());
            mapping.setFilters(filters);
            return mapping;
        }else{
            throw  new IdNotFoundException("No product found");
        }

    }
//
    public MappingJacksonValue getAllProduct(long categoryId){
        Optional<List<Product>> product = productRepository.findProductByCategoryId(categoryId);
        if(!product.get().isEmpty()){
            SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","brand","createdDate","sellerId","name","category","isActive");
            FilterProvider filters = new SimpleFilterProvider().addFilter("productFilter", filter);
            MappingJacksonValue mapping = new MappingJacksonValue(product.get());
            mapping.setFilters(filters);
            return mapping;
        }else{
            throw  new IdNotFoundException("No product found");
        }

    }
}

