package com.ttn.ecommerce.admin.adminmicroservice.Service;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.ttn.ecommerce.admin.adminmicroservice.Entity.ConfirmationToken;
import com.ttn.ecommerce.admin.adminmicroservice.Entity.Role;
import com.ttn.ecommerce.admin.adminmicroservice.Entity.User;
import com.ttn.ecommerce.admin.adminmicroservice.Entity.products.Category;
import com.ttn.ecommerce.admin.adminmicroservice.Entity.products.CategoryMetadataField;
import com.ttn.ecommerce.admin.adminmicroservice.Entity.products.CategoryMetadataFieldValue;
import com.ttn.ecommerce.admin.adminmicroservice.Entity.products.Product;
import com.ttn.ecommerce.admin.adminmicroservice.Models.AddCategoryModel;
import com.ttn.ecommerce.admin.adminmicroservice.Repository.*;
import com.ttn.ecommerce.admin.adminmicroservice.exceptions.IdNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminServices {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    EmailSendService emailSendService;

    @Autowired
    CategoryMetadataFieldRepository categoryMetadataFieldRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMetadataFieldValueRepository categoryMetadataFieldValueRepository;

    @Autowired
    ProductRepository productRepository;

    public String activateCustomer(long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            User user1 = user.get();
            Role role = user.get().getRole();
            String userRole = role.getAuthority();
            if(userRole.equals("ROLE_CUSTOMER")){
                if(user1.getActive() == false ){
                    user1.setActive(true);
                    userRepository.save(user1);
                    ConfirmationToken confirmationToken = new ConfirmationToken(user1);
                    confirmationTokenRepository.save(confirmationToken);

                    SimpleMailMessage mail = new SimpleMailMessage();
                    mail.setTo(user1.getEmail());
                    mail.setSubject("user deactivation");
                    mail.setText("Your account had been activated");
                    mail.setFrom("khandelwal.nitin0001@gmail.com");
                    emailSendService.sendEmail(mail);
                    return "Customer is activated successfully";
                }else {
                    return "Customer is already activated";
                }
            }else {
                return "This user is not a customer";
            }
        }
        throw new IdNotFoundException("Customer not found");
    }

    public String deActivateCustomer(long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            User user1 = user.get();
            Role role = user.get().getRole();
            String userRole = role.getAuthority();
            if(userRole.equals("ROLE_CUSTOMER")){
                if(user1.getActive() == true ){
                    user1.setActive(false);
                    userRepository.save(user1);
                    ConfirmationToken confirmationToken = new ConfirmationToken(user1);
                    confirmationTokenRepository.save(confirmationToken);

                    SimpleMailMessage mail = new SimpleMailMessage();
                    mail.setTo(user1.getEmail());
                    mail.setSubject("user deactivation");
                    mail.setText("Your account had been deactivated");
                    mail.setFrom("khandelwal.nitin0001@gmail.com");
                    emailSendService.sendEmail(mail);
                    return "Customer is deactivated successfully";
                }else {
                    return "Customer is already deactivated";
                }
            }else {
                return "This user is not a customer";
            }
        }
        throw new IdNotFoundException("Customer not found");
    }

    public String activateSeller(long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            User user1 = user.get();
            Role role = user.get().getRole();
            String userRole = role.getAuthority();
            if(userRole.equals("ROLE_SELLER")){
                if(user1.getActive() == false ){
                    user1.setActive(true);
                    userRepository.save(user1);
                    ConfirmationToken confirmationToken = new ConfirmationToken(user1);
                    confirmationTokenRepository.save(confirmationToken);

                    SimpleMailMessage mail = new SimpleMailMessage();
                    mail.setTo(user1.getEmail());
                    mail.setSubject("activate Seller");
                    mail.setText("Your account had been activated");
                    mail.setFrom("khandelwal.nitin0001@gmail.com");
                    emailSendService.sendEmail(mail);
                    return "Seller is activated successfully";
                }else {
                    return "Seller is already activated";
                }
            }else {
                return "This user is not a Seller";
            }
        }
        throw new IdNotFoundException("Seller not found");
    }

    public String deActivateSeller(long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            User user1 = user.get();
            Role role = user.get().getRole();
            String userRole = role.getAuthority();
            if(userRole.equals("ROLE_SELLER")){
                if(user1.getActive() == true ){
                    user1.setActive(false);
                    userRepository.save(user1);
                    ConfirmationToken confirmationToken = new ConfirmationToken(user1);
                    confirmationTokenRepository.save(confirmationToken);
                    emailSendService.sendEmail(user1.getEmail(),"user deactivation","Your account had been deactivated" );
                    return "Seller is deactivated successfully";
                }else {
                    return "Seller is already deactivated";
                }
            }else {
                return "This user is not a seller";
            }
        }
        throw new IdNotFoundException("Seller not found");
    }

    public String addCategoryMetadataField(String fieldName){
        if(fieldName.isEmpty()){
            return "null fieldName cannot be passed";
        }
        String name = categoryMetadataFieldRepository.findByNameIgnoreCase(fieldName);
        if(name != null){
            return "throw exception field already exist";
        }else{
            CategoryMetadataField categoryMetadataField = new CategoryMetadataField();
            categoryMetadataField.setName(fieldName);
            categoryMetadataFieldRepository.save(categoryMetadataField);
            return "field saved Successfully";
        }
    }
    public List<CategoryMetadataField> showAllMetadataFields(String size, String page, String sort){
        Pageable pageable = PageRequest.of(Integer.parseInt(page),Integer.parseInt(size), Sort.Direction.ASC, sort);
        return (List<CategoryMetadataField>) categoryMetadataFieldRepository.findAllMetadataField(pageable);
    }
    public String addNewCategory(AddCategoryModel addCategoryModel){
        Optional<Category> category = Optional.ofNullable(categoryRepository.findByNameIgnoreCase(addCategoryModel.getName()));
        if(category.isPresent()){
            return "category already exist";
        }else {
            ModelMapper modelMapper = new ModelMapper();
            Category category1 = modelMapper.map(addCategoryModel, Category.class);
            category1.setName(addCategoryModel.getName());
            Category parentCategory = categoryRepository.findByNameIgnoreCase(addCategoryModel.getParentCategory().getName());
            if(parentCategory != null){
                category1.setParentCategory(parentCategory);
            }else{
                category1.setParentCategory(addCategoryModel.getParentCategory());
            }

            categoryRepository.save(category1);
            return "category successfully saved";
        }
    }

    public List<Category> showCategoryTree(long categoryId){
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isPresent()){
            Category category1 = category.get();
            long parentId = category1.getParentCategory().getCategory_id();
            return categoryRepository.findParentCategoryOfChild(parentId);
        }
        throw new IdNotFoundException("Category not found");
    }

    public List<Category> showAllCategories(){
        Pageable pageable = PageRequest.of(0,10,Sort.Direction.ASC,"category_id");
        return (List<Category>) categoryRepository.findAllCategory(pageable);
    }

    public String updateCategory(long id, String name){
       Optional<Category> category = categoryRepository.findById(id);
       if(category.isPresent()){
           Category category1 = category.get();
           category1.setName(name);
           categoryRepository.save(category1);
           return "Category is successfully updated";
       }
        throw new IdNotFoundException("Category not found");
    }

    public String addMetaDataFieldValue(long categoryId, long categoryMetadataFieldId, String value){
        Optional<Category> category = categoryRepository.findById(categoryId);
        Optional<CategoryMetadataField> categoryMetadataField = categoryMetadataFieldRepository.findById(categoryMetadataFieldId);
        if(category.isPresent() && categoryMetadataField.isPresent()){
            CategoryMetadataFieldValue metadataFieldValue = new CategoryMetadataFieldValue();
            metadataFieldValue.setCategory(category.get());
            metadataFieldValue.setCategoryMetadataField(categoryMetadataField.get());

            List<String> listValues= Arrays.asList(value.split(",",-1));
            Set<String> setValues= new LinkedHashSet<>(listValues);
            String distinctValue= String.join(",",setValues);

            metadataFieldValue.setFieldValue(distinctValue);
            categoryMetadataFieldValueRepository.save(metadataFieldValue);
            return "value for field is added";
        }
        return "invalid category or metadataField";
    }

    public String updateMetadataFieldValue(long categoryId, long categoryMetadataFieldId, String value){
        CategoryMetadataFieldValue categoryMetadataFieldValue = categoryMetadataFieldValueRepository.getFieldValueObj(categoryId, categoryMetadataFieldId);
        if(categoryMetadataFieldValue != null){
            String newValue = categoryMetadataFieldValue.getFieldValue() + "," + value;
            List<String> listValues= Arrays.asList(newValue.split(",",-1));
            Set<String> setValues= new LinkedHashSet<>(listValues);
            String distinctValue= String.join(",",setValues);

            categoryMetadataFieldValue.setFieldValue(distinctValue);
            categoryMetadataFieldValueRepository.save(categoryMetadataFieldValue);
            return "Value updated successfully";
        }
        return "invalid inputs";
    }
    public MappingJacksonValue getProduct(long productId){
        Optional<Product> product = productRepository.findProductById(productId);
        if(product.isPresent()){
            SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","brand","createdDate","sellerId","name","category","isActive");
            FilterProvider filters = new SimpleFilterProvider().addFilter("productFilter", filter);
            MappingJacksonValue mapping = new MappingJacksonValue(product.get());
            mapping.setFilters(filters);
            return mapping;
        }else {
            throw new IdNotFoundException("Product not found");
        }

    }

    public MappingJacksonValue getAllProduct(long categoryId){
        Optional<List<Product>> product = productRepository.findProductByCategoryId(categoryId);
        if(!product.isEmpty()){
            SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","brand","createdDate","sellerId","name","category","isActive");
            FilterProvider filters = new SimpleFilterProvider().addFilter("productFilter", filter);
            MappingJacksonValue mapping = new MappingJacksonValue(product.get());
            mapping.setFilters(filters);
            return mapping;
        }else {
            throw new IdNotFoundException("Product not found");
        }

    }

    public String deactivateProduct(long productId){
        Optional<Product> product = productRepository.findById(productId);
        String sellerEmail = product.get().getSeller().getEmail();
        if(product.isPresent()){
            if(product.get().getDeleted() == false){
                if(product.get().getIsActive() == true){
                    product.get().setIsActive(false);
                    productRepository.save(product.get());
                    emailSendService.sendEmail(sellerEmail,"product deactivation","Your product had been deactivated");
                    return "product deactivated";
                }
                return "product already deactivated";
            }
            return "This is deleted product";
        }
        throw new IdNotFoundException("Product not found");
    }

    public String activateProduct(long productId){
        Optional<Product> product = productRepository.findById(productId);
        String sellerEmail = product.get().getSeller().getEmail();
        if(product.isPresent()){
            if(product.get().getDeleted() == false){
                if(product.get().getIsActive() == false){
                    product.get().setIsActive(true);
                    productRepository.save(product.get());
                    emailSendService.sendEmail(sellerEmail,"product activation","Your product had been activated");
                    return "product activated";
                }
                return "product already activated";
            }
            return "This is deleted product";
        }
        throw new IdNotFoundException("Product not found");
    }
}
