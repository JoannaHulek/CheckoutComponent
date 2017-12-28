package com.joannahulek.checkout.component;

import com.joannahulek.checkout.component.interfaces.Countable;

import java.math.BigDecimal;

public class CountableProduct extends Product implements Countable{
  private int amount;

    public CountableProduct(String name, BigDecimal price, int amount) {
        super(name, price);
        this.amount=amount;
    }

    @Override
    public int count() {
        return amount;
    }
}
