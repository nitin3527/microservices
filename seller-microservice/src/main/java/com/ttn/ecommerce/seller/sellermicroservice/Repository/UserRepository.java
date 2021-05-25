package com.ttn.ecommerce.seller.sellermicroservice.Repository;


import com.ttn.ecommerce.seller.sellermicroservice.Entity.Address;
import com.ttn.ecommerce.seller.sellermicroservice.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findAllByEmailIgnoreCase(String email);
    User findAllByUsernameIgnoreCase(String username);


    User findByUsername(String username);
    @Query("from User")
    List<User> findAllUsers();


    @Query("from Address where user_id=:id")
    List<Address> showAllAddresses(@Param("id") long id);
}
