package com.joannahulek.checkout.component.controller;

import com.joannahulek.checkout.component.CountableProduct;
import com.joannahulek.checkout.component.repository.StoreRepository;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StorageControllerTest {

    @Test
    public void getStorage() {
        StoreRepository storeRepository = mock(StoreRepository.class);
        when(storeRepository.getStorage()).thenReturn(products());

        StorageController storageController = new StorageController(storeRepository);
        List<CountableProduct> createdStorage = storageController.getStorage();
        Assert.assertNotNull(createdStorage);
        Assert.assertNotEquals(new ArrayList<CountableProduct>(), createdStorage);   //storage is not empty
    }

    private List<CountableProduct> products() {
        List<CountableProduct> sampleProducts = new ArrayList<CountableProduct>();
        sampleProducts.add(new CountableProduct("Potato", new BigDecimal("2.2"), 3));
        return sampleProducts;
    }
}
