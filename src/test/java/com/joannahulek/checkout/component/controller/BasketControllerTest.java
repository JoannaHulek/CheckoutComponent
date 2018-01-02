package com.joannahulek.checkout.component.controller;

import com.joannahulek.checkout.component.*;
import com.joannahulek.checkout.component.repository.BasketRepository;
import com.joannahulek.checkout.component.repository.ProductRepository;
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
    private BasketController basketController;

    @Before
    public void setup() {
        basketRepository = mock(BasketRepository.class);
        productRepository = mock(ProductRepository.class);
        basketController = new BasketController(basketRepository, productRepository);
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
        productsList.add(new CountableProduct(new Product("Apple", new BigDecimal("2.9")), 3));
        productsList.add(new CountableProduct(new Product("Banana", new BigDecimal("3.2")), 2));
        Mockito.when(basketRepository.getBasket(eq("123"))).thenReturn(new Basket(productsList));
        BigDecimal actualPrice = basketController.closeBasket("123").getTotalPrice();
        Assert.assertEquals(new BigDecimal("15.1"), actualPrice);
    }

    @Test
    public void summarizeBasketAmount() {
        List<CountableProduct> productsList = new ArrayList<>();
        productsList.add(new CountableProduct(new Product("Apple", new BigDecimal("2.8")), 5));
        productsList.add(new CountableProduct(new Product("Banana", new BigDecimal("3.4")), 1));
        Mockito.when(basketRepository.getBasket(eq("123"))).thenReturn(new Basket(productsList));
        int actualAmount = basketController.closeBasket("123").getNumberOfItems();
        Assert.assertEquals(6, actualAmount);
    }

    @Test
    public void addToBasket() {
        Mockito.when(basketRepository.getBasket(eq("123"))).thenReturn(new Basket(new ArrayList<>()));
        Mockito.when(productRepository.findProduct("Orange")).thenReturn(new Product("Orange", new BigDecimal("3.8")));
        String basketId = "123";
        CountableItem item = new CountableItem("Orange", 1);
        CountableProduct product = new CountableProduct(new Product("Orange", new BigDecimal("3.8")), 1);
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