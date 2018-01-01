package com.joannahulek.checkout.component;

import java.math.BigDecimal;

public class Summary {

    private final BigDecimal totalPrice;
    private final int numberOfItems;

    public Summary(BigDecimal totalPrice, int numberOfItems) {
        this.totalPrice = totalPrice;
        this.numberOfItems = numberOfItems;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}