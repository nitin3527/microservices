package com.ttn.ecommerce.seller.sellermicroservice.Service;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.ttn.ecommerce.seller.sellermicroservice.Entity.Address;
import com.ttn.ecommerce.seller.sellermicroservice.Entity.Seller;
import com.ttn.ecommerce.seller.sellermicroservice.Entity.User;
import com.ttn.ecommerce.seller.sellermicroservice.Entity.products.Category;
import com.ttn.ecommerce.seller.sellermicroservice.Entity.products.CategoryMetadataFieldValue;
import com.ttn.ecommerce.seller.sellermicroservice.Entity.products.Product;
import com.ttn.ecommerce.seller.sellermicroservice.Models.AddressModel;
import com.ttn.ecommerce.seller.sellermicroservice.Models.UpdatePasswordModel;
import com.ttn.ecommerce.seller.sellermicroservice.Models.UpdateSellerModel;
import com.ttn.ecommerce.seller.sellermicroservice.Repository.*;
import com.ttn.ecommerce.seller.sellermicroservice.exceptions.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SellerServices {
    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailSendService emailSendService;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CategoryMetadataFieldValueRepository categoryMetadataFieldValueRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public MappingJacksonValue showSellerDetails(long sellerId){
        Optional<User> user = userRepository.findById(sellerId);
        if(user.isPresent()){
            Seller seller = new Seller();
            seller = (Seller) user.get();
            SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("firstName","email","active","lastName","addresses");
            FilterProvider filters = new SimpleFilterProvider().addFilter("userFilter", filter);
            MappingJacksonValue mapping = new MappingJacksonValue(seller);
            mapping.setFilters(filters);
            return mapping;
        }
        throw  new IdNotFoundException("user not found");
    }

    public MappingJacksonValue showAddressDetails(long sellerId){
        Optional<User> user = userRepository.findById(sellerId);
        if(user.isPresent()){
            Seller seller = new Seller();
            seller = (Seller) user.get();
            SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("firstName","email","active","lastName");
            FilterProvider filters = new SimpleFilterProvider().addFilter("userFilter", filter);
            MappingJacksonValue mapping = new MappingJacksonValue(seller);
            mapping.setFilters(filters);
            return mapping;
        }
        throw  new IdNotFoundException("user not found");
    }


    public User getLoggedInSeller(){
        Object principle=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findAllByUsernameIgnoreCase(principle.toString());
    }

    @Transactional
    public String updatePassword(UpdatePasswordModel updatePasswordModel, long sellerId){
        Optional<User> user = userRepository.findById(sellerId);
        if(user.isPresent()){
            User seller = user.get();
            String password = updatePasswordModel.getPassword();
            String confirmPassword = updatePasswordModel.getConfirmPassword();

            if(password.equals(confirmPassword)){
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                seller.setPassword(passwordEncoder.encode(password));
                userRepository.save(seller);
                emailSendService.sendEmail(seller.getEmail(),"password updated","Password is successfully updated");
                return "Password is successfully updated";
            }
            return "Password and confirmPassword does not match";
        }
        throw new IdNotFoundException("User does not exist");
    }

    @Transactional
    public String updateSeller(UpdateSellerModel updateSellerModel, long id)
    {
        Optional<User> user = userRepository.findById(id);
        System.out.println(user);
        if(user.isPresent()){
            Seller seller = (Seller) user.get();

            if(updateSellerModel.getCompanyContact()!=null)
            {
                seller.setCompanyContact(updateSellerModel.getCompanyContact());
            }

            if(updateSellerModel.getFirstName()!=null) {
                seller.setFirstName(updateSellerModel.getFirstName());
            }

            if(updateSellerModel.getLastName()!=null) {
                seller.setLastName(updateSellerModel.getLastName());
            }
            if(updateSellerModel.getCompanyName()!=null){
                seller.setCompanyName(updateSellerModel.getCompanyName());
            }
            if(updateSellerModel.getGst() != null){
                seller.setGst(updateSellerModel.getGst());
            }
            userRepository.save(seller);
            return "Profile Updated!";
        }
        throw  new IdNotFoundException("seller not found");
    }

    @Transactional
    public String updateAddress(AddressModel addressModel, long sellerId, int addressId){
        Optional<User> user = userRepository.findById(sellerId);
        if(user.isPresent()){
            Optional<Address> address1 = addressRepository.findById(addressId);
            if(address1.isPresent()){
                Address address= address1.get();
                if(addressModel.getAddressLine() != null){
                    address.setAddressLine(addressModel.getAddressLine());
                }
                if(addressModel.getCity() != null){
                    address.setState(addressModel.getState());
                }
                if(addressModel.getCountry() != null){
                    address.setCountry(addressModel.getCountry());
                }
                if(addressModel.getZipCode() != null){
                    address.setZipCode(addressModel.getZipCode());
                }
                if(addressModel.getState() != null){
                    address.setState(addressModel.getState());
                }
                if(addressModel.getLabel() != null){
                    address.setLabel(addressModel.getLabel());
                }
                addressRepository.save(address);
                return "Address updated successfully";
            }
            throw  new IdNotFoundException("address not found");
        }
        throw  new IdNotFoundException("user not found");
    }

    public List<CategoryMetadataFieldValue> showAllMetadataFieldValues(){
        return (List<CategoryMetadataFieldValue>) categoryMetadataFieldValueRepository.findAll();
    }

    public String deleteProduct(long productId, long sellerId){
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()){
            if(product.get().getSeller().getUser_id() == sellerId){
                product.get().setDeleted(true);
                productRepository.save(product.get());
                return "Product deleted";
            }
            return "Unauthorized user";
        }
        throw  new IdNotFoundException("product not found");
    }

    public List<Category> showAllCategory(){
        return (List<Category>) categoryRepository.findAll();
    }

}
