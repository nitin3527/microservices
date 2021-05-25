package com.ttn.ecommerce.seller.sellermicroservice.Models;



import com.ttn.ecommerce.seller.sellermicroservice.validations.FieldMatch;
import com.ttn.ecommerce.seller.sellermicroservice.validations.ValidRegex;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@FieldMatch
public class UpdatePasswordModel {
    @NotNull
    @Pattern(regexp = ValidRegex.PASSWORD)
    private String password;

    @NotNull
    @Pattern(regexp = ValidRegex.PASSWORD)
    private String confirmPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
