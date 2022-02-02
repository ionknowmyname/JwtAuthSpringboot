package com.ionknowmyname.userauth.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
// @AllArgsConstructor
public class JwtResponse {

    private String jwtToken;
    private String username;
    private List<String> roles;

    public JwtResponse(String token) {
        this.jwtToken = token;
    }


    public JwtResponse(String token, String username, List<String> roles) {
        this.jwtToken = token;
        this.username = username;
        this.roles = roles;
    }



    // lombok creates the getters and setters and constructor
}
