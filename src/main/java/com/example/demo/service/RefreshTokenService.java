package com.example.demo.service;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.RefreshTokenRepository;
import com.example.demo.entity.RefreshTokenEntity;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class RefreshTokenService {
    private static final long REFRESH_TOKEN_VALIDITY_DAYS = 7;

    private final RefreshTokenRepository refreshTokenRepository;

    private final SecureRandom secureRandom = new SecureRandom();

    @Transactional 
    public String createRefreshToken(String username){
        String tokenValue = generateSecureToken();

        RefreshTokenEntity refreshToken = refreshTokenRepository
                .findById(username)
                .orElseGet(()-> RefreshTokenEntity.builder()
                        .username(username)
                        .build());

        refreshToken.setRefreshToken(tokenValue);
        refreshToken.setExpiry_at(
            Instant.now().plus(
                REFRESH_TOKEN_VALIDITY_DAYS,
                ChronoUnit.DAYS
            )
        );
        refreshToken.setRevoked(false);
        refreshTokenRepository.save(refreshToken);
        return tokenValue;
    }

    @Transactional
    public RefreshTokenEntity validateRefreshToken(String tokenValue){
        RefreshTokenEntity refreshToken = refreshTokenRepository
                .findByRefreshToken(tokenValue)
                .orElseThrow(()-> new IllegalArgumentException(
                    "Refresh Token not found"
                ));

        if(refreshToken.isRevoked()){
            throw new IllegalArgumentException(
                "Refresh token has been revoked"
            );
        }

        if(refreshToken.getExpiry_at().isBefore(Instant.now())){
            refreshTokenRepository.delete(refreshToken);

            throw new IllegalArgumentException(
                "Refresh token is expired"
            );
        }
        return refreshToken;
    }

    @Transactional 
    public void revokeRefreshToken(String tokenValue){
        refreshTokenRepository.findByRefreshToken(tokenValue)
            .ifPresent(refreshToken -> {
                refreshToken.setRevoked(false);
                refreshTokenRepository.save(refreshToken);
            });
    }

    private String generateSecureToken(){
        byte[] randomBytes = new byte[64];
        secureRandom.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }
}
