package com.joannahulek.checkout.component.model;

import javax.persistence.*;

@Entity
@Table(name = "storageCountableProduct")
public class StorageCountableProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private int amount;
    @OneToOne
    private Product product;

    public StorageCountableProduct() {
    }

    public StorageCountableProduct(int amount, Product product) {
        this.amount = amount;
        this.product = product;
    }

    public String getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "StorageCountableProduct{" +
                "amount=" + amount +
                ", product=" + product +
                '}';
    }

    public Product getProduct() {
        return product;
    }
}
