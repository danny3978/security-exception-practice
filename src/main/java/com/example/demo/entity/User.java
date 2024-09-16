package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;



    private User(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }


    public static User create(String username, String password, UserRole role) {
        return new User(username, password, role);
    }

    public static User token(String username, String password, UserRole role) {
        return new User(username, password, role);
    }
}
