package com.joannahulek.checkout.component.model;

import java.math.BigDecimal;

public class Summary {

    private BigDecimal totalPrice;
    private int numberOfItems;

    public Summary() {
    }

    Summary(BigDecimal totalPrice, int numberOfItems) {
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