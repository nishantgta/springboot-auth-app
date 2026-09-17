package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


// import java.sql.Date;
import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RegisterUserEntity {

    // @Id
    // @GeneratedValue(strategy = GenerationType.UUID)
    //private UUID id;

    @Id 
    private String username;

    //@Column(nullable = false, updatable = false)
    @CreationTimestamp 
    private Instant created_at;

    private String password;
}
