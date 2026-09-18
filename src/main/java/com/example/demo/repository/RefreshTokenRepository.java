package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.RefreshTokenEntity;

@Repository 
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, String> {
    Optional <RefreshTokenEntity> findByRefreshToken(String refreshToken);
}