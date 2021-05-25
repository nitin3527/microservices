package com.ttn.ecommerce.seller.sellermicroservice.Repository;


import com.ttn.ecommerce.seller.sellermicroservice.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Override
    void deleteById(Integer id);

    @Modifying
    @Query("delete from Address where id=:addressId AND user_id=:user_id")
    void deleteAddressId(@Param("addressId") int addressId, @Param("user_id") long user_id);
}
