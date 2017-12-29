package com.joannahulek.checkout.component;

import com.joannahulek.checkout.component.interfaces.Countable;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "countableProducts")
public class CountableProduct implements Countable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private int amount;
    @OneToOne
    private Product product;

    public CountableProduct() {

    }

    public CountableProduct(String name, BigDecimal price, int amount) {
        product = new Product(name, price);
        this.amount = amount;
    }

    @Override
    public int count() {
        return amount;
    }

    public String getName() {
        return product.getName();
    }

    public BigDecimal getPrice() {
        return product.getPrice();
    }

    public String getId() {
        return id;
    }
}
