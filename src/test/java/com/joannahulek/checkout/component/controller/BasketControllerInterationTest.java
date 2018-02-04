package com.joannahulek.checkout.component.controller;

import com.joannahulek.checkout.component.ProductPrototypes;
import com.joannahulek.checkout.component.model.Basket;
import com.joannahulek.checkout.component.model.CountableItem;
import com.joannahulek.checkout.component.model.CountableProduct;
import com.joannahulek.checkout.component.model.Summary;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class BasketControllerInterationTest extends IntegrationTestBase {

    @Test
    public void shouldCreateBasket() {
        Basket basket = postForNewBasket();
        Assert.assertNotNull(basket);
    }

    @Test
    public void shouldCloseBasket() {
        Basket basket = postForNewBasket();
        String id = basket.getId();
        template.delete(base + "/basket/" + id);
        Basket closedBasket = postForBasketInDb(id);
        Assert.assertFalse(closedBasket.isActive());
    }

    @Test
    @Transactional
    public void shouldAddToBasket() {
        Basket basket = postForNewBasket();
        String id = basket.getId();
        CountableProduct product = ProductPrototypes.createCountableProduct(
                "Milk", new BigDecimal("2.3"), 1);
        CountableItem item = new CountableItem("Milk", 1);
        Basket actualBasket = postForExistingBasketWithItem(id, item);
        Basket actualDbBasket = postForBasketInDb(id);
        List<CountableProduct> actualDbProducts = actualDbBasket.getProducts();
        List<CountableProduct> actualProducts = actualBasket.getProducts();
        List<CountableProduct> expectedProducts = new ArrayList<>();
        expectedProducts.add(product);

        Assert.assertEquals(expectedProducts, actualProducts);
        Assert.assertEquals(expectedProducts, actualDbProducts);
    }

    @Test
    @Transactional
    public void shouldCalculateDiscounts() {
        Basket basket = postForNewBasket();
        String id = basket.getId();
        postForExistingBasketWithItem(id, new CountableItem("Apple", 3));
        postForExistingBasketWithItem(id, new CountableItem("Banana", 20));

        Summary summary = template.exchange(base + "/basket/" + id, HttpMethod.DELETE, null, Summary.class).getBody();

        Assertions.assertThat(summary.getTotalPrice()).isEqualByComparingTo("64");
    }

    private Basket postForNewBasket() {
        return template.postForObject(base + "/basket", null, Basket.class);
    }

    private Basket postForExistingBasketWithItem(String id, CountableItem item) {
        return template.postForObject(base + "/basket/" + id, item, Basket.class);
    }

    private Basket postForBasketInDb(String id) {
        return template.getForObject(base + "/basket/" + id, Basket.class);
    }
}