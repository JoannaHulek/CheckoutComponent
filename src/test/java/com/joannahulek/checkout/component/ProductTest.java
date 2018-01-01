package com.joannahulek.checkout.component;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ProductTest {

    @Test
    public void equals() {
        Product product1 = new Product("Test", new BigDecimal("2.3"));
        Product product2 = new Product("Test", new BigDecimal("2.30"));
        Boolean result = product1.equals(product2);
        Assert.assertTrue(result);
    }
}