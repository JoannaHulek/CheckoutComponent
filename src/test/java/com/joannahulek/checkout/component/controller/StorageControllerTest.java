package com.joannahulek.checkout.component.controller;

import com.joannahulek.checkout.component.CountableProduct;
import com.joannahulek.checkout.component.Product;
import com.joannahulek.checkout.component.StorageCountableProduct;
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
        List<StorageCountableProduct> createdStorage = storageController.getStorage();
        Assert.assertNotNull(createdStorage);
        Assert.assertNotEquals(new ArrayList<CountableProduct>(), createdStorage);   //storage is not empty
    }

    private List<StorageCountableProduct> products() {
        List<StorageCountableProduct> sampleProducts = new ArrayList<>();
        sampleProducts.add(new StorageCountableProduct(3, new Product("Potato", new BigDecimal("2.2"))));
        return sampleProducts;
    }
}