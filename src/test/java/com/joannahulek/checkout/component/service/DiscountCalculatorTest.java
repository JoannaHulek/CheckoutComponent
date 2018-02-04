package com.joannahulek.checkout.component.service;

import com.joannahulek.checkout.component.ProductPrototypes;
import com.joannahulek.checkout.component.model.Basket;
import com.joannahulek.checkout.component.model.CountableProduct;
import com.joannahulek.checkout.component.model.Discount;
import com.joannahulek.checkout.component.repository.BasketRepository;
import com.joannahulek.checkout.component.repository.DiscountRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DiscountCalculatorTest {

    private DiscountCalculator discountCalculator;
    private BasketRepository basketRepository;
    private DiscountRepository discountRepository;


    @Before
    public void setUp() {
        basketRepository = mock(BasketRepository.class);
        discountRepository = mock(DiscountRepository.class);
        discountCalculator = new DiscountCalculator(basketRepository, discountRepository);
    }

    @Test
    public void calculateDiscountWhenNoPromotionFound() {
        Basket expectedBasket = new Basket(Collections.singletonList(
                ProductPrototypes.createCountableProduct(
                        "First", new BigDecimal("4.2"), 20)));
        String existingId = "123";
        when(basketRepository.getBasket(existingId)).thenReturn(expectedBasket);
        Basket actualBasket;
        actualBasket = discountCalculator.calculateDiscount(existingId);
        assertThat(actualBasket).isSameAs(expectedBasket);
    }

    @Test
    public void calculateDiscountWhenIsOneProductInPromotion() {
        Basket basketWithOneDiscount = new Basket(Collections.singletonList(
                ProductPrototypes.createCountableProduct(
                        "First", new BigDecimal("4.2"), 20)));
        String existingId = "123";
        when(basketRepository.getBasket(existingId)).thenReturn(basketWithOneDiscount);
        when(discountRepository.getAll()).thenReturn(
                Collections.singletonList(new Discount(
                        Collections.singletonList(ProductPrototypes.createProductInPromotion(
                                "First", new BigDecimal("4.2"), 20, new BigDecimal("0.5"))))));
        Basket actualBasket = discountCalculator.calculateDiscount(existingId);
        Assertions.assertThat(actualBasket.getProducts().get(0).getDiscountMultiplier())
                .isEqualTo(BigDecimal.valueOf(0.5d));
    }

    @Test
    public void calculateDiscountWhenIsManyProductsInPromotionInOneDiscount() {
        Basket basketWithManyProductsInOneDiscount = new Basket(Arrays.asList(
                ProductPrototypes.createCountableProduct(
                        "First", new BigDecimal("4.2"), 20),
                ProductPrototypes.createCountableProduct(
                        "Second", new BigDecimal("6.35"), 10)));
        String existingID = "15";
        when(basketRepository.getBasket(existingID)).thenReturn(basketWithManyProductsInOneDiscount);
        when(discountRepository.getAll()).thenReturn(
                Collections.singletonList(new Discount(
                        Arrays.asList
                                (ProductPrototypes.createProductInPromotion(
                                        "First", new BigDecimal("4.2"), 20, new BigDecimal("0.5")),
                                        ProductPrototypes.createProductInPromotion(
                                                "Second", new BigDecimal("6.35"), 10, new BigDecimal("0.1"))))));
        Basket actualBasket = discountCalculator.calculateDiscount(existingID);
        Assertions.assertThat(actualBasket.getProducts())
                .extracting(CountableProduct::getDiscountMultiplier)
                .containsExactly(BigDecimal.valueOf(0.5d), BigDecimal.valueOf(0.1d));
    }

    @Test
    public void calculateDiscountWhenIsManyDiscounts() {
        Basket basketWithManyDiscounts = new Basket(Arrays.asList(
                ProductPrototypes.createCountableProduct(
                        "First", new BigDecimal("4.2"), 20),
                ProductPrototypes.createCountableProduct(
                        "Second", new BigDecimal("6.35"), 10)));
        String existingID = "321";
        when(basketRepository.getBasket(existingID)).thenReturn(basketWithManyDiscounts);
        when(discountRepository.getAll()).thenReturn(
                Arrays.asList(
                        new Discount(
                                Collections.singletonList(
                                        ProductPrototypes.createProductInPromotion(
                                                "First", new BigDecimal("4.2"), 20, new BigDecimal("0.5")))),
                        new Discount(
                                Collections.singletonList(
                                        ProductPrototypes.createProductInPromotion(
                                                "Second", new BigDecimal("6.35"), 10, new BigDecimal("0.1"))))));
        Basket actualBasket = discountCalculator.calculateDiscount(existingID);
        Assertions.assertThat(actualBasket.getProducts())
                .extracting(CountableProduct::getDiscountMultiplier)
                .containsExactly(BigDecimal.valueOf(0.5d), BigDecimal.valueOf(0.1d));
    }

    @Test
    public void chooseBetterPromotion() {
        Basket basketWithProductCoveredByManyPromotions = new Basket(
                Collections.singletonList(ProductPrototypes.createCountableProduct(
                        "First", new BigDecimal("4.2"), 20)));
        String existingID = "159";
        when(basketRepository.getBasket(existingID)).thenReturn(basketWithProductCoveredByManyPromotions);
        when(discountRepository.getAll()).thenReturn(
                Arrays.asList(
                        new Discount(
                                Collections.singletonList(ProductPrototypes.createProductInPromotion(
                                        "First", new BigDecimal("4.2"), 20, new BigDecimal("0.5")))),
                        new Discount(
                                Collections.singletonList(ProductPrototypes.createProductInPromotion(
                                        "First", new BigDecimal("4.2"), 20, new BigDecimal("0.8"))))));
        Basket actualBasket = discountCalculator.calculateDiscount(existingID);
        Assertions.assertThat(actualBasket.getProducts())
                .extracting(CountableProduct::getDiscountMultiplier)
                .containsExactly(BigDecimal.valueOf(0.8d));
    }
}