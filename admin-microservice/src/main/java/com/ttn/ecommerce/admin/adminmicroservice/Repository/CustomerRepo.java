package com.ttn.ecommerce.admin.adminmicroservice.Repository;



import com.ttn.ecommerce.admin.adminmicroservice.Entity.Address;
import com.ttn.ecommerce.admin.adminmicroservice.Entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Long> {
//    Customer findByUserId(int id);
      Customer findByUsername(String username);
      Customer findById(long id);

      @Query("from Address where user_id=:id")
      List<Address> showAllAddresses(@Param("id") long id);

      @Query("from Customer")
      List<Customer> findAllCustomers(Pageable pageable);
}
