package com.ttn.project.customermicroservice.Repositry;


import com.ttn.project.customermicroservice.Entity.User;
import com.ttn.project.customermicroservice.token.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer> {
      ConfirmationToken findByConfirmationToken(String confirmationToken);
      void deleteByConfirmationToken(String ConfirmationToken);
      ConfirmationToken findByUser(User user);
}
