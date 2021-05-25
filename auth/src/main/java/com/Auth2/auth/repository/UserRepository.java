package com.Auth2.auth.repository;


import com.Auth2.auth.enitity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findAllByEmailIgnoreCase(String email);

    User findAllByUsernameIgnoreCase(String username);


    User findByUsername(String username);

    @Query("from User")
    List<User> findAllUsers();
}
