package com.ttn.ecommerce.admin.adminmicroservice.Service;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.ttn.ecommerce.admin.adminmicroservice.Entity.orders.OrderProduct;
import com.ttn.ecommerce.admin.adminmicroservice.Entity.orders.OrderStatus;
import com.ttn.ecommerce.admin.adminmicroservice.Entity.orders.OrderTable;
import com.ttn.ecommerce.admin.adminmicroservice.Models.OrderStatusModel;
import com.ttn.ecommerce.admin.adminmicroservice.Repository.OrderProductRepository;
import com.ttn.ecommerce.admin.adminmicroservice.Repository.OrderStatusRepository;
import com.ttn.ecommerce.admin.adminmicroservice.Repository.OrderTableRepository;
import com.ttn.ecommerce.admin.adminmicroservice.Repository.ProductRepository;
import com.ttn.ecommerce.admin.adminmicroservice.exceptions.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.*;

@Service
public class OrderServices {
//    @Autowired
//    CartRepository cartRepository;
//
//    @Autowired
//    CustomerRepo customerRepo;

    @Autowired
    OrderTableRepository orderTableRepository;

    @Autowired
    OrderProductRepository orderProductRepository;

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Autowired
    ProductRepository productRepository;

//    @Autowired
//    ProductVariationRepository productVariationRepository;

//    public String placeOrder(long customerId){
//        Optional<List<Cart>> cartList=cartRepository.findByCustomerId(customerId);
//        if(cartList.isPresent()&&!cartList.get().isEmpty())
//        {
//            System.out.println("inside first if");
//            Cart cart=cartList.get().iterator().next();
//            System.out.println(cart+"Cart extracted");
//            OrderTable order=new OrderTable();
//            System.out.println("order object created");
//            Address customerAddress= cart.getCustomer().getAddresses().iterator().next();
//            System.out.println("customer address extracted");
//            if(!cart.getCustomer().getAddresses().isEmpty())
//            {
//                order.setCustomer(cart.getCustomer());
//                order.setCreatedDate(new Date());
//                order.setPaymentMethod("Online");
//                order.setCustomerAddressCity(customerAddress.getCity());
//                order.setCustomerAddressState(customerAddress.getState());
//                order.setCustomerAddressCountry(customerAddress.getCountry());
//                order.setCustomerAddressLine(customerAddress.getAddressLine());
//                order.setCustomerAddressZipcode(customerAddress.getZipCode());
//                order.setCustomerAddressLabel(customerAddress.getLabel());
//                System.out.println("customer address set");
//                double price=0;
//                int quantity=0;
//                double amount_paid=0;
//                for(Cart cartOrder:cartList.get())
//                {
//                    quantity=cartOrder.getQuantity();
//                    System.out.println(quantity);
//                    price=cartOrder.getProductVariation().getPrice();
//                    System.out.println(price);
//                    amount_paid=amount_paid+(price*quantity);
//                    System.out.println(amount_paid);
//                }
//
//                order.setAmountPaid(amount_paid);
//                orderTableRepository.save(order);
//                System.out.println(" " +" order saved");
//
//                for(Cart cartOrder:cartList.get())
//                {
//                    OrderProduct product= new OrderProduct();
//                    product.setOrder(order);
//                    product.setProductVariation(cartOrder.getProductVariation());
//                    product.setPrice(cartOrder.getProductVariation().getPrice());
//                    product.setQuantity(cartOrder.getQuantity());
//                    orderProductRepository.save(product);
//                    System.out.println(" " +" order product saved");
//
//                    OrderStatus orderStatus= new OrderStatus();
//                    orderStatus.setOrderProduct(product);
//                    orderStatus.setFromStatus(FromStatus.ORDER_PLACED);
//                    orderStatus.setToStatus(ToStatus.ORDER_CONFIRMED);
//                    orderStatus.setTransitionNoteComment("Order Placed");
//                    orderStatusRepository.save(orderStatus);
//                    System.out.println(" " +" order status saved");
//                    cartRepository.deleteById(cartOrder.getId());
//                }
//                return "Order Placed with Order id: "+order.getOrder_id();
//            }
//            else
//            {
//                return "Add address before placing your order";
//            }
//        }
//        else {
//            throw new IdNotFoundException("product not found in cart");
//        }
//    }

//    public String placePartialProducts(long customerId,String product_variation_ids) throws ValidationException {
//        Optional<List<Cart>> cartList = cartRepository.findByCustomerId(customerId);
//        if (product_variation_ids.isEmpty())
//            throw new ValidationException("product variation id's cannot be empty");
//        else {
//            if (cartList.isPresent() && !cartList.get().isEmpty()) {
//                List<String> listValues = Arrays.asList(product_variation_ids.split(",", -1));
//                List<Long> newListValues = new ArrayList<>();
//                for (String s : listValues)
//                    newListValues.add(Long.valueOf(s));
//                List<Long> dbKeyList = new ArrayList<>();
//                cartList.get().forEach(e -> dbKeyList.add(e.getProductVariation().getId()));
//                if (dbKeyList.containsAll(newListValues)) {
//                    for (Long id : newListValues) {
//                        Optional<Cart> cart = Optional.ofNullable(cartRepository.findByProductVariationId(id));
//                        if (cart.isPresent())
//                            continue;
//                        else
//                            throw new ValidationException("Provided product variation id is not valid");
//                    }
//
//
//                    Cart cart = cartList.get().iterator().next();
//                    OrderTable order = new OrderTable();
//                    Address customerAddress = cart.getCustomer().getAddresses().iterator().next();
//                    if (!cart.getCustomer().getAddresses().isEmpty()) {
//                        order.setCustomer(cart.getCustomer());
//                        order.setCreatedDate(new Date());
//                        order.setPaymentMethod("Online");
//                        order.setCustomerAddressCity(customerAddress.getCity());
//                        order.setCustomerAddressState(customerAddress.getState());
//                        order.setCustomerAddressCountry(customerAddress.getCountry());
//                        order.setCustomerAddressLine(customerAddress.getAddressLine());
//                        order.setCustomerAddressZipcode(customerAddress.getZipCode());
//                        order.setCustomerAddressLabel(customerAddress.getLabel());
//                        double price = 0;
//                        int quantity = 0;
//                        double amount_paid = 0;
//                        for (Long id : newListValues) {
//                            Optional<Cart> cartOptional = Optional.ofNullable(cartRepository.findByProductVariationId(id));
//                            if (cartOptional.isPresent()) {
//                                Cart cartOrder = cartOptional.get();
//                                quantity = cartOrder.getQuantity();
//                                System.out.println(quantity);
//                                price = cartOrder.getProductVariation().getPrice();
//                                System.out.println(price);
//                                amount_paid = amount_paid + (price * quantity);
//                                System.out.println(amount_paid);
//                            } else {
//                                throw new ValidationException("Invalid Product Variation Id");
//                            }
//                        }
//
//                        order.setAmountPaid(amount_paid);
//                        orderTableRepository.save(order);
//                        System.out.println(" " + " order saved");
//
//                        for (Long id : newListValues) {
//                            Optional<Cart> cartOptional = Optional.ofNullable(cartRepository.findByProductVariationId(id));
//                            if (cartOptional.isPresent()) {
//                                Cart cartOrder = cartOptional.get();
//                                OrderProduct product = new OrderProduct();
//                                product.setOrder(order);
//                                product.setProductVariation(cartOrder.getProductVariation());
//                                product.setPrice(cartOrder.getProductVariation().getPrice());
//                                product.setQuantity(cartOrder.getQuantity());
//                                orderProductRepository.save(product);
//                                System.out.println(" " + " order product saved");
//
//                                OrderStatus orderStatus = new OrderStatus();
//                                orderStatus.setOrderProduct(product);
//                                orderStatus.setFromStatus(FromStatus.ORDER_PLACED);
//                                orderStatus.setToStatus(ToStatus.ORDER_CONFIRMED);
//                                orderStatus.setTransitionNoteComment("Order Placed");
//                                orderStatusRepository.save(orderStatus);
//                                System.out.println(" " + " order status saved");
//                                cartRepository.deleteById(cartOrder.getId());
//                                System.out.println(" " + " cart id deleted");
//                            }
//                            else
//                                throw new ValidationException("Invalid Product Variation Id");
//                        }
//                        return "Order PLaced SuccessFully with Order id: " + order.getOrder_id();
//                    } else
//                        throw new ValidationException("Add Customer address first to place your order");
//                } else
//                    throw new ValidationException("Provided Variation Id is not available in cart");
//            } else
//                throw new ValidationException("Cart is Empty");
//        }
//    }
//
//
//    public String directlyOrderProduct(long productVariationId, int quantity, long customerId){
//        Optional<OrderProduct> productVariation = orderProductRepository.findById(productVariationId);
//        Customer customer = customerRepo.findById(customerId);
//        if(customer!= null){
//            if(productVariation.isPresent() && productVariation.get().getQuantity() >= quantity){
//                Address customerAddress = customer.getAddresses().iterator().next();
//
//                OrderTable orderTable = new OrderTable();
//                orderTable.setAmountPaid(productVariation.get().getPrice() * quantity);
//                orderTable.setCustomer(customer);
//                orderTable.setCreatedDate(new Date());
//                orderTable.setPaymentMethod("Online");
//                orderTable.setCustomerAddressCity(customerAddress.getCity());
//                orderTable.setCustomerAddressState(customerAddress.getState());
//                orderTable.setCustomerAddressCountry(customerAddress.getCountry());
//                orderTable.setCustomerAddressLine(customerAddress.getAddressLine());
//                orderTable.setCustomerAddressZipcode(customerAddress.getZipCode());
//                orderTable.setCustomerAddressLabel(customerAddress.getLabel());
//
//                orderTableRepository.save(orderTable);
//
//                OrderProduct product = new OrderProduct();
//                product.setOrder(orderTable);
//                product.setProductVariation(productVariation.get().getProductVariation());
//                product.setPrice(productVariation.get().getPrice());
//                product.setQuantity(quantity);
//                orderProductRepository.save(product);
//
//                OrderStatus orderStatus = new OrderStatus();
//                orderStatus.setOrderProduct(product);
//                orderStatus.setFromStatus(FromStatus.ORDER_PLACED);
//                orderStatus.setToStatus(ToStatus.ORDER_CONFIRMED);
//                orderStatus.setTransitionNoteComment("Order Placed");
//                orderStatusRepository.save(orderStatus);
//                return "order Placed successfully " + orderTable.getOrder_id();
//            }
//            return "invalid product or quantity is greater than inventory";
//        }
//        throw  new IdNotFoundException("customer not found");
//    }
//
//    public String cancelOrder(long orderId, long customerId){
//        List<OrderProduct> orderProduct = orderProductRepository.findByOrderId(orderId);
//        Optional<OrderTable> orderTable = orderTableRepository.findById(orderId);
//
//        if(orderTable.isPresent()){
//            if(orderTable.get().getCustomer().getUser_id() == customerId){
//                if(!orderProduct.isEmpty()){
//                    System.out.println("inside if orderProduct");
//                    for(OrderProduct product: orderProduct){
//                        System.out.println("inside loop");
//                        OrderStatus orderStatus = orderStatusRepository.findByOrderProductId(product.getOrder_product_id());
//                        if(orderStatus.getFromStatus() == FromStatus.DELIVERED)
//                        {
//                            orderStatus.setToStatus(ToStatus.CANCELLED);
//                            orderStatus.setTransitionNoteComment("Order cancelled");
//                            orderStatusRepository.save(orderStatus);
//                            System.out.println("after save orderStatus repo");
//                        }
//                    }
//                    return "order cancelled";
//                }
//                return "product not found";
//            }
//            return "invalid customer";
//        }
//        throw new IdNotFoundException("order not found");
//    }
//
//    public String returnOrder(long orderId, long customerId){
//        List<OrderProduct> orderProduct = orderProductRepository.findByOrderId(orderId);
//        Optional<OrderTable> orderTable = orderTableRepository.findById(orderId);
//
//        if(orderTable.isPresent()){
//            if(orderTable.get().getCustomer().getUser_id() == customerId){
//                if(!orderProduct.isEmpty()){
//                    System.out.println("inside if orderProduct");
//                    for(OrderProduct product: orderProduct){
//                        System.out.println("inside loop");
//                        OrderStatus orderStatus = orderStatusRepository.findByOrderProductId(product.getOrder_product_id());
//                        if(orderStatus.getFromStatus() == FromStatus.DELIVERED)
//                        {
//                            orderStatus.setToStatus(ToStatus.RETURN_REQUESTED);
//                            orderStatus.setTransitionNoteComment("Order return requested");
//                            orderStatusRepository.save(orderStatus);
//                        }
//                    }
//                    return "order returned";
//                }
//                return "product not found";
//            }
//            return "invalid customer";
//        }
//        throw new IdNotFoundException("order not found");
//    }
//
//    public MappingJacksonValue showOrder(long orderId, long customerId){
//        Optional<OrderTable> orderTable = orderTableRepository.findById(orderId);
//        if(orderTable.isPresent() && orderTable.get().getCustomer().getUser_id() == customerId){
//            SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("order_id","amountPaid","paymentMethod","customerAddressCity","customerAddressState",
//                    "customerAddressCountry","customerAddressZipCode","customerAddressLabel");
//            FilterProvider filters = new SimpleFilterProvider().addFilter("orderFilter", filter);
//            MappingJacksonValue mapping = new MappingJacksonValue(orderTable.get());
//            mapping.setFilters(filters);
//            return mapping;
//        }
//        throw new IdNotFoundException("invalid customer");
//    }

