package com.joannahulek.checkout.component.controller;

import com.joannahulek.checkout.component.Basket;
import com.joannahulek.checkout.component.CountableItem;
import com.joannahulek.checkout.component.CountableProduct;
import com.joannahulek.checkout.component.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class BasketControllerInterationTest extends IntegrationTestBase {

    @Test
    public void shouldCreateBasket() {
        Basket basket = template.postForObject(base + "/basket", null, Basket.class);
        Assert.assertNotNull(basket);
    }

    @Test
    public void shouldCloseBasket() {
        Basket basket = template.postForObject(base + "/basket", null, Basket.class);
        String id = basket.getId();
        template.delete(base + "/basket/" + id);
        Basket closedBasket = template.getForObject(base + "/basket/" + id, Basket.class);
        Assert.assertFalse(closedBasket.isActive());
    }

    @Test
    @Transactional
    public void shouldAddToBasket() {
        Basket basket = template.postForObject(base + "/basket", null, Basket.class);
        String id = basket.getId();
        CountableProduct product = new CountableProduct(new Product("Milk", new BigDecimal("2.3")), 1);
        CountableItem item = new CountableItem("Milk", 1);
        Basket actualBasket = template.postForObject(base + "/basket/" + id, item, Basket.class);
        Basket actualDbBasket = template.getForObject(base + "/basket/" + id, Basket.class);
        List<CountableProduct> actualDbProducts = actualDbBasket.getProducts();
        List<CountableProduct> actualProducts = actualBasket.getProducts();
        List<CountableProduct> expectedProducts = new ArrayList<>();
        expectedProducts.add(product);

        Assert.assertEquals(expectedProducts, actualProducts);
        Assert.assertEquals(expectedProducts, actualDbProducts);
    }
}
