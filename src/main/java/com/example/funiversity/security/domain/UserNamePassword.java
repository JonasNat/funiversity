package com.example.funiversity.security.domain;

public class UserNamePassword {
    private final String userName;
    private final String password;

    public UserNamePassword(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
