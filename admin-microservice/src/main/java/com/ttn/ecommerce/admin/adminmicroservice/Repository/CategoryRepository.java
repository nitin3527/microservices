package com.ttn.ecommerce.admin.adminmicroservice.Repository;


import com.ttn.ecommerce.admin.adminmicroservice.Entity.products.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Category findByNameIgnoreCase(String name);

    @Query(value = "select * from category where parent_category_id=:id", nativeQuery = true)
    List<Category> findParentCategoryOfChild(@Param("id") long id);

    @Query("from Category")
    List<Category> findAllCategory(Pageable pageable);
}
