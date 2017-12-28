package com.joannahulek.checkout.component.controller;

import com.joannahulek.checkout.component.Basket;
import com.joannahulek.checkout.component.CountableProduct;
import com.joannahulek.checkout.component.Summary;
import com.joannahulek.checkout.component.repository.BasketRepository;
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

public class BasketControllerTest {

    private BasketRepository basketRepository;
    private BasketController basketController;

    @Before
    public void setup() {
        basketRepository = mock(BasketRepository.class);
        basketController = new BasketController(basketRepository);
    }


    @Test
    public void createBasket() {

        Basket createdBasket = basketController.createBasket();
        assertNotNull(createdBasket);
        Mockito.verify(basketRepository).createBasket(createdBasket);
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
        List<CountableProduct> productsList = new ArrayList<CountableProduct>();
        productsList.add(new CountableProduct("Apple", new BigDecimal("2.9"), 3));
        productsList.add(new CountableProduct("Banana", new BigDecimal("3.2"), 2));
        Mockito.when(basketRepository.getBasket(eq("123"))).thenReturn(new Basket(productsList));
        BigDecimal actualPrice = basketController.closeBasket("123").getTotalPrice();
        Assert.assertEquals(new BigDecimal("15.1"), actualPrice);
    }

    @Test
    public void summarizeBasketAmount() {
        List<CountableProduct> productsList = new ArrayList<CountableProduct>();
        productsList.add(new CountableProduct("Apple", new BigDecimal("2.8"), 5));
        productsList.add(new CountableProduct("Banana", new BigDecimal("3.4"), 1));
        Mockito.when(basketRepository.getBasket(eq("123"))).thenReturn(new Basket(productsList));
        int actualAmount = basketController.closeBasket("123").getNumberOfItems();
        Assert.assertEquals(6, actualAmount);
    }


}
