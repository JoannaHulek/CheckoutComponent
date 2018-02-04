package com.joannahulek.checkout.component.controller;

import com.joannahulek.checkout.component.model.*;
import com.joannahulek.checkout.component.repository.BasketRepository;
import com.joannahulek.checkout.component.repository.ProductRepository;
import com.joannahulek.checkout.component.service.DiscountCalculator;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;

@RestController
public class BasketController {

    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final DiscountCalculator discountCalculator;

    BasketController(BasketRepository basketRepository, ProductRepository productRepository, DiscountCalculator discountCalculator) {
        this.basketRepository = basketRepository;
        this.productRepository = productRepository;
        this.discountCalculator = discountCalculator;
    }

    @Transactional
    @PostMapping("/basket")
    public Basket saveBasket() {
        Basket basket = new Basket(new ArrayList<>());
        basketRepository.saveBasket(basket);
        return basket;
    }

    @GetMapping("/basket/{id}")
    public Basket getBasket(@PathVariable("id") String id) {
        return basketRepository.getBasket(id);
    }

    @Transactional
    @DeleteMapping("/basket/{id}")
    public Summary closeBasket(@PathVariable("id") String id) {
        Basket closedBasket = discountCalculator.calculateDiscount(id);
        Summary basketSummary = closedBasket.closeBasket();
        basketRepository.saveBasket(closedBasket);
        return basketSummary;
    }

    @Transactional
    @PostMapping("/basket/{id}")
    public Basket addToBasket(@PathVariable("id") String id,
                              @RequestBody CountableItem item) {
        String productName = item.getName();
        int amount = item.count();
        Product product = productRepository.findProduct(productName);
        CountableProduct countableProduct = new CountableProduct(product, amount);
        Basket actualBasket = basketRepository.getBasket(id);
        actualBasket.addProduct(countableProduct);
        productRepository.saveCountableProduct(countableProduct);
        basketRepository.saveBasket(actualBasket);
        return actualBasket;
    }
}