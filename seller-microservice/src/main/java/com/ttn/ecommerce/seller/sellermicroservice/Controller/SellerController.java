package com.ttn.ecommerce.seller.sellermicroservice.Controller;


import com.ttn.ecommerce.seller.sellermicroservice.Entity.User;
import com.ttn.ecommerce.seller.sellermicroservice.Entity.products.Category;
import com.ttn.ecommerce.seller.sellermicroservice.Models.AddressModel;
import com.ttn.ecommerce.seller.sellermicroservice.Models.UpdatePasswordModel;
import com.ttn.ecommerce.seller.sellermicroservice.Models.UpdateSellerModel;
import com.ttn.ecommerce.seller.sellermicroservice.Repository.UserRepository;
import com.ttn.ecommerce.seller.sellermicroservice.Service.SellerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SellerController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SellerServices sellerServices;

    @GetMapping("/seller/details")
    public MappingJacksonValue showSellerDetails(){
        User seller = sellerServices.getLoggedInSeller();
        return sellerServices.showSellerDetails(seller.getUser_id());
    }

    @PutMapping("/seller/updatePassword")
    public String updateSellerPassword(@RequestBody UpdatePasswordModel updatePasswordModel){
        User seller = sellerServices.getLoggedInSeller();
        return sellerServices.updatePassword(updatePasswordModel, seller.getUser_id());
    }

    @PutMapping("/seller/updateProfile")
    public String updateSellerProfile(@Valid @RequestBody UpdateSellerModel updateSellerModel){
        User seller = sellerServices.getLoggedInSeller();
        return sellerServices.updateSeller(updateSellerModel, seller.getUser_id());
    }

    @PutMapping("/seller/updateAddress")
    public String updateSellerAddress(@RequestBody AddressModel addressModel,
                                      @RequestParam("addressId") int addressId){
        User seller = sellerServices.getLoggedInSeller();
        return sellerServices.updateAddress(addressModel,seller.getUser_id(),addressId);
    }

    @GetMapping("/seller/showAllCategory")
    public List<Category> showAllMetadataFieldValues(){
        return sellerServices.showAllCategory();
    }

    @DeleteMapping("/seller/deleteProduct")
    public String deactivateProduct(@RequestParam("productId") long productId){
        User seller = sellerServices.getLoggedInSeller();
        return sellerServices.deleteProduct(productId, seller.getUser_id());
    }
}
