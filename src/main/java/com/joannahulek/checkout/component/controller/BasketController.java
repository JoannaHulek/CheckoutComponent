package com.joannahulek.checkout.component.controller;

import com.joannahulek.checkout.component.Basket;
import com.joannahulek.checkout.component.Summary;
import com.joannahulek.checkout.component.repository.BasketRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.ArrayList;

@RestController
public class BasketController {

    private final BasketRepository basketRepository;

    public BasketController(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Transactional
    @PostMapping("/basket")
    public Basket createBasket() {
        Basket basket = new Basket(new ArrayList<>());
        basketRepository.createBasket(basket);
        return basket;
    }

    @DeleteMapping("/basket/{id}")
    public Summary closeBasket(@PathVariable("id") String id) {
        Basket closedBasket = basketRepository.getBasket(id);
        Summary basketSummary = closedBasket.closeBasket();
        return basketSummary;
    }
}
