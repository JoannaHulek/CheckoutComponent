package com.joannahulek.checkout.component.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ProductInPromotionTest {

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
}