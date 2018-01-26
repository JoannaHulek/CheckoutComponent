package com.joannahulek.checkout.component.model;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "productsInPromotion")
public class ProductInPromotion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @OneToOne
    private Product productInPromotion;
    private int amount;
    private BigDecimal discountValue;   // range 0-1 (%)

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
        return normalizeDiscount();
    }

    private BigDecimal normalizeDiscount() {
        return discountValue.min(BigDecimal.ONE).max(BigDecimal.ZERO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductInPromotion that = (ProductInPromotion) o;

        return amount == that.amount && productInPromotion.equals(that.productInPromotion) && discountValue.equals(that.discountValue);
    }

    @Override
    public int hashCode() {
        int result = productInPromotion.hashCode();
        result = 31 * result + amount;
        result = 31 * result + discountValue.hashCode();
        return result;
    }
}