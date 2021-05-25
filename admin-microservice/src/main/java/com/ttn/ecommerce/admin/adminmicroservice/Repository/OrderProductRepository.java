package com.ttn.ecommerce.admin.adminmicroservice.Repository;


import com.ttn.ecommerce.admin.adminmicroservice.Entity.orders.OrderProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderProductRepository extends CrudRepository<OrderProduct, Long> {

    @Query(value = "from OrderProduct where order_id=:orderId")
    List<OrderProduct> findByOrderId(@Param("orderId") long orderId);

    @Query(value = "from OrderProduct where product_variation_id=:productVariationId")
    List<OrderProduct> findByProductVariationId(@Param("productVariationId") long productVariationId);
}
