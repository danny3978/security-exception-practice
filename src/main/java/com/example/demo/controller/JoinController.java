package com.example.demo.controller;


import com.example.demo.dto.request.JoinDto;
import com.example.demo.dto.response.JoinSuccess;
import com.example.demo.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;


@RestController
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @PostMapping(value = "/join", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JoinSuccess> joinProcess(@RequestBody JoinDto joinDto) {
        JoinSuccess joinSuccess = joinService.joinProcess(joinDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(joinSuccess);
    }
}
