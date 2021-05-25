package com.ttn.ecommerce.seller.sellermicroservice.validations;


import com.ttn.ecommerce.seller.sellermicroservice.Models.SellerModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object>
{

    @Override
    public void initialize(final FieldMatch constraintAnnotation)
    {

    }

    @Override
    public boolean isValid(Object obj, final ConstraintValidatorContext context)
    {
        if(obj instanceof SellerModel){
            SellerModel sellerModel = (SellerModel) obj;
            return sellerModel.getPassword().equals(sellerModel.getConfirmPassword());
        }
        else {
            throw new RuntimeException("User not found");
        }

    }
}