package com.example.spring.security.controller;

import javax.validation.constraints.NotBlank;

public class AccountModifyForm {

    @NotBlank
    private String userName;

    @NotBlank
    private String emailAddress;

    public AccountModifyForm() {
    }

    public AccountModifyForm(String userName, String mailAddress) {
        this.userName = userName;
        this.emailAddress = mailAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "AccountModifyForm{" +
                "userName='" + userName + '\'' +
                ", mailAddress='" + emailAddress + '\'' +
                '}';
    }
}
