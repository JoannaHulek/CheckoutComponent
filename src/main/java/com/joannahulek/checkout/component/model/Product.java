package com.joannahulek.checkout.component.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {
    @Id
    private String name;
    private BigDecimal price;
    private transient BigDecimal multiplayer = BigDecimal.ONE;

    public Product() {
    }

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return (name != null ? name.equals(product.name) : product.name == null) && (price != null ? price.compareTo(product.price) == 0 : product.price == null);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    public BigDecimal getMultiplayer() {
        return multiplayer;
    }

    public void setMultiplayer(BigDecimal multiplayer) {
        this.multiplayer = multiplayer;
    }
}