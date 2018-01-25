package com.joannahulek.checkout.component.service;

import com.joannahulek.checkout.component.model.Basket;
import com.joannahulek.checkout.component.model.CountableProduct;
import com.joannahulek.checkout.component.model.Product;
import com.joannahulek.checkout.component.repository.BasketRepository;
import com.joannahulek.checkout.component.repository.DiscountRepository;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

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
        Basket expectetBasket = new Basket(createCountableProducts());
        String existingId = "123";
        when(basketRepository.getBasket(existingId)).thenReturn(expectetBasket);
        Basket actualBasket = discountCalculator.calculateDiscount(existingId);
        assertThat(actualBasket).isSameAs(expectetBasket);
    }

    @Test
    public void calculateDiscountWhenIsOneProductInPromotion() {
        Basket basketWithDiscount = new Basket(createCountableProducts());
        String existingId = "123";
        when(basketRepository.getBasket(existingId)).thenReturn(basketWithDiscount);
        Basket actualBasket = discountCalculator.calculateDiscount(existingId);
        assertThat(actualBasket.getProducts().get(0).getProduct().getMultiplayer()).isNotEqualTo(BigDecimal.ONE);
    }

    private List<CountableProduct> createCountableProducts() {
        return Arrays.asList(new CountableProduct(new Product("Tomato", new BigDecimal("4.2")), 20));
    }
}