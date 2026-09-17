package com.example.demo.entity;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
public class RegisterUserRequestModel {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;
}
