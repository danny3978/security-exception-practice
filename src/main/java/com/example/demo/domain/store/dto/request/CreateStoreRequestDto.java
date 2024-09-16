package com.example.demo.domain.store.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateStoreRequestDto {

    private String storeName;
    private String storeAddress;
    private String storeNumber;

}

