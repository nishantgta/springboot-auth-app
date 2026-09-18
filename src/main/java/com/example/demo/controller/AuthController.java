package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import com.example.demo.service.AuthService;
import com.example.demo.entity.AuthRequest;
import com.example.demo.entity.AuthResponse;
import com.example.demo.entity.RefreshAccessTokenRequest;

@RestController
@RequiredArgsConstructor 
@RequestMapping("/api/auth")
public class AuthController{

    private final AuthService authService;

    @PostMapping("/token")
    public AuthResponse login(@RequestBody AuthRequest authRequest){
        return authService.authenticate(authRequest);
    }

    @PostMapping("/token/refresh")
    public AuthResponse refresh(@RequestBody RefreshAccessTokenRequest request){
        return authService.refreshAccessToken(request.getRefreshToken());
    }
}