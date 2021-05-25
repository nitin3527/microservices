package com.ttn.project.customermicroservice.Controller;


import com.ttn.project.customermicroservice.Entity.Address;
import com.ttn.project.customermicroservice.Entity.User;
import com.ttn.project.customermicroservice.Model.AddressModel;
import com.ttn.project.customermicroservice.Model.CustomerUpdateModel;
import com.ttn.project.customermicroservice.Model.UpdatePasswordModel;
import com.ttn.project.customermicroservice.Repositry.UserRepository;
import com.ttn.project.customermicroservice.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserRepository userRepository;

    @PutMapping("/customer/updateCustomer")
    public String updateCustomer(@Valid @RequestBody CustomerUpdateModel customerUpdateModel){
        User customer = customerService.getLoggedInCustomer();
        return customerService.updateCustomer(customerUpdateModel, customer.getUser_id());
    }
    @PostMapping("/customer/addCustomerAddress")
    public String addCustomerAddress(@RequestBody AddressModel addressModel){
        User customer = customerService.getLoggedInCustomer();
        return customerService.addAddress(addressModel, customer.getUser_id());
    }

    @GetMapping("/customer/showAllCustomerAddresses")
    public List<Address> showAllCustomerAddresses(){
        User customer = customerService.getLoggedInCustomer();
        return userRepository.showAllAddresses(customer.getUser_id());
    }

    @PutMapping("/customer/updateCustomerAddress")
    public String updateAddress(@RequestBody AddressModel addressModel,
                                @RequestParam("addressId") int addressId){
        User customer = customerService.getLoggedInCustomer();
        return customerService.updateAddress(addressModel,customer.getUser_id(),addressId);

    }

    @DeleteMapping("/customer/deleteAddress")
    public String deleteAddress(@RequestParam("addressId") int addressId){
        User customer = customerService.getLoggedInCustomer();
        return customerService.deleteAddress(customer.getUser_id(),addressId);
    }

    @PutMapping("/customer/updateCustomerPassword")
    public String updatePassword(@Valid @RequestBody UpdatePasswordModel updatePasswordModel){
        User customer = customerService.getLoggedInCustomer();
        return customerService.updatePassword(updatePasswordModel, customer.getUser_id());
    }

    @GetMapping("/customer/customerDetails")
    public MappingJacksonValue showCustomerDetails(){
        User customer = customerService.getLoggedInCustomer();
        return customerService.showCustomerDetails(customer.getUser_id());
    }
//    @GetMapping("/customer/showAllCategory")
//    public List<Category> showAllCategory(){
//        return customerService.showAllCategories();
//    }
//
//    @GetMapping("/customer/filterAllProducts")
//    public MappingJacksonValue filterAllProducts(@RequestParam("categoryId") long categoryId){
//        return customerService.filterAllProducts(categoryId);
//    }
}
