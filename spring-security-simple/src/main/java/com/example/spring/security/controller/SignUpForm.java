package com.example.spring.security.controller;

import javax.validation.constraints.NotBlank;

public class SignUpForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public SignUpForm() {
    }

    public SignUpForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
