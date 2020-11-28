package com.example.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home/account")
public class AccountSettingController {

    @GetMapping
    public String account() {
        return "account";
    }

}
