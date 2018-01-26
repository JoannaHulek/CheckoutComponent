package com.joannahulek.checkout.component.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class CountableProductTest {

    private CountableProduct genetareSampleCountableProduct() {
        return new CountableProduct(new Product("Banana", new BigDecimal("3.2")), 10);
    }

    @Test
    public void equals() {
        CountableProduct product1 = genetareSampleCountableProduct();
        CountableProduct product2 = genetareSampleCountableProduct();
        Assert.assertTrue(product1.equals(product2));
    }

    @Test
    public void compareToProductInPromotion() {
        CountableProduct countableProduct = genetareSampleCountableProduct();
        ProductInPromotion productInPromotion = new ProductInPromotion(new Product("Banana", new BigDecimal("3.2")), 10, new BigDecimal("0.05"));
        Assert.assertTrue(countableProduct.compareToProductInPromotion(productInPromotion));
    }
}