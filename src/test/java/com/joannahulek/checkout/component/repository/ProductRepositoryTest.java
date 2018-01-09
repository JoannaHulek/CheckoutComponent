package com.joannahulek.checkout.component.repository;

import com.joannahulek.checkout.component.model.CountableProduct;
import com.joannahulek.checkout.component.model.Product;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ProductRepositoryTest {

    private EntityManager entityManager;
    private ProductRepository productRepository;

    @Before
    public void setup() {
        entityManager = mock(EntityManager.class);
        productRepository = new ProductRepository(entityManager);
    }

    @Test
    public void saveProduct() {
        Product product = new Product("Tomato", new BigDecimal("4.2"));
        productRepository.saveProduct(product);
        verify(entityManager).merge(product);
    }

    @Test
    public void findProduct() {
        productRepository.findProduct("Test");
        verify(entityManager).find(Product.class, "Test");
    }

    @Test
    public void saveCountableProduct() {
        CountableProduct countableProduct = new CountableProduct();
        productRepository.saveCountableProduct(countableProduct);
        verify(entityManager).persist(countableProduct);
    }
}