package com.example.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("login")
    public String login(@RequestParam(required = false) String logout, Model model) {
        if (logout != null) model.addAttribute("logout", true);
        return "login";
    }

}
