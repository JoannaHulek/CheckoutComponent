package com.joannahulek.checkout.component.controller;

import com.joannahulek.checkout.component.Basket;
import com.joannahulek.checkout.component.repository.BasketRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;

public class BasketControllerTest {

    private BasketRepository basketRepository;

    @Before
    public void setup() {
        basketRepository = mock(BasketRepository.class);
    }


    @Test
    public void createBasket() {
        BasketController basketControler = new BasketController(basketRepository);
        Basket createdBasket = basketControler.createBasket();
        assertNotNull(createdBasket);
        Mockito.verify(basketRepository).addBasket(createdBasket);
    }

    @Test
    public void closeBasket() {
        Mockito.when(basketRepository.getBasket(eq("123"))).thenReturn(new Basket(Collections.emptyList()));
        String existingBasketID = "123";
        BasketController basketController = new BasketController(basketRepository);
        Basket createdBasket = basketController.closeBasket(existingBasketID);
        assertNotNull(createdBasket);
    }
}
