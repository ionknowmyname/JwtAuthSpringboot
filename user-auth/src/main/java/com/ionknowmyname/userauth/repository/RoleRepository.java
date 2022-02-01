package com.ionknowmyname.userauth.repository;

import com.ionknowmyname.userauth.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {

    // Optional<Role> findByName(RoleEnum name);

    // List<Role> findByRole(String role);

    Role findByRole(String role);
}
