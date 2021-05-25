package com.ttn.ecommerce.admin.adminmicroservice.Repository;


import com.ttn.ecommerce.admin.adminmicroservice.Entity.orders.OrderTable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderTableRepository extends CrudRepository<OrderTable, Long> {

    @Query(value = "from OrderTable where customer_id=:customerId")
    List<OrderTable> findByCustomerId(@Param("customerId") long customerId);


}
