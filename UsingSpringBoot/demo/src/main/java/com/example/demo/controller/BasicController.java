package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController() // no usages
    public class BasicController {

    @GetMapping("/hola") // no usages
    public String holaEndpoint() {
        return "Hola desde Spring Boot";
    }

    @GetMapping("/adios") // no usages
    public String adiosEndpoint() {
        return "Adios desde Spring Boot";
    }
}
