package com.example.demo.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InMemoryUserDetailsService implements UserDetailsService{
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public InMemoryUserDetailsService(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        if("user".equals(username)){
            return new User(
                "user",
                passwordEncoder.encode("123456"),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        }
        else if("admin".equals(username)){
            return new User(
                "admin",
                passwordEncoder.encode("adminrole"),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
