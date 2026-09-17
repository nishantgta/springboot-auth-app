package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.RegisterUserEntity;

@Repository /*not required as we are using JpaRepository*/
public interface RegisterUserRepository extends JpaRepository<RegisterUserEntity, String>{
    Optional <RegisterUserEntity> findByUsername(String username); 
}
