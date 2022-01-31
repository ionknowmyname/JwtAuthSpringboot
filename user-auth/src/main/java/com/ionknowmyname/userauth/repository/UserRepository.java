package com.ionknowmyname.userauth.repository;


import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  { // extends MongoRepository<User, String>

    /*Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);*/
}
