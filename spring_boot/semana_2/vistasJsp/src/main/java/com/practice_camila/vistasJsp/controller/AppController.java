package com.practice_camila.vistasJsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

    @GetMapping("/")
    public String landing() {
        return "landing"; // landing.jsp
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login"; // login.jsp
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam String username,
                              @RequestParam String password,
                              Model model) {

        if (username.equals("admin") && password.equals("1234")) {
            model.addAttribute("nombre", username);
            return "home"; // home.jsp
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
        }
    }
}
