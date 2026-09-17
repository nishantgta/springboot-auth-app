package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
// import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

@Entity
@Data
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
public class RefreshTokenEntity {
    // @Id 
    // @GeneratedValue(strategy = GenerationType.UUID)
    // private UUID id;

    @Id
    private String username;
    private String refreshToken;

    @CreationTimestamp 
    private Instant expiry_at;

    @Builder.Default
    private boolean revoked = false;
}
