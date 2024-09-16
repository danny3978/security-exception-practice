package com.example.demo.domain.store.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String storeName;
    private String storeAddress;
    private String storePhone;
    private Integer favoriteCount;

    @Builder
    public Store(String storeName, String storeAddress, String storeNumber, Integer favoriteCount) {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.storePhone = storeNumber;
        this.favoriteCount = favoriteCount;
    }


}
