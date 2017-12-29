package com.joannahulek.checkout.component.repository;

import com.joannahulek.checkout.component.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ProductRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public ProductRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveProduct(Product product) {
        entityManager.persist(product);
    }
}