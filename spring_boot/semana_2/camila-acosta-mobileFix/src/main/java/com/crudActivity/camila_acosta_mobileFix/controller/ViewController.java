package com.crudActivity.camila_acosta_mobileFix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    // Muestra la página de login (que ahora simula la sesión)
    // El login.jsp ahora maneja la redirección directamente a /rol_dashboard.jsp
    @GetMapping({"/", "/login"})
    public String loginPage() {
        return "login";
    }

    // Mapeo directo para cada dashboard
    @GetMapping("/admin_dashboard")
    public String adminDashboard() {
        return "admin_dashboard";
    }

    @GetMapping("/tech_dashboard")
    public String techDashboard() {
        return "tech_dashboard";
    }

    @GetMapping("/user_dashboard")
    public String userDashboard() {
        return "user_dashboard";
    }
}