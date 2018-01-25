package com.joannahulek.checkout.component.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ProductInPromotionTest {

    private ProductInPromotion getSampleProductInPromotion() {
        return new ProductInPromotion(new Product("Banana", new BigDecimal("3.2")), 10, new BigDecimal("0.05"));
    }

    @Test
    public void normalizeUpperDiscount() {
        ProductInPromotion actualProductInPromotion = new ProductInPromotion(new Product("Apple", new BigDecimal("2.9")), 1, new BigDecimal("1.5"));
        ProductInPromotion expectedProductInPromotion = new ProductInPromotion(new Product("Apple", new BigDecimal("2.9")), 1, BigDecimal.ONE);
        actualProductInPromotion.getDiscountValue();
        Assert.assertEquals(actualProductInPromotion.getDiscountValue(), expectedProductInPromotion.getDiscountValue());
    }

    @Test
    public void normalizeLowerDiscount() {
        ProductInPromotion actualProductInPromotion = new ProductInPromotion(new Product("Apple", new BigDecimal("2.9")), 1, new BigDecimal("-0.5"));
        ProductInPromotion expectedProductInPromotion = new ProductInPromotion(new Product("Apple", new BigDecimal("2.9")), 1, BigDecimal.ZERO);
        actualProductInPromotion.getDiscountValue();
        Assert.assertEquals(actualProductInPromotion.getDiscountValue(), expectedProductInPromotion.getDiscountValue());
    }

    @Test
    public void equals() {
        ProductInPromotion product1 = getSampleProductInPromotion();
        ProductInPromotion product2 = getSampleProductInPromotion();
        Assert.assertTrue(product1.equals(product2));
    }

    @Test
    public void compareToCountableProduct() {
        ProductInPromotion productInPromotion = getSampleProductInPromotion();
        CountableProduct countableProduct = new CountableProduct(new Product("Banana", new BigDecimal("3.2")), 10);
        Assert.assertTrue(productInPromotion.compareToCountableProduct(productInPromotion, countableProduct));
    }
}