package com.joannahulek.checkout.component.model;

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
    @OneToMany(fetch = FetchType.EAGER)
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
                .map(countableProduct -> countableProduct.getPrice()
                        .multiply(countableProduct.getDiscountMultiplier())
                        .multiply(BigDecimal.valueOf(countableProduct.count())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        int numberOfItems = getProducts().stream()
                .map(CountableProduct::count)
                .reduce(0, (integer, integer2) -> integer + integer2);

        return new Summary(totalPrice, numberOfItems);
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