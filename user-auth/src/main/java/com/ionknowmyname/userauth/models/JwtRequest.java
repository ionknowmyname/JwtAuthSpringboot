package com.ionknowmyname.userauth.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data // getters, setters, toString, equalsAndHashCode & RequiredArgsConstructor(which is for final variables)
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {

    // @NotBlank
    // @NotNull
    private String username;
    private String password;

    // lombok creates the getters and setters and constructor
}
