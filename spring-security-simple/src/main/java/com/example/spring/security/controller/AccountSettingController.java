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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

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
        UserEntity userEntity = getUserEntityOrElseThrow(userDetails);

        List<String> roles = userMapper.findRolesByUserId(userEntity.getId());

        model.addAttribute("emailAddress", userEntity.getEmailAddress());
        model.addAttribute("roles", String.join(", ", roles));

        return "account";
    }

    @GetMapping("accountModify")
    @Transactional(readOnly = true)
    public String accountModify(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        UserEntity userEntity = getUserEntityOrElseThrow(userDetails);

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
    public String accountModifySave(@AuthenticationPrincipal UserDetails userDetails, @Valid AccountModifyForm form, BindingResult result, Model model) {
        logger.info("accept change account {}", form);

        if (result.hasErrors()) {
            logger.info("has invalidate form {}", form);
            List<String> roles = userMapper.findRolesByUserId(getUserEntityOrElseThrow(userDetails).getId());
            model.addAttribute("roles", String.join(", ", roles));
            return "accountModify";
        }

        return "redirect:/home/account";
    }

    private UserEntity getUserEntityOrElseThrow(UserDetails userDetails) {
        return userMapper.findUserByUsername(userDetails.getUsername()).orElseThrow();
    }

}
