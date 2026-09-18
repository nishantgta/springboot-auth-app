package com.example.demo.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.demo.entity.AuthRequest;
import com.example.demo.entity.AuthResponse;
import com.example.demo.entity.RefreshTokenEntity;

import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor 
public class AuthService{

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final RefreshTokenService refreshTokenService;

    public AuthResponse authenticate(AuthRequest authRequest){
        var token = new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(), 
                            authRequest.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(token);
        
        String jwtToken = jwtTokenService.generateToken(authentication);
        Long expiresAt = jwtTokenService.extractExpirationTime(jwtToken);
        String refreshToken = refreshTokenService.createRefreshToken(authentication.getName());

        return new AuthResponse(jwtToken, authentication.getName(), expiresAt, refreshToken);
    }

    @Transactional
    public AuthResponse refreshAccessToken(String tokenValue){
        RefreshTokenEntity oldRefreshToken = refreshTokenService.validateRefreshToken(tokenValue);
        String username = oldRefreshToken.getUsername();
        String newJwtToken = jwtTokenService.generateTokenForUsername(username);
        Long expiresAt = jwtTokenService.extractExpirationTime(newJwtToken);
        refreshTokenService.revokeRefreshToken(tokenValue);
        String newRefreshToken = refreshTokenService.createRefreshToken(username);
        return new AuthResponse(
            newJwtToken,
            username,
            expiresAt,
            newRefreshToken
        );
    }
}