    public MappingJacksonValue showAllOrder(long customerId){
        List<OrderTable> orderTable = orderTableRepository.findByCustomerId(customerId);
        if(!orderTable.isEmpty()){
            SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("order_id","amountPaid","paymentMethod","customerAddressCity","customerAddressState",
                    "customerAddressCountry","customerAddressZipCode","customerAddressLabel");
            FilterProvider filters = new SimpleFilterProvider().addFilter("orderFilter", filter);
            MappingJacksonValue mapping = new MappingJacksonValue(orderTable);
            mapping.setFilters(filters);
            return mapping;
        }
        throw new IdNotFoundException("invalid customer");
    }

//    public MappingJacksonValue getAllSellerOrders(long sellerId){
//        List<Product> products = productRepository.showAllSellerProducts(sellerId);
//        Set<Long> orderIds = new HashSet<>();
//        List<OrderTable> orders = new ArrayList<>();
//
//        for(Product product:products)
//        {
//            if(product.getSeller().getUser_id() == sellerId)
//            {
//                List<ProductVariation> productVariations = productVariationRepository.findByProductIdForSeller(product.getId());
//                for(ProductVariation productVariation:productVariations){
//                    List<OrderProduct> orderProducts = orderProductRepository.findByProductVariationId(productVariation.getId());
//                    for(OrderProduct orderProduct:orderProducts){
//                        orders.add(orderProduct.getOrder());
//                    }
//                }
//            }
//        }
//        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("order_id","amountPaid","paymentMethod","customerAddressCity","customerAddressState",
//                "customerAddressCountry","customerAddressZipCode","customerAddressLabel");
//        FilterProvider filters = new SimpleFilterProvider().addFilter("orderFilter", filter);
//        MappingJacksonValue mapping = new MappingJacksonValue(orders);
//        mapping.setFilters(filters);
//        return mapping;
//    }
//
//    public String changeOrderStatus(OrderStatusModel orderStatusModel, long sellerId){
//        Optional<OrderProduct> orderProduct = orderProductRepository.findById(orderStatusModel.getOrderProductId());
//        if(orderProduct.isPresent()){
//            if(orderProduct.get().getProductVariation().getProduct().getSeller().getUser_id() == sellerId){
//                OrderStatus orderStatus = orderStatusRepository.findByOrderProductId(orderStatusModel.getOrderProductId());
//                if(orderStatus.getFromStatus() == orderStatusModel.getFromStatus() && orderStatus.getToStatus() == orderStatusModel.getToStatus()){
//                    return "No changes occurred";
//                }
//                orderStatus.setToStatus(orderStatusModel.getToStatus());
//                orderStatus.setFromStatus(orderStatusModel.getFromStatus());
//                orderStatusRepository.save(orderStatus);
//                return "orderStatus updated";
//            }
//            return "unauthorised seller";
//        }
//        throw new IdNotFoundException("order product not found");
//    }

