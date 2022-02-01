package com.ionknowmyname.userauth.repository;


import com.ionknowmyname.userauth.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {


    // Optional<org.springframework.security.core.userdetails.User> findByUsername(String username);

    // Optional<User> findByUsername(String username);

    User findByUsername(String username);




    /*Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);*/
}
