package com.joannahulek.checkout.component;

import java.math.BigDecimal;
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

    public Summary closeBasket() {
        active = false;
        BigDecimal totalPrice = getProducts().stream()
                .map(countableProduct -> countableProduct.getPrice().multiply(BigDecimal.valueOf(countableProduct.count())))
                .reduce(BigDecimal.ZERO, (bigDecimal, bigDecimal2) -> bigDecimal.add(bigDecimal2));
        int numberOfItems = getProducts().stream()
                .map(countableProduct -> countableProduct.count())
                .reduce(0, (integer, integer2) -> integer + integer2);
        Summary basketSummary = new Summary(totalPrice, numberOfItems);

        return basketSummary;
    }

}
