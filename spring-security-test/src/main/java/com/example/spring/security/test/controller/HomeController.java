package com.example.spring.security.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/users")
    public String users() {
        return "users";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

}
