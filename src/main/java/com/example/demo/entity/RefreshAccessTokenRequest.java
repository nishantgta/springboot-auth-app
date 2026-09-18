package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class RefreshAccessTokenRequest {
    
    @JsonProperty("refreshToken")
    private String refreshToken;
}
