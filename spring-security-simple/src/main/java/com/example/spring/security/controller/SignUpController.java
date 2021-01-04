package com.example.spring.security.controller;

import com.example.spring.security.security.UserEntity;
import com.example.spring.security.security.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class SignUpController {

    private final Logger logger = LoggerFactory.getLogger(SignUpController.class.getName());

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public SignUpController(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String signUpForm(Model model) {
        model.addAttribute("signUpForm", new SignUpForm());
        return "signUpForm";
    }

    @PostMapping
    public String singUp(@Valid SignUpForm signUpForm, BindingResult result) {
        logger.info("accept sign up with username {}", signUpForm.getUsername());

        if (result.hasErrors()) {
            return "signUpForm";
        }

        createNewUser(signUpForm.getUsername(), signUpForm.getPassword());
        return "redirect:/login";
    }

    private void createNewUser(String username, String rawPassword) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        int newIdentity = userMapper.generateNextIdentity();
        UserEntity newUserEntity = new UserEntity(newIdentity, username, encodedPassword, null);
        userMapper.insertUser(newUserEntity);
        userMapper.insertRole(newIdentity, "NORMAL");
    }

}
