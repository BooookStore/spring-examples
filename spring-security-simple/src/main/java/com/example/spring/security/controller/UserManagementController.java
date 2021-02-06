package com.example.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("userManagement")
public class UserManagementController {

    @GetMapping
    public String userList() {
        return "userManagement";
    }

}
