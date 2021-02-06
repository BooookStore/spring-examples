package com.example.spring.security.repository.mapper;

public class UserTable {

    private int id;

    private String username;

    private String emailAddress;

    public UserTable(int id, String username, String emailAddress) {
        this.id = id;
        this.username = username;
        this.emailAddress = emailAddress;
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

}
