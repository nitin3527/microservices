package com.ttn.ecommerce.admin.adminmicroservice.Models;


import com.ttn.ecommerce.admin.adminmicroservice.Entity.products.Category;

public class AddCategoryModel {

    private String name;

    private Category parentCategory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }
}
