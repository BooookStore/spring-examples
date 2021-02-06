package com.example.spring.security.controller.usermanagement;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("userManagement")
public class UserManagementController {

    @GetMapping
    public String userList(Model model) {
        var userList = new ArrayList<>();
        userList.add(new UserListRowDto(1, "demo1"));
        userList.add(new UserListRowDto(2, "demo2"));
        model.addAttribute("users", userList);
        return "userManagement";
    }

}
