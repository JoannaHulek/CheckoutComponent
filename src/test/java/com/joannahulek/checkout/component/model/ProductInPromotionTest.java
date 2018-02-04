package com.joannahulek.checkout.component.model;

import com.joannahulek.checkout.component.ProductPrototypes;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ProductInPromotionTest {

    @Test
    public void normalizeUpperDiscount() {
        ProductInPromotion actualProductInPromotion = ProductPrototypes.createProductInPromotion(
                "Apple", new BigDecimal("2.9"), 1, new BigDecimal("1.5"));
        ProductInPromotion expectedProductInPromotion = ProductPrototypes.createProductInPromotion(
                "Apple", new BigDecimal("2.9"), 1, BigDecimal.ONE);
        actualProductInPromotion.getDiscountValue();
        Assert.assertEquals(actualProductInPromotion.getDiscountValue(), expectedProductInPromotion.getDiscountValue());
    }

    @Test
    public void normalizeLowerDiscount() {
        ProductInPromotion actualProductInPromotion = ProductPrototypes.createProductInPromotion(
                "Apple", new BigDecimal("2.9"), 1, new BigDecimal("-0.5"));
        ProductInPromotion expectedProductInPromotion = ProductPrototypes.createProductInPromotion(
                "Apple", new BigDecimal("2.9"), 1, BigDecimal.ZERO);
        actualProductInPromotion.getDiscountValue();
        Assert.assertEquals(actualProductInPromotion.getDiscountValue(), expectedProductInPromotion.getDiscountValue());
    }

    @Test
    public void equals() {
        ProductInPromotion product1 = ProductPrototypes.createProductInPromotion(
                "Apple", new BigDecimal("2.9"), 1, new BigDecimal("0.5"));
        ProductInPromotion product2 = ProductPrototypes.createProductInPromotion(
                "Apple", new BigDecimal("2.9"), 1, new BigDecimal("0.5"));
        Assert.assertTrue(product1.equals(product2));
    }
}