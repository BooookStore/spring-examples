package com.example.spring.security.controller;

import com.example.spring.security.security.UserEntity;
import com.example.spring.security.security.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/home/account")
@Transactional
public class AccountSettingController {

    private final Logger logger = LoggerFactory.getLogger(AccountSettingController.class.getName());

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public AccountSettingController(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    @Transactional(readOnly = true)
    public String account(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(required = false) String accountChanged, Model model) {
        if (accountChanged != null) model.addAttribute("accountChanged", true);

        UserEntity userEntity = getUserEntityOrElseThrow(userDetails.getUsername());
        List<String> roles = userMapper.findRolesByUserId(userEntity.getId());
        model.addAttribute("roles", String.join(", ", roles));
        model.addAttribute("emailAddress", userEntity.getEmailAddress());

        return "account";
    }

    @GetMapping("accountModify")
    @Transactional(readOnly = true)
    public String accountModify(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        UserEntity userEntity = getUserEntityOrElseThrow(userDetails.getUsername());

        // ロールを表示
        List<String> roles = userMapper.findRolesByUserId(userEntity.getId());
        model.addAttribute("roles", String.join(", ", roles));

        // 既存のアカウント設定を表示
        AccountModifyForm accountModifyForm = new AccountModifyForm();
        accountModifyForm.setUserName(userEntity.getUsername());
        accountModifyForm.setEmailAddress(userEntity.getEmailAddress());
        model.addAttribute("accountModifyForm", accountModifyForm);

        return "accountModify";
    }

    @PostMapping("accountModifySave")
    public String accountModifySave(@AuthenticationPrincipal UserDetails userDetails, @Valid AccountModifyForm form, BindingResult result, Model model) {
        logger.info("accept change account {}", form);

        if (result.hasErrors()) {
            logger.info("has invalidate form {}", form);

            // ロールを表示
            List<String> roles = userMapper.findRolesByUserId(getUserEntityOrElseThrow(userDetails.getUsername()).getId());
            model.addAttribute("roles", String.join(", ", roles));

            return "accountModify";
        }

        // DBを更新
        UserEntity userEntity = getUserEntityOrElseThrow(userDetails.getUsername());
        userEntity.setUsername(form.getUserName());
        userEntity.setEmailAddress(form.getEmailAddress());
        userMapper.updateUser(userEntity);

        reflectAccountModifyToSecurityContext(userEntity);

        return "redirect:/home/account?accountChanged";
    }

    @GetMapping("password")
    public String password(@RequestParam(required = false) String passwordChanged, Model model) {
        if (passwordChanged != null) model.addAttribute("passwordChanged", true);
        return "accountPassword";
    }

    @GetMapping("passwordModify")
    public String passwordModify(Model model) {
        model.addAttribute("passwordModifyForm", new PasswordModifyForm());
        return "accountPasswordModify";
    }

    @PostMapping("passwordModifySave")
    public String passwordModifySave(@AuthenticationPrincipal UserDetails userDetails, @Valid PasswordModifyForm passwordModifyForm, BindingResult result) {
        logger.info("accept change password {}", userDetails.getUsername());

        if (result.hasErrors()) {
            logger.info("has invalidate form {}", userDetails.getUsername());

            return "accountPasswordModify";
        }

        // 保存されているパスワードと一致しているか検証
        UserEntity userEntity = getUserEntityOrElseThrow(userDetails.getUsername());
        boolean matchPassword = passwordEncoder.matches(passwordModifyForm.getCurrentPassword(), userEntity.getPassword());
        if (!matchPassword) return "accountPasswordModify";

        // 新しいパスワードを保存
        userEntity.setPassword(passwordEncoder.encode(passwordModifyForm.getNewPassword()));
        userMapper.updateUser(userEntity);

        return "redirect:/home/account/password?passwordChanged";
    }

    private void reflectAccountModifyToSecurityContext(UserEntity userEntity) {
        UserDetails user = User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .roles(userMapper.findRolesByUserId(userEntity.getId()).toArray(String[]::new))
                .build();
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()));

        logger.info("security context updated to {}", user);
    }

    private UserEntity getUserEntityOrElseThrow(String username) {
        return userMapper.findUserByUsername(username).orElseThrow();
    }

}
