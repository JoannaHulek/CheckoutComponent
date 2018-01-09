package com.joannahulek.checkout.component.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @OneToMany
    private List<ProductInPromotion> productsInPromotion;

    public Discount() {
    }

    public Discount(List<ProductInPromotion> productsInPromotion) {
        this.productsInPromotion = productsInPromotion;
    }

    public List<ProductInPromotion> getProductsInPromotion() {
        return productsInPromotion;
    }
}