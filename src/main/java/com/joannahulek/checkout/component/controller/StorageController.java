package com.joannahulek.checkout.component.controller;

import com.joannahulek.checkout.component.CountableProduct;
import com.joannahulek.checkout.component.repository.StoreRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StorageController {

    private final StoreRepository storeRepository;

    public StorageController(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @GetMapping("/storage")
    public List<CountableProduct> getStorage() {
        List<CountableProduct> storage = new ArrayList<CountableProduct>();
        storage=storeRepository.getStorage();
        return storage;
    }
}
