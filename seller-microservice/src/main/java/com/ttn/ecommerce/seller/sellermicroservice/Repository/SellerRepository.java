package com.ttn.ecommerce.seller.sellermicroservice.Repository;


import com.ttn.ecommerce.seller.sellermicroservice.Entity.Seller;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SellerRepository extends CrudRepository<Seller, Long> {

    Seller findByGst(String gst);
    Seller findByCompanyName(String companyName);

    @Query("from Seller")
    List<Seller> findAll(Pageable pageable);
}
