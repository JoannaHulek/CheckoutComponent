package com.joannahulek.checkout.component;

import java.util.List;

public class Basket {

    private List<CountableProduct> products;

    private boolean active;

    public Basket(List<CountableProduct> products) {
        this.products = products;
        this.active = true;
    }

    public List<CountableProduct> getProducts() {
        return products;
    }

    public boolean isActive() {
        return active;
    }

    public void closeBasket() {
        active = false;
    }
}
