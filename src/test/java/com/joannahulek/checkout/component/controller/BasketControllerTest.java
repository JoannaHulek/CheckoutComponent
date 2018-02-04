package com.joannahulek.checkout.component.controller;

import com.joannahulek.checkout.component.ProductPrototypes;
import com.joannahulek.checkout.component.model.*;
import com.joannahulek.checkout.component.repository.BasketRepository;
import com.joannahulek.checkout.component.repository.ProductRepository;
import com.joannahulek.checkout.component.service.DiscountCalculator;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class BasketControllerTest {

    private BasketRepository basketRepository;
    private ProductRepository productRepository;
    private DiscountCalculator discountCalculator;
    private BasketController basketController;

    @Before
    public void setup() {
        basketRepository = mock(BasketRepository.class);
        productRepository = mock(ProductRepository.class);
        discountCalculator = mock(DiscountCalculator.class);
        basketController = new BasketController(basketRepository, productRepository, discountCalculator);
    }

    @Test
    public void saveBasket() {
        Basket createdBasket = basketController.saveBasket();
        assertNotNull(createdBasket);
        Mockito.verify(basketRepository, times(1)).saveBasket(createdBasket);
    }

    @Test
    public void closeBasket() {
        Mockito.when(basketRepository.getBasket(eq("123"))).thenReturn(new Basket(Collections.emptyList()));
        String existingBasketID = "123";
        Summary basketSummary = basketController.closeBasket(existingBasketID);
        assertNotNull(basketSummary);
    }

    @Test
    public void summarizeBasketPrice() {
        List<CountableProduct> productsList = new ArrayList<>();
        productsList.add(ProductPrototypes.createCountableProduct(
                "Apple", new BigDecimal("2.9"), 3));
        productsList.add(ProductPrototypes.createCountableProduct(
                "Banana", new BigDecimal("3.2"), 2));
        String existingID = "11";
        Mockito.when(basketRepository.getBasket(eq(existingID))).thenReturn(new Basket(productsList));
        BigDecimal actualPrice = basketController.closeBasket(existingID).getTotalPrice();
        Assert.assertEquals(new BigDecimal("15.1"), actualPrice);
    }

    @Test
    public void summarizeBasketPriceWithDiscounts() {
        List<CountableProduct> productsList = new ArrayList<>();
        CountableProduct product = ProductPrototypes.createCountableProduct(
                "Apple", new BigDecimal("2.9"), 5);
        product.setDiscountMultiplier(new BigDecimal("0.2"));
        productsList.add(product);
        String existingID = "145";
        Mockito.when(discountCalculator.calculateDiscount(existingID)).thenReturn(new Basket(productsList));
        BigDecimal actualPrice = basketController.closeBasket(existingID).getTotalPrice();
        Assertions.assertThat(actualPrice).isEqualByComparingTo("11.6");
    }

    @Test
    public void summarizeBasketAmount() {
        List<CountableProduct> productsList = new ArrayList<>();
        productsList.add(ProductPrototypes.createCountableProduct(
                "Apple", new BigDecimal("2.8"), 5));
        productsList.add(ProductPrototypes.createCountableProduct(
                "Banana", new BigDecimal("3.4"), 1));
        Mockito.when(basketRepository.getBasket(eq("123"))).thenReturn(new Basket(productsList));
        int actualAmount = basketController.closeBasket("123").getNumberOfItems();
        Assert.assertEquals(6, actualAmount);
    }

    @Test
    public void addToBasket() {
        Mockito.when(basketRepository.getBasket(eq("123")))
                .thenReturn(new Basket(new ArrayList<>()));
        Mockito.when(productRepository.findProduct("Orange"))
                .thenReturn(new Product("Orange", new BigDecimal("3.8")));
        String basketId = "123";
        CountableItem item = new CountableItem("Orange", 1);
        CountableProduct product = ProductPrototypes.createCountableProduct(
                "Orange", new BigDecimal("3.8"), 1);
        Basket actualBasket = basketController.addToBasket(basketId, item);
        Assert.assertEquals(new Basket(Collections.singletonList(product)), actualBasket);
    }

    @Test
    public void getBasket() {
        Basket expectedBasket = new Basket();
        Mockito.when(basketRepository.getBasket(eq("123"))).thenReturn(expectedBasket);
        Basket actualBasket = basketController.getBasket("123");
        Mockito.verify(basketRepository).getBasket("123");
        Assert.assertEquals(expectedBasket, actualBasket);
    }
}