package com.example.spring.security.model;

import java.util.List;

public class User {

    private int id;

    private String username;

    private String emailAddress;

    private List<String> roles;

    public User(int id, String username, String emailAddress, List<String> roles) {
        this.id = id;
        this.username = username;
        this.emailAddress = emailAddress;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", roles=" + roles +
                '}';
    }
}
