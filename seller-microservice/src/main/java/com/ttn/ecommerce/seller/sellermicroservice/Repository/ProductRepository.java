package com.ttn.ecommerce.seller.sellermicroservice.Repository;


import com.ttn.ecommerce.seller.sellermicroservice.Entity.products.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    @Query(value = "select * from product where name=:name AND " +
            "category_id=:categoryId AND " +
            "brand=:brand AND seller_id=:sellerId", nativeQuery = true)
    Product findExistingProduct(@Param("categoryId") long categoryId,
                                @Param("name") String name,
                                @Param("brand") String brand,
                                @Param("sellerId") long sellerId);



    @Query(value = "select * from product where seller_id=:seller_id AND id=:product_id", nativeQuery = true)
    Product findSellerProduct(@Param("seller_id") long seller_id,
                                  @Param("product_id") long product_id);

    @Query(value = "select * from product where seller_id=:seller_id AND is_deleted=false",nativeQuery = true)
    List<Product> showAllSellerProducts(@Param("seller_id") long seller_id);

    @Query(value = "select * from product where id=:productId AND is_deleted=false",nativeQuery = true)
    Optional<Product> findProductById(@Param("productId") long productId);

    @Query(value = "select * from product where id=:productId AND is_deleted=false AND is_active=true",nativeQuery = true)
    Optional<Product> findProductForCustomer(@Param("productId") long productId);

    @Query(value = "select * from product where category_id=:categoryId",nativeQuery = true)
    Optional<List<Product>> findProductByCategoryId(@Param("categoryId") long categoryId);

    @Query(value = "select * from product where category_id=:categoryId AND is_active=true",nativeQuery = true)
    Optional<List<Product>> findAllProductForCustomer(@Param("categoryId") long categoryId);
}

