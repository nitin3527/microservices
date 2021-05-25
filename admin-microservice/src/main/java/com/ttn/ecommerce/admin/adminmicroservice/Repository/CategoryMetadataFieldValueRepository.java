package com.ttn.ecommerce.admin.adminmicroservice.Repository;


import com.ttn.ecommerce.admin.adminmicroservice.Entity.products.CategoryMetadataFieldValue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryMetadataFieldValueRepository extends CrudRepository<CategoryMetadataFieldValue, Long> {

    @Query(value = "select * from category_metadata_field_value where category_category_id=:categoryId AND category_metadata_field_id=:fieldId", nativeQuery = true)
    CategoryMetadataFieldValue getFieldValueObj(@Param("categoryId") long categoryId,
                                                @Param("fieldId") long fieldId);

    @Query(value = "select * from category_metadata_field_value where category_category_id=:categoryId", nativeQuery = true)
    List<CategoryMetadataFieldValue> getFieldValueFromCategory(@Param("categoryId") long categoryId);

    @Query(value = "select * from category_metadata_field_value where category_metadata_field_id=:metadataFieldId", nativeQuery = true)
    CategoryMetadataFieldValue getMetadataFieldValueForMetadata(@Param("metadataFieldId") long metadataFieldId);
}
