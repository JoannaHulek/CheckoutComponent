package com.joannahulek.checkout.component.controller;

import com.joannahulek.checkout.component.model.StorageCountableProduct;
import com.joannahulek.checkout.component.repository.StoreRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StorageController {

    private final StoreRepository storeRepository;

    StorageController(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @GetMapping("/storage")
    public List<StorageCountableProduct> getStorage() {
        List<StorageCountableProduct> storage;
        storage = storeRepository.getStorage();
        return storage;
    }
}