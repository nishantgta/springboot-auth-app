package com.example.demo.entity;

import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.DialectOverride.OverridesAnnotation;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.RequiredArgsConstructor;

import com.example.demo.entity.RegisterUserEntity;

@RequiredArgsConstructor 
public class AuthUser implements UserDetails{

    private final RegisterUserEntity registerUserEntity;

    @Override
    public String getUsername(){
        return registerUserEntity.getUsername();
    }

    @Override
    public String getPassword(){
        return registerUserEntity.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }
}