package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RegisterUserEntity;
import com.example.demo.repository.RegisterUserRepository;

import org.mindrot.jbcrypt.BCrypt;

@Service
public class HashService {

    @Autowired
    private RegisterUserRepository registerUserRepository;

    public String hashandStorePassword(String password, String username){
        int logRounds = 12;
        String salt = BCrypt.gensalt(logRounds);
        String hashPassword = BCrypt.hashpw(password, salt);
        /*store username and password*/
        RegisterUserEntity entity = new RegisterUserEntity();
        entity.setUsername(username); entity.setPassword(hashPassword);
        RegisterUserEntity returnedEntity = registerUserRepository.save(entity);
        //System.out.println("After saving Id="+returnedEntity.getId());
        System.out.println("After saving username="+returnedEntity.getUsername());
        System.out.println("Hashed Password="+returnedEntity.getPassword());
        return returnedEntity.getPassword();
    }

    private Boolean checkForPassword(String password, String storedHash){
        return BCrypt.checkpw(password, storedHash);
    }

    public Boolean validatePassword(String password){
        String sampleHash="$2a$12$oU98qkEUnRwhYR8vR1KAtO6IDHQe2nmiuuqav6njHkWPg9mW/8O5a";
        return checkForPassword(password, sampleHash);
    }
}
