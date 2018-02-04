package com.joannahulek.checkout.component;

import com.joannahulek.checkout.component.model.CountableProduct;
import com.joannahulek.checkout.component.model.Product;
import com.joannahulek.checkout.component.model.ProductInPromotion;

import java.math.BigDecimal;

public class ProductPrototypes {

    public static Product createProduct(String name, BigDecimal price) {
        return new Product(name, price);
    }

    public static CountableProduct createCountableProduct(String name, BigDecimal price, int amount) {
        return new CountableProduct(createProduct(name, price), amount);
    }

    public static ProductInPromotion createProductInPromotion(String name, BigDecimal price, int amount, BigDecimal discountValue) {
        return new ProductInPromotion(createProduct(name, price), amount, discountValue);
    }
}
