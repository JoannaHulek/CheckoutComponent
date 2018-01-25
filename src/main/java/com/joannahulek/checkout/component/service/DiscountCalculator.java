package com.joannahulek.checkout.component.service;

import com.joannahulek.checkout.component.model.Basket;
import com.joannahulek.checkout.component.model.CountableProduct;
import com.joannahulek.checkout.component.repository.BasketRepository;
import com.joannahulek.checkout.component.repository.DiscountRepository;

import java.util.List;

public class DiscountCalculator {
    private final BasketRepository basketRepository;
    private final DiscountRepository discountRepository;

    public DiscountCalculator(BasketRepository basketRepository, DiscountRepository discountRepository) {
        this.basketRepository = basketRepository;
        this.discountRepository = discountRepository;
    }

    public Basket calculateDiscount(String basketId) {
        Basket currentBasket = basketRepository.getBasket(basketId);
        List<CountableProduct> products = currentBasket.getProducts();

        return currentBasket;
    }
}
