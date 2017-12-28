package com.joannahulek.checkout.component.controller;

import com.joannahulek.checkout.component.Basket;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class BasketControllerInterationTest extends IntegrationTestBase{
    @Test
    public void shouldCreateBasket (){
        Basket basket = template.postForObject(base + "/basket", null, Basket.class);
        Assert.assertNotNull(basket);
    }
}
