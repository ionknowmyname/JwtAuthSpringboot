package com.ionknowmyname.userauth.controller;


import com.ionknowmyname.userauth.models.Role;
import com.ionknowmyname.userauth.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("api/role")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;


    @PostMapping("/add")
    public String addRole(@RequestBody Role role){

        Role rolename = roleRepository.findByRole(role.getRole());

        if (Objects.equals(rolename.getRole(), "ADMIN")) {
            throw new IllegalStateException("ADMIN role already exists");
        }

        if (rolename.getRole().equals("USER")) {
            throw new IllegalStateException("USER role already exists");
        }

        System.out.println("rolename from RoleController" + rolename.getRole());

        roleRepository.save(role);

        return "New role '" + role.getRole() + "' added";

        // return roleService.addRole(role);
    }
}
