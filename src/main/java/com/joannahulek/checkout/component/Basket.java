package com.joannahulek.checkout.component;

import java.util.List;

public class Basket {

    private List<CountableProduct> products;

    public Basket(List<CountableProduct> products) {
        this.products = products;
    }

    public List<CountableProduct> getProducts() {
        return products;
    }
}
