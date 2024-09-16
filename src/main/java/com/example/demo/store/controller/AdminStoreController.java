package com.example.demo.store.controller;


import com.example.demo.store.dto.request.CreateStoreRequestDto;
import com.example.demo.store.dto.response.CreateStoreResponseDto;
import com.example.demo.store.service.AdminStoreService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminStoreController {

    private final AdminStoreService adminStoreService;

    @PostMapping("/stores")
    public ResponseEntity<CreateStoreResponseDto> createStore(@RequestBody CreateStoreRequestDto request) {

        // http 관련 머시깽이 저렇게 해놓으면 되나?? 모르겠네 일단 보류

        CreateStoreResponseDto responseDto = adminStoreService.createStore(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }


}
