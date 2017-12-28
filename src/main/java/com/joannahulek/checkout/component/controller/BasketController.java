package com.joannahulek.checkout.component.controller;

import com.joannahulek.checkout.component.Basket;
import com.joannahulek.checkout.component.repository.BasketRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class BasketController {

    private final BasketRepository basketRepository;

    public BasketController(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @PostMapping("/basket")
    public Basket createBasket() {
        Basket basket = new Basket(new ArrayList<>());
        basketRepository.addBasket(basket);
        return basket;
    }

    @DeleteMapping("/basket/{id}")
    public Basket closeBasket(@PathVariable("id") String id) {

        return null;
    }

}
