package com.joannahulek.checkout.component.model;

import com.joannahulek.checkout.component.ProductPrototypes;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class CountableProductTest {

    @Test
    public void equals() {
        CountableProduct product1 = ProductPrototypes.createCountableProduct(
                "Banana", new BigDecimal("3.2"), 10);
        CountableProduct product2 = ProductPrototypes.createCountableProduct(
                "Banana", new BigDecimal("3.2"), 10);
        Assert.assertTrue(product1.equals(product2));
    }

    @Test
    public void compareToProductInPromotion() {
        CountableProduct countableProduct = ProductPrototypes.createCountableProduct(
                "Banana", new BigDecimal("3.2"), 10);
        ProductInPromotion productInPromotion = ProductPrototypes.createProductInPromotion(
                "Banana", new BigDecimal("3.2"), 10, new BigDecimal("0.05"));
        Assert.assertTrue(countableProduct.compareToProductInPromotion(productInPromotion));
    }
}