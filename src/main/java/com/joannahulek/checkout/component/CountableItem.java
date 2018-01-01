package com.joannahulek.checkout.component;

import com.joannahulek.checkout.component.interfaces.Countable;

public class CountableItem implements Countable {

    private String name;
    private int amount;

    public CountableItem() {
    }

    public CountableItem(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    @Override
    public int count() {
        return amount;
    }

    public String getName() {
        return name;
    }
}