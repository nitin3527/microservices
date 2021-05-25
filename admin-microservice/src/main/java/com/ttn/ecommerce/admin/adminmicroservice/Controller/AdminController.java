package com.ttn.ecommerce.admin.adminmicroservice.Controller;


import com.ttn.ecommerce.admin.adminmicroservice.Entity.products.Category;
import com.ttn.ecommerce.admin.adminmicroservice.Entity.products.CategoryMetadataField;
import com.ttn.ecommerce.admin.adminmicroservice.Models.AddCategoryModel;
import com.ttn.ecommerce.admin.adminmicroservice.Repository.UserRepository;
import com.ttn.ecommerce.admin.adminmicroservice.Service.AdminServices;
import com.ttn.ecommerce.admin.adminmicroservice.Service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private UserServices userServices;

//    @Autowired
//    private CustomerService customerService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminServices adminServices;

//    @Autowired
//    private SellerServices sellerServices;


    @GetMapping("admin/showAllCustomers")
    public MappingJacksonValue showAllActiveCustomers(@RequestParam(defaultValue = "10") String size,
                                                      @RequestParam(defaultValue = "0") String page,
                                                      @RequestParam(defaultValue = "id") String sort) {
        return userServices.showAllRegisteredCustomers(size, page, sort);
    }

    @GetMapping("admin/showAllSellers")
    public MappingJacksonValue showAllRegisteredSellers(@RequestParam(defaultValue = "10") String size,
                                                        @RequestParam(defaultValue = "0") String page,
                                                        @RequestParam(defaultValue = "id") String sort) {
        return userServices.showAllRegisteredSellers(size, page, sort);
    }

    @PutMapping("admin/activateCustomer/{id}")
    public String activateCustomer(@PathVariable long id) {
        return adminServices.activateCustomer(id);
    }

    @PutMapping("admin/deActivateCustomer/{id}")
    public String deActivateCustomer(@PathVariable long id) {
        return adminServices.deActivateCustomer(id);
    }

    @PutMapping("admin/activateSeller/{id}")
    public String activateSeller(@PathVariable long id) {
        return adminServices.activateSeller(id);
    }

    @PutMapping("admin/deActivateSeller/{id}")
    public String deActivateSeller(@PathVariable long id) {
        return adminServices.deActivateSeller(id);
    }

    @PostMapping("admin/addCategoryMetadataField")
    public String addCategoryMetadataField(@RequestParam("fieldName") String fieldName) {
        return adminServices.addCategoryMetadataField(fieldName);
    }

    @GetMapping("admin/showAllCategoryMetadataFields")
    public List<CategoryMetadataField> showAllMetadataFields(@RequestParam(defaultValue = "10") String size,
                                                             @RequestParam(defaultValue = "0") String page,
                                                             @RequestParam(defaultValue = "id") String sort) {
        return adminServices.showAllMetadataFields(size, page, sort);
    }

    @PostMapping("admin/addCategory")
    public String addCategory(@Valid @RequestBody AddCategoryModel addCategoryModel) {
        return adminServices.addNewCategory(addCategoryModel);
    }

    @GetMapping("admin/showCategory/{id}")
    public List<Category> showCategoryTree(@PathVariable long id) {
        return adminServices.showCategoryTree(id);
    }

    @GetMapping("admin/showAllCategories")
    public List<Category> showAllCategories() {
        return adminServices.showAllCategories();
    }

    @PutMapping("admin/updateCategory")
    public String updateCategory(@RequestParam("categoryName") String categoryName,
                                 @RequestParam("categoryId") long categoryId) {
        return adminServices.updateCategory(categoryId, categoryName);

    }

    @PostMapping("admin/addMetaDataFieldValue")
    public String addMetaDataFieldValue(@RequestParam("categoryId") long categoryId,
                                        @RequestParam("metadataFieldId") long metadataFieldId,
                                        @RequestParam("value") String value) {
        return adminServices.addMetaDataFieldValue(categoryId, metadataFieldId, value);
    }

    @PutMapping("admin/updateMetadataFieldValue")
    public String updateMetadataFieldValue(@RequestParam("categoryId") long categoryId,
                                           @RequestParam("metadataFieldId") long metadataFieldId,
                                           @RequestParam("value") String value) {
        return adminServices.updateMetadataFieldValue(categoryId, metadataFieldId, value);
    }

    @GetMapping("admin/getProduct")
    public MappingJacksonValue getProduct(@RequestParam("productId") long productId) {
        return adminServices.getProduct(productId);
    }

    @GetMapping("admin/getAllProduct")
    public MappingJacksonValue getAllProduct(@RequestParam("categoryId") long categoryId) {
        return adminServices.getAllProduct(categoryId);
    }

    @PutMapping("admin/deactivateProduct")
    public String deactivateProduct(@RequestParam("productId") long productId){
        return adminServices.deactivateProduct(productId);
    }

    @PutMapping("admin/activateProduct")
    public String activateProduct(@RequestParam("productId") long productId){
        return adminServices.activateProduct(productId);
    }
}