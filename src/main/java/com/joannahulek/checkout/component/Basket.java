package com.joannahulek.checkout.component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "basket")
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Transient
    private List<CountableProduct> products;
    private boolean active;

    public Basket() {
    }

    public Basket(List<CountableProduct> products) {
        this.products = products;
        this.active = true;
    }


    public List<CountableProduct> getProducts() {
        return products != null ? products : Collections.emptyList();
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

    public String getId() {
        return id;
    }
}
