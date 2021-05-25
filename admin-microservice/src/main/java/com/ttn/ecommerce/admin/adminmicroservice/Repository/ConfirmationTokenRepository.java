package com.ttn.ecommerce.admin.adminmicroservice.Repository;


import com.ttn.ecommerce.admin.adminmicroservice.Entity.ConfirmationToken;
import com.ttn.ecommerce.admin.adminmicroservice.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer> {
      ConfirmationToken findByConfirmationToken(String confirmationToken);
      void deleteByConfirmationToken(String ConfirmationToken);
      ConfirmationToken findByUser(User user);
}
