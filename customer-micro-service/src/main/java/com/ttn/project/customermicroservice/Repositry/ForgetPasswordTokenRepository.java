package com.ttn.project.customermicroservice.Repositry;


import com.ttn.project.customermicroservice.Entity.User;
import com.ttn.project.customermicroservice.token.ForgotPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForgetPasswordTokenRepository extends JpaRepository<ForgotPasswordToken,Integer> {
    ForgotPasswordToken findByForgotPasswordToken(String forgotPasswordToken);
    void deleteByForgotPasswordToken(String ConfirmationToken);
    ForgotPasswordToken findByUser(User user);
}
