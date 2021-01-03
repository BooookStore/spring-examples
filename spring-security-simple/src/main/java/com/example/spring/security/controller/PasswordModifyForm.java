package com.example.spring.security.controller;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;

public class PasswordModifyForm {

    @NotBlank
    private String currentPassword;

    @NotBlank
    private String newPassword;

    @NotBlank
    private String newPasswordCheck;

    public PasswordModifyForm() {
    }

    public PasswordModifyForm(String currentPassword, String newPassword, String newPasswordCheck) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.newPasswordCheck = newPasswordCheck;
    }

    @AssertTrue
    public boolean isNewPasswordSame() {
        if (newPassword == null) return true;
        if (newPasswordCheck == null) return true;

        return newPassword.equals(newPasswordCheck);
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordCheck() {
        return newPasswordCheck;
    }

    public void setNewPasswordCheck(String newPasswordCheck) {
        this.newPasswordCheck = newPasswordCheck;
    }

}
