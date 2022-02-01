package com.ionknowmyname.userauth.services;

import com.ionknowmyname.userauth.models.Role;
import com.ionknowmyname.userauth.models.User;
import com.ionknowmyname.userauth.repository.RoleRepository;
import com.ionknowmyname.userauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
// @AllArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // logic to get user from DB
        User user = userRepository.findByUsername(username);
        System.out.println("from loadbyusername: " + user);

        if(user != null) {
            List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());

            return buildUserForAuthentication(user, authorities);
        } else {
            throw new UsernameNotFoundException("username not found");
        }

       // return new User("testing", "testing2", new ArrayList<>()); // arraylist is posed to be the role
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        });

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    // connecting MongoDB user to Spring Security user as called from the `loadUserByUsername`
    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////










    public User findByUsername(String username) { // if errors, figure out which user, userdetails user or model user
        return userRepository.findByUsername(username);
    }

    public String saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // user.setEnabled(true);

        Role userRole = roleRepository.findByRole("USER"); // ADMIN
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));

        userRepository.save(user);

        return "New '" + user.getRoles() + "' user with username '" + user.getUsername() + "' successfully registered";

    }

}
