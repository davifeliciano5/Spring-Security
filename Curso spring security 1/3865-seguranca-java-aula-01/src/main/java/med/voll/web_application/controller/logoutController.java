package med.voll.web_application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class logoutController {

    @GetMapping("/logout")
    public String logout(){
        return "autenticacao/logout";
    }
}
