package com.joannahulek.checkout.component;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "productsInPromotion")
public class ProductInPromotion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private Product productInPromotion;
    private int amount;
    private BigDecimal discountValue;

    public ProductInPromotion() {
    }

    public ProductInPromotion(Product productInPromotion, int amount, BigDecimal discountValue) {
        this.productInPromotion = productInPromotion;
        this.amount = amount;
        this.discountValue = discountValue;
    }

    public Product getProductInPromotion() {
        return productInPromotion;
    }

    public int getAmount() {
        return amount;
    }

    public BigDecimal getDiscountValue() {
        verifyDiscount();
        return discountValue;
    }

    private void verifyDiscount() {

    }
}