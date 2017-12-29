package com.joannahulek.checkout.component.controller;

import com.joannahulek.checkout.component.Basket;
import com.joannahulek.checkout.component.CountableProduct;
import com.joannahulek.checkout.component.Summary;
import com.joannahulek.checkout.component.repository.BasketRepository;
import org.springframework.web.bind.annotation.*;

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

    @Transactional
    @DeleteMapping("/basket/{id}")
    public Summary closeBasket(@PathVariable("id") String id) {
        Basket closedBasket = basketRepository.getBasket(id);
        Summary basketSummary = closedBasket.closeBasket();
        basketRepository.createBasket(closedBasket);
        return basketSummary;
    }

    @Transactional
    @PostMapping("/basket/{id}")
    public Basket addToBasket(@PathVariable("id") String id,
                              @RequestBody CountableProduct product) {
        Basket actualBasket = basketRepository.getBasket(id);
        actualBasket.addProduct(product);
        basketRepository.createBasket(actualBasket);
        return actualBasket;
    }
}
