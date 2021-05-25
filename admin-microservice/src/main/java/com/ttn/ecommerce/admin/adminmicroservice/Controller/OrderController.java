package com.ttn.ecommerce.admin.adminmicroservice.Controller;


import com.ttn.ecommerce.admin.adminmicroservice.Models.OrderStatusModel;
import com.ttn.ecommerce.admin.adminmicroservice.Service.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
//    @Autowired
//    CustomerService customerService;

    @Autowired
    OrderServices orderServices;

//    @Autowired
//    SellerServices sellerServices;

//    @PostMapping("/customer/placeOrder")
//    private String placeOrder(){
//        User customer = customerService.getLoggedInCustomer();
//        return orderServices.placeOrder(customer.getUser_id());
//    }

//    @PostMapping("/customer/placePartialProducts")
//    private String placePartialProducts(@RequestBody String productVariationIds){
//        User customer = customerService.getLoggedInCustomer();
//        return orderServices.placePartialProducts(customer.getUser_id(), productVariationIds);
//    }
//
//    @PostMapping("/customer/directlyOrderProduct")
//    private String directlyOrderProduct(@RequestParam("productVariationId") long productVariationId,
//                                        @RequestParam("quantity") int quantity){
//        User customer = customerService.getLoggedInCustomer();
//        return orderServices.directlyOrderProduct(productVariationId, quantity, customer.getUser_id());
//    }
//
//    @PatchMapping("customer/cancelOrder")
//    private String cancelOrder(@RequestParam("orderId") long orderId){
//        User customer = customerService.getLoggedInCustomer();
//        return orderServices.cancelOrder(orderId, customer.getUser_id());
//    }
//
//    @PatchMapping("customer/returnOrder")
//    private String returnOrder(@RequestParam("orderId") long orderId){
//        User customer = customerService.getLoggedInCustomer();
//        return orderServices.returnOrder(orderId, customer.getUser_id());
//    }
//
//    @GetMapping("customer/showOrder")
//    private MappingJacksonValue showOrder(@RequestParam("orderId") long orderId){
//        User customer = customerService.getLoggedInCustomer();
//        return orderServices.showOrder(orderId, customer.getUser_id());
//    }
//
//    @GetMapping("customer/showAllOrders")
//    private MappingJacksonValue showAllOrders(){
//        User customer = customerService.getLoggedInCustomer();
//        return orderServices.showAllOrder(customer.getUser_id());
//    }
//    @GetMapping("seller/getAllSellerOrders")
//    private MappingJacksonValue getAllSellerOrders(){
//        User seller = sellerServices.getLoggedInSeller();
//        return orderServices.getAllSellerOrders(seller.getUser_id());
//    }
//
//    @PutMapping("seller/changeOrderStatus")
//    private String changeOrderStatus(@RequestBody OrderStatusModel orderStatusModel){
//        User seller = sellerServices.getLoggedInSeller();
//        return orderServices.changeOrderStatus(orderStatusModel, seller.getUser_id());
//    }

    @GetMapping("admin/getAllOrders")
    private MappingJacksonValue getAllOrders(){
        return orderServices.getAllOrders();
    }

    @PutMapping("admin/adminChangeStatusOfOrder")
    private String adminChangeStatusOfOrder(@RequestBody OrderStatusModel orderStatusModel){
        return orderServices.adminChangeStatusOfOrder(orderStatusModel);
    }
}
