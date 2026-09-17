package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class AuthRequest{
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;
}