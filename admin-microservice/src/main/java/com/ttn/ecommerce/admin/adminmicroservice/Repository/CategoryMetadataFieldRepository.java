package com.ttn.ecommerce.admin.adminmicroservice.Repository;


import com.ttn.ecommerce.admin.adminmicroservice.Entity.products.CategoryMetadataField;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryMetadataFieldRepository extends CrudRepository<CategoryMetadataField, Long> {
    String findByNameIgnoreCase(String name);
    CategoryMetadataField findByName(String name);

    @Query("from CategoryMetadataField")
    List<CategoryMetadataField> findAllMetadataField(Pageable pageable);

}
