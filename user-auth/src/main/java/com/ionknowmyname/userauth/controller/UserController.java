package com.ionknowmyname.userauth.controller;

import com.ionknowmyname.userauth.models.JwtRequest;
import com.ionknowmyname.userauth.models.JwtResponse;
import com.ionknowmyname.userauth.models.User;
import com.ionknowmyname.userauth.services.UserService;
import com.ionknowmyname.userauth.utils.JwtUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/auth")
@Slf4j
public class UserController {

    @Autowired
    private UserService userDetailsService;

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;   // from SecurityConfig



    @GetMapping("/home")
    public String home(){
        return "you are successfully authenticated";
    }


    @PostMapping("/authenticate")  // can be signin
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e){
            throw new Exception("Invalid Credentials", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        System.out.println("loadbyusername from UserController: " + userDetails);

        final String token  = jwtUtility.generateToken(userDetails);


        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        log.info("roles from /authenticate in UserController: {}", roles);

        //return new JwtResponse(token);

        return new JwtResponse(token, userDetails.getUsername(), roles);

        // setting security context is done under JwtFilter
    }


    @PostMapping("/signup")
    public String saveUser(@Valid @RequestBody User user){ // User user
        return userDetailsService.saveUser(user);
    }

    @PostMapping("/signupAdmin")
    public String saveAdmin(@Valid @RequestBody User user){
        return userDetailsService.saveAdmin(user);
    }

    /*

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody JwtRequest jwtRequest) throws Exception {

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid Credentials", e);
        }

        final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtility.generateToken(userDetails);


        *//*
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));

        *//*


        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    */
}
