package com.joannahulek.checkout.component.controller;

import com.joannahulek.checkout.component.Basket;
import org.junit.Assert;
import org.junit.Test;

public class BasketControllerInterationTest extends IntegrationTestBase{
    @Test
    public void shouldCreateBasket (){
        Basket basket = template.postForObject(base + "/basket", null, Basket.class);
        Assert.assertNotNull(basket);
    }
}
