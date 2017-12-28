package com.joannahulek.checkout.component.repository;

import com.joannahulek.checkout.component.Basket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BasketRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public BasketRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public Basket getBasket(String id) {
        return entityManager.find(Basket.class, id);
    }

    public void createBasket(Basket createdBasket) {
        entityManager.persist(createdBasket);
    }
}
