package com.angelogoes.contactbookapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.angelogoes.contactbookapp.dto.LoginRequest;
import com.angelogoes.contactbookapp.dto.RegisterRequest;
import com.angelogoes.contactbookapp.model.User;
import com.angelogoes.contactbookapp.service.AuthService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class AuthController {
    
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<Boolean> login(@RequestBody LoginRequest loginRequest) {
        boolean loginSuccess = authService.login(loginRequest);
        return new ResponseEntity<>(loginSuccess, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return new ResponseEntity<>("User Registration Successful", HttpStatus.OK);
    }

}
