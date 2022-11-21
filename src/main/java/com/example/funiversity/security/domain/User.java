package com.example.funiversity.security.domain;

import com.example.funiversity.security.domain.Role;

public class User {
    private String name;
    private String password;
    private Role role;

    public User(String name, String password, Role role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public boolean canHaveAccessTo(Feature feature) {
        return role.containsFeature(feature);
    }

    public boolean doesPasswordMatch(String password) {
        return this.password.equals(password);
    }
}
