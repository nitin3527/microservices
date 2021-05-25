package com.ttn.ecommerce.admin.adminmicroservice.Repository;


import com.ttn.ecommerce.admin.adminmicroservice.Entity.orders.OrderStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrderStatusRepository extends CrudRepository<OrderStatus, Long> {

    @Query(value = "from OrderStatus where order_Product_Id=:orderProductId")
    OrderStatus findByOrderProductId(@Param("orderProductId") long orderProductId);
}
