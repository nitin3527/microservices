package com.ttn.ecommerce.seller.sellermicroservice.Entity.products;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ttn.ecommerce.seller.sellermicroservice.utilites.MetaDataFieldValueId;

import javax.persistence.*;

@Entity
public class CategoryMetadataFieldValue {

    @EmbeddedId
    @JsonIgnore
    MetaDataFieldValueId id = new MetaDataFieldValueId();

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("categoryId")
    Category category;

    private String fieldValue;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("categoryMetaDataFieldId")
    CategoryMetadataField categoryMetadataField;

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public MetaDataFieldValueId getId() {
        return id;
    }

    public void setId(MetaDataFieldValueId id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public CategoryMetadataField getCategoryMetadataField() {
        return categoryMetadataField;
    }

    public void setCategoryMetadataField(CategoryMetadataField categoryMetadataField) {
        this.categoryMetadataField = categoryMetadataField;
    }

    @Override
    public String toString() {
        return "CategoryMetadataFieldValue{" +
                "id=" + id +
                ", category=" + category +
                ", fieldValue='" + fieldValue + '\'' +
                ", categoryMetadataField=" + categoryMetadataField +
                '}';
    }
}
