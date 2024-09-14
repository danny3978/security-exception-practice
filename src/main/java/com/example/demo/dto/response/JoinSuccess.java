package com.example.demo.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class JoinSuccess {

    private Long user_id;
    private String message;
    private int status;

    public JoinSuccess(Long user_id, String message, int status) {
        this.user_id = user_id;
        this.message = message;
        this.status = status;
    }
}
