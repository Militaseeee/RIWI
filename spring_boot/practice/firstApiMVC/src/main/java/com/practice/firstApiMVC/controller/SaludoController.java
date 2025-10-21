package com.practice.firstApiMVC.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Se debe mapear para saber que es un controlador
@RestController

// Se hace una solicitud -> se recibe desde un cliente
@RequestMapping("/apisaludos")

public class SaludoController {

    // This is a get method
    @GetMapping("/hola")
    public String helloWorld() {
        return "Hello world c:";
    }

    @GetMapping("/holanombre/{nombre}/{edad}")
    // Para que la persona pueda poner su nombre y su edad
    public String holaMundoNombre(@PathVariable String nombre, @PathVariable int edad) {
        return "Hola mundo " + nombre + " tu edad es: " + edad;
    }
}
