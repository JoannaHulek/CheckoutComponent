package com.joannahulek.checkout.component.controller;

import com.joannahulek.checkout.component.Basket;
import org.junit.Test;

import static org.junit.Assert.*;

public class BasketControllerTest {

    @Test
    public void createBasket() {
        BasketController basketControler = new BasketController();
        Basket createdBasket = basketControler.createBasket();
        assertNotNull(createdBasket);
    }
}
