package com.joannahulek.checkout.component.service;

import com.joannahulek.checkout.component.model.Basket;
import com.joannahulek.checkout.component.model.CountableProduct;
import com.joannahulek.checkout.component.model.Discount;
import com.joannahulek.checkout.component.model.ProductInPromotion;
import com.joannahulek.checkout.component.repository.BasketRepository;
import com.joannahulek.checkout.component.repository.DiscountRepository;

import java.math.BigDecimal;
import java.util.Comparator;
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
        List<CountableProduct> currentProducts = currentBasket.getProducts();
        List<Discount> discounts = discountRepository.getAll();

        currentProducts
                .forEach(countableProduct ->
                        countableProduct.setDiscountMultiplier(discounts
                                .stream()
                                .filter(discount ->
                                        discount.getProductsInPromotion()
                                                .stream()
                                                .allMatch(productInPromotion ->
                                                        currentProducts
                                                                .stream()
                                                                .anyMatch(cp -> cp.compareToProductInPromotion(productInPromotion))))
                                .flatMap(discount -> discount.getProductsInPromotion().stream())
                                .filter(countableProduct::compareToProductInPromotion)
                                .max(Comparator.comparing(ProductInPromotion::getDiscountValue))
                                .map(ProductInPromotion::getDiscountValue)
                                .orElse(BigDecimal.ONE)));
        return currentBasket;
    }
}