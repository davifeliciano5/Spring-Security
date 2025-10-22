package com.security.alura1.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {

    @GetMapping(value = "/login")
    public String carregaPaginaListagem(){
        return "autenticacao/login.html";
    }

}
