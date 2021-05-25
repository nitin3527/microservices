package com.ttn.project.customermicroservice.Service;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.ttn.project.customermicroservice.Entity.Address;
import com.ttn.project.customermicroservice.Entity.Customer;
import com.ttn.project.customermicroservice.Entity.User;
import com.ttn.project.customermicroservice.Exception.IdNotFoundException;
import com.ttn.project.customermicroservice.Model.AddressModel;
import com.ttn.project.customermicroservice.Model.CustomerUpdateModel;
import com.ttn.project.customermicroservice.Model.UpdatePasswordModel;
import com.ttn.project.customermicroservice.Repositry.AddressRepository;
import com.ttn.project.customermicroservice.Repositry.CustomerRepo;
import com.ttn.project.customermicroservice.Repositry.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    EmailSendService emailSendService;

    @Autowired
    CustomerRepo customerRepo;

//    @Autowired
//    CategoryRepository categoryRepository;
//
//    @Autowired
//    ProductRepository productRepository;
//
//    @Autowired
//    ProductVariationRepository productVariationRepository;


    @Transactional
    @Modifying
    public String updateCustomer(CustomerUpdateModel customerUpdateModel, long id)
    {
        Optional<User> user = userRepository.findById(id);
        System.out.println(user);
        if(user.isPresent()){
            Customer customer= (Customer) user.get();

            if(customerUpdateModel.getContact()!=null)
            {
                customer.setContact(customerUpdateModel.getContact());
            }
            if(customerUpdateModel.getFirstName()!=null) {
                customer.setFirstName(customerUpdateModel.getFirstName());
            }
            if(customerUpdateModel.getLastName()!=null) {
                customer.setLastName(customerUpdateModel.getLastName());
            }
            userRepository.save(customer);
            return "Profile Updated Successfully";
        }
        throw new IdNotFoundException("user does not exist");
    }

    public User getLoggedInCustomer(){
        Object principle=SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            return userRepository.findAllByUsernameIgnoreCase(principle.toString());
    }

    public String addAddress(AddressModel addressModel, long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            Address address = new Address();
            address.setAddressLine(addressModel.getAddressLine());
            address.setLabel(addressModel.getLabel());
            address.setState(addressModel.getState());
            address.setCity(addressModel.getCity());
            address.setZipCode(addressModel.getZipCode());
            address.setCountry(addressModel.getCountry());
            User user1= (User) user.get();
            user1.addAddress(address);
            userRepository.save(user1);
            return "Address is successfully saved";
        }
        throw new IdNotFoundException("user does not exist");
    }

    public String updateAddress(AddressModel addressModel, long customerId, int addressId){
        Optional<User> user = userRepository.findById(customerId);
        if(user.isPresent()){
            Optional<Address> address1 = addressRepository.findById(addressId);
            if(address1.isPresent()){
                Address address= address1.get();
                if(addressModel.getAddressLine() != null){
                    address.setAddressLine(addressModel.getAddressLine());
                }
                if(addressModel.getCity() != null){
                    address.setCity(addressModel.getCity());
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
            throw new IdNotFoundException("address does not exist");
        }
        throw new IdNotFoundException("user does not exist");
    }



    @Transactional
    public String deleteAddress(long customerId, int addressId){
        Optional<User> user = userRepository.findById(customerId);
        if(user.isPresent()){
            Optional<Address> address1 = addressRepository.findById(addressId);
            if(address1.isPresent()){
                addressRepository.deleteAddressId(addressId, customerId);
                return "address is successfully deleted";
            }
            return "address does not exist";
        }else {
            throw new IdNotFoundException("user does not exist");
        }
    }

    @Transactional
    public String updatePassword(UpdatePasswordModel updatePasswordModel, long customerId){
        Optional<User> user = userRepository.findById(customerId);
        if(user.isPresent()){
            User customer = user.get();
            String password = updatePasswordModel.getPassword();
            String confirmPassword = updatePasswordModel.getConfirmPassword();

            if(password.equals(confirmPassword)){
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                customer.setPassword(passwordEncoder.encode(password));
                userRepository.save(customer);
                emailSendService.sendEmail(customer.getEmail(),"password updated","Password is successfully updated");
                return "Password is successfully updated";
            }
            return "Password and confirmPassword does not match";
        }
        throw new IdNotFoundException("User does not exist");
    }

    public MappingJacksonValue showCustomerDetails(long customerId){
        Optional<User> user = userRepository.findById(customerId);
        if(user.isPresent()){
            Customer customer = new Customer();
            customer = (Customer) user.get();
            SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("firstName","email","active","lastName");
            FilterProvider filters = new SimpleFilterProvider().addFilter("userFilter", filter);
            MappingJacksonValue mapping = new MappingJacksonValue(customer);
            mapping.setFilters(filters);
            return mapping;
        }else {
            throw new IdNotFoundException("user not found");
        }
    }

    public MappingJacksonValue showAllAddress(long user_id){
        List<Address> addresses =  customerRepo.showAllAddresses(user_id);
        if(!addresses.isEmpty()){
            SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("city","state","country","addressLine","zipCode","label");
            FilterProvider filters = new SimpleFilterProvider().addFilter("addressFilter", filter);
            MappingJacksonValue mapping = new MappingJacksonValue(addresses);
            mapping.setFilters(filters);
            return mapping;
        }else {
            throw new IdNotFoundException("Address not found");
        }
    }

//    public List<Category> showAllCategories(){
//        return (List<Category>) categoryRepository.findAll();
//    }

//    public MappingJacksonValue showAllRelatedProducts(long productId){
//        Optional<List<ProductVariation>> productVariations = productVariationRepository.findRelatedProductsForCustomer(productId);
//        if(!productVariations.isEmpty()){
//            SimpleBeanPropertyFilter productFilter = SimpleBeanPropertyFilter.filterOutAllExcept("id","brand","createdDate","sellerId","name","category");
//            SimpleBeanPropertyFilter productVariationFilter = SimpleBeanPropertyFilter.filterOutAllExcept("id","quantity","price","productsMetadata","isActive","createdDate","product");
//            FilterProvider filters = new SimpleFilterProvider()
//                    .addFilter("productVariationFilter", productVariationFilter)
//                    .addFilter("productFilter", productFilter);
//            MappingJacksonValue mapping = new MappingJacksonValue(productVariations);
//            mapping.setFilters(filters);
//            return mapping;
//        }else{
//            throw new IdNotFoundException("product Variation not found");
//        }
//    }
//
//    public MappingJacksonValue getProduct(long productId){
//        Optional<Product> product = productRepository.findProductForCustomer(productId);
//        if(product.isPresent()){
//            SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","brand","createdDate","sellerId","name","category");
//            FilterProvider filters = new SimpleFilterProvider().addFilter("productFilter", filter);
//            MappingJacksonValue mapping = new MappingJacksonValue(product.get());
//            mapping.setFilters(filters);
//            return mapping;
//        }else {
//            throw new IdNotFoundException("product not found");
//        }
//    }
//
//    public MappingJacksonValue getAllProduct(long categoryId){
//        Optional<List<Product>> product = productRepository.findAllProductForCustomer(categoryId);
//        if(!product.get().isEmpty()){
//            SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","brand","createdDate","sellerId","name","category");
//            FilterProvider filters = new SimpleFilterProvider().addFilter("productFilter", filter);
//            MappingJacksonValue mapping = new MappingJacksonValue(product.get());
//            mapping.setFilters(filters);
//            return mapping;
//        }else{
//            throw new IdNotFoundException("No products found");
//        }
//    }
//
//    public MappingJacksonValue filterAllProducts(long categoryId){
//        Optional<List<ProductVariation>> products = productVariationRepository.findFilteredProductForCustomer(categoryId);
//        if(!products.get().isEmpty()){
//            SimpleBeanPropertyFilter productFilter = SimpleBeanPropertyFilter.filterOutAllExcept("id","brand","createdDate","sellerId","name","category");
//            SimpleBeanPropertyFilter productVariationFilter = SimpleBeanPropertyFilter.filterOutAllExcept("id","quantity","price","productsMetadata","isActive","createdDate","product");
//            FilterProvider filters = new SimpleFilterProvider()
//                    .addFilter("productVariationFilter", productVariationFilter)
//                    .addFilter("productFilter", productFilter);
//            MappingJacksonValue mapping = new MappingJacksonValue(products);
//            mapping.setFilters(filters);
//            return mapping;
//        }else {
//            throw new IdNotFoundException("No products found");
//        }
//    }
}
