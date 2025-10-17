package com.example.jwt.controller;

import com.example.jwt.service.AuthenticationServices;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    public final AuthenticationServices authenticationServices;

    public AuthenticationController(AuthenticationServices authenticationServices){
        this.authenticationServices =  authenticationServices;
    }

    @PostMapping("authenticate")
    public String authenticate(Authentication authentication){
        return authenticationServices.authenticate(authentication);
    }
}
