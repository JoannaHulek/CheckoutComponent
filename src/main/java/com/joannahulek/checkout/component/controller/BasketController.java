package com.joannahulek.checkout.component.controller;

import com.joannahulek.checkout.component.Basket;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class BasketController {

    @PostMapping("/basket")
    public Basket createBasket() {
        Basket basket = new Basket(new ArrayList<>());
        return basket;
    }
}
