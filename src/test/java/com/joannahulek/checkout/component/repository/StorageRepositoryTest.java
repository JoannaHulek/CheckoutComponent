package com.joannahulek.checkout.component.repository;

import com.joannahulek.checkout.component.Product;
import com.joannahulek.checkout.component.StorageCountableProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class StorageRepositoryTest {

    @InjectMocks
    StoreRepository storeRepository;
    @Mock
    private EntityManager entityManager;

    @Test
    public void saveStorage() {
        StorageCountableProduct storageCountableProduct = new StorageCountableProduct(5, new Product("Milk", new BigDecimal("2.3")));
        storeRepository.saveStorage(storageCountableProduct);
        Mockito.verify(entityManager).merge(storageCountableProduct);
    }
}