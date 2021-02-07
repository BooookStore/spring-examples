package com.example.spring.security.controller.usermanagement;

import com.example.spring.security.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("manage")
@Transactional
public class UserManagementController {

    private final UserRepository userRepository;

    public UserManagementController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    @Transactional(readOnly = true)
    public String userList(Model model) {
        List<UserListRowDto> users = userRepository.findAll().stream()
                .map(user -> new UserListRowDto(user.getId(), user.getUsername()))
                .collect(Collectors.toList());
        model.addAttribute("users", users);
        return "usermanagement/userManagement";
    }

}
