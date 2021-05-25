package com.ttn.ecommerce.seller.sellermicroservice.utilites;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MetaDataFieldValueId implements Serializable {
    private long categoryId;
    private long categoryMetaDataFieldId;
}
