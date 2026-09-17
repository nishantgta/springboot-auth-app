package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class AuthResponse {

    private String jwtToken;
    private String name;
    private Long expiresAt;

    public AuthResponse(String jwtToken2, String name2, Long expiresAt2) {
        this.jwtToken = jwtToken2;
        this.name = name2;
        this.expiresAt = expiresAt2;
    }
}
