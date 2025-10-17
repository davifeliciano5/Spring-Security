package com.example.jwt.service;

import com.example.jwt.infra.jwt.JwtService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServices {

    private final JwtService jwtService;
    public AuthenticationServices(JwtService jwtService){
        this.jwtService = jwtService;
    }

    public String authenticate(Authentication authentication){
        return jwtService.generateToken(authentication);
    }
}
