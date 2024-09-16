package com.example.demo.entity;

public enum UserRole {
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");


    String role;

    UserRole(String role) {
        this.role = role;
    }
}