    public MappingJacksonValue getAllOrders(){
        List<OrderTable> orderTable = (List<OrderTable>) orderTableRepository.findAll();
        if(!orderTable.isEmpty()){
            SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("order_id","amountPaid","paymentMethod","customerAddressCity","customerAddressState",
                    "customerAddressCountry","customerAddressZipCode","customerAddressLabel");
            FilterProvider filters = new SimpleFilterProvider().addFilter("orderFilter", filter);
            MappingJacksonValue mapping = new MappingJacksonValue(orderTable);
            mapping.setFilters(filters);
            return mapping;
        }
        throw new IdNotFoundException("invalid customer");
    }

    public String adminChangeStatusOfOrder(OrderStatusModel orderStatusModel){
        Optional<OrderProduct> orderProduct = orderProductRepository.findById(orderStatusModel.getOrderProductId());
        if(orderProduct.isPresent()){
                OrderStatus orderStatus = orderStatusRepository.findByOrderProductId(orderStatusModel.getOrderProductId());
                orderStatus.setToStatus(orderStatusModel.getToStatus());
                orderStatus.setFromStatus(orderStatusModel.getFromStatus());
                orderStatusRepository.save(orderStatus);
                return "orderStatus updated";
        }
        throw new IdNotFoundException("order product not found");
    }
}

