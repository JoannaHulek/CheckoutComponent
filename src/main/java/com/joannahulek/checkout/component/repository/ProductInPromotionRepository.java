package com.joannahulek.checkout.component.repository;

import com.joannahulek.checkout.component.model.ProductInPromotion;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class ProductInPromotionRepository {

    @PersistenceContext
    private final EntityManager entityManager;


    ProductInPromotionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void saveProductInPromotion(ProductInPromotion productInPromotion) {
        entityManager.merge(productInPromotion);
    }
}