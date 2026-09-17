package com.example.demo.service;
import com.example.demo.repository.RegisterUserRepository;
import com.example.demo.entity.AuthUser;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class AuthUserDetailsService implements UserDetailsService {

    private final RegisterUserRepository registerUserRepository;

    @Override
    public UserDetails loadUserByUsername(String Username) {
        return registerUserRepository.findByUsername(Username)
            .map(AuthUser::new)
            .orElseThrow(()->new UsernameNotFoundException("User not found: " + Username));
    }

}