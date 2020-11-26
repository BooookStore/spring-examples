package com.example.spring.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home/account")
public class AccountSettingController {

    @GetMapping
    public String account(Authentication authentication, Model model) {
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "account";
    }

}
