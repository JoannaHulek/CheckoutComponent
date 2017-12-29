package com.joannahulek.checkout.component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "baskets")
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @OneToMany
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

    public void addProduct(CountableProduct product) {
        List<CountableProduct> productsList = getProducts();
        productsList.add(product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        return active == basket.active &&
                Objects.equals(products, basket.products);
    }

    @Override
    public int hashCode() {

        return Objects.hash(products, active);
    }
}
