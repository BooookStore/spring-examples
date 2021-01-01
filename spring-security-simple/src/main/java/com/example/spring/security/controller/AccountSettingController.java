package com.example.spring.security.controller;

import com.example.spring.security.security.UserEntity;
import com.example.spring.security.security.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/home/account")
public class AccountSettingController {

    private Logger logger = LoggerFactory.getLogger(AccountSettingController.class.getName());

    private final UserMapper userMapper;

    public AccountSettingController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping
    @Transactional(readOnly = true)
    public String account(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Optional<UserEntity> optional = userMapper.findUserByUsername(userDetails.getUsername());
        UserEntity userEntity = optional.orElseThrow();

        List<String> roles = userMapper.findRolesByUserId(userEntity.getId());

        model.addAttribute("emailAddress", userEntity.getEmailAddress());
        model.addAttribute("roles", String.join(", ", roles));

        return "account";
    }

    @GetMapping("accountModify")
    @Transactional(readOnly = true)
    public String accountModify(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Optional<UserEntity> optional = userMapper.findUserByUsername(userDetails.getUsername());
        UserEntity userEntity = optional.orElseThrow();

        List<String> roles = userMapper.findRolesByUserId(userEntity.getId());
        model.addAttribute("roles", String.join(", ", roles));

        AccountModifyForm accountModifyForm = new AccountModifyForm();
        accountModifyForm.setUserName(userEntity.getUsername());
        accountModifyForm.setEmailAddress(userEntity.getEmailAddress());
        model.addAttribute("accountModifyForm", accountModifyForm);

        return "accountModify";
    }

    @PostMapping("accountModifySave")
    @Transactional
    public String accountModifySave(@AuthenticationPrincipal UserDetails userDetails, AccountModifyForm form) {
        logger.info("accept change account {}", form);
        return "redirect:/home/account";
    }

}
