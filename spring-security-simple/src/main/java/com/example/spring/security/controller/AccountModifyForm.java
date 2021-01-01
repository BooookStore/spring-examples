package com.example.spring.security.controller;

public class AccountModifyForm {

    private String userName;

    private String mailAddress;

    public AccountModifyForm() {
    }

    public AccountModifyForm(String userName, String mailAddress) {
        this.userName = userName;
        this.mailAddress = mailAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    @Override
    public String toString() {
        return "AccountModifyForm{" +
                "userName='" + userName + '\'' +
                ", mailAddress='" + mailAddress + '\'' +
                '}';
    }
}
