package com.ttn.project.customermicroservice.Validation;






import com.ttn.project.customermicroservice.Model.CustomerRegistrationModel;
import com.ttn.project.customermicroservice.Model.UpdatePasswordModel;

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
        if(obj instanceof CustomerRegistrationModel){
            CustomerRegistrationModel customerRegistrationModel = (CustomerRegistrationModel) obj;
            return customerRegistrationModel.getConfirmPassword().equals(customerRegistrationModel.getPassword());
        }
        else if(obj instanceof UpdatePasswordModel){
            UpdatePasswordModel updatePasswordModel = (UpdatePasswordModel) obj;
            return updatePasswordModel.getPassword().equals(updatePasswordModel.getConfirmPassword());
        }
        else {
            throw new RuntimeException("not a customer");
        }

    }
}