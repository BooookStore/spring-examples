package com.example.spring.security.controller;

import com.example.spring.security.security.UserEntity;
import com.example.spring.security.security.SecurityUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("signup")
public class SignUpController {

    private final Logger logger = LoggerFactory.getLogger(SignUpController.class.getName());

    private final SecurityUserMapper securityUserMapper;

    private final PasswordEncoder passwordEncoder;

    public SignUpController(SecurityUserMapper securityUserMapper, PasswordEncoder passwordEncoder) {
        this.securityUserMapper = securityUserMapper;
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
        int newIdentity = securityUserMapper.generateNextIdentity();
        UserEntity newUserEntity = new UserEntity(newIdentity, username, encodedPassword, null);
        securityUserMapper.insertUser(newUserEntity);
        securityUserMapper.insertRole(newIdentity, "NORMAL");
    }

}
