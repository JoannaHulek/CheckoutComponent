package com.joannahulek.checkout.component.repository;

import com.joannahulek.checkout.component.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class ProductRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public ProductRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void saveProduct(Product product) {
        entityManager.merge(product);
    }

    public Product findProduct(String name) {
        return entityManager.find(Product.class, name);
    }
}