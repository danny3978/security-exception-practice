package com.example.demo.dto.request;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class JoinDto {

    private String email;
    private String password;
    private String nickname;
    private String name;
}
