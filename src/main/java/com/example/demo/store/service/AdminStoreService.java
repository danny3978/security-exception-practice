package com.example.demo.store.service;


import com.example.demo.store.dto.request.CreateStoreRequestDto;
import com.example.demo.store.dto.response.CreateStoreResponseDto;
import com.example.demo.store.entity.Store;
import com.example.demo.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminStoreService {

    private final StoreRepository storeRepository;

    @Transactional
    public CreateStoreResponseDto createStore(CreateStoreRequestDto createStoreRequestDto) {

        storeCheck(createStoreRequestDto);

        Store store = Store.builder()
                .storeAddress(createStoreRequestDto.getStoreAddress())
                .storeNumber(createStoreRequestDto.getStoreNumber())
                .storeName(createStoreRequestDto.getStoreName())
                .favoriteCount(0)
                .build();


        store = storeRepository.save(store);

        return CreateStoreResponseDto.createStore(store.getId(), store.getStoreName(), store.getStoreAddress(), store.getStorePhone());
    }



    public void storeCheck(CreateStoreRequestDto createStoreRequestDto) {
        if(createStoreRequestDto.getStoreAddress() == null || createStoreRequestDto.getStoreNumber() == null ||
                createStoreRequestDto.getStoreName() == null){
            throw new IllegalArgumentException("가게 주소와 번호, 이름이 없으면 안 됩니다.");
        }
    }

}
