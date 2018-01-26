package com.joannahulek.checkout.component.model;

import com.joannahulek.checkout.component.interfaces.Countable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "countableProducts")
public class CountableProduct implements Countable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private int amount;
    @ManyToOne
    private Product product;

    public CountableProduct() {
    }

    public CountableProduct(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    @Override
    public int count() {
        return getAmount();
    }

    @Transient
    public String getName() {
        return product.getName();
    }

    @Transient
    public BigDecimal getPrice() {
        return product.getPrice();
    }

    public int getAmount() {
        return amount;
    }

    public String getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountableProduct product1 = (CountableProduct) o;
        return amount == product1.amount &&
                Objects.equals(product, product1.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, product);
    }

    @Override
    public String toString() {
        return "CountableProduct{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", product=" + product +
                '}';
    }

    public Boolean compareToProductInPromotion(ProductInPromotion productInPromotion) {
        return this.getProduct().equals(productInPromotion.getProductInPromotion())
                && this.getAmount() >= productInPromotion.getAmount();
    }
}