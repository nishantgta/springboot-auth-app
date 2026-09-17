package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.demo.service.HashService;
import com.example.demo.entity.RegisterUserRequestModel;

@RestController
public class RegisterUser {

    @Autowired
    private HashService hashService;

    @PostMapping("/user/register")
    public ResponseEntity<String> register(@RequestBody RegisterUserRequestModel requestBody){
        System.out.println("Generate Password!");
        System.out.println("Before saving Username="+requestBody.getUsername());
        System.out.println("Raw Password="+requestBody.getPassword());
        String hashedPassword = hashService.hashandStorePassword(requestBody.getPassword(), requestBody.getUsername());
        return ResponseEntity.ok("Response");
    }

    @PostMapping("/sample/checkPassword")
    public ResponseEntity<String> samplePasswordCheck(@RequestHeader String password){
        Boolean isValidatedPassword = hashService.validatePassword(password);
        return isValidatedPassword ? ResponseEntity.ok("Correct Password"):ResponseEntity.ok("Incorrect Password");
    }
}