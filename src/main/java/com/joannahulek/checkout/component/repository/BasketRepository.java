package com.joannahulek.checkout.component.repository;

import com.joannahulek.checkout.component.Basket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class BasketRepository {

    private final Map<String, Basket> sampleBaskets;
    private final IdRepository idRepository;

    @Autowired
    public BasketRepository(Map<String, Basket> sampleBaskets, IdRepository idRepository) {
        this.sampleBaskets = sampleBaskets;
        this.idRepository = idRepository;
    }


    public Basket getBasket(String id) {
        return sampleBaskets.get(id);
    }

    public void addBasket(Basket createdBasket) {
        sampleBaskets.put(idRepository.createId(), createdBasket);

    }
}
