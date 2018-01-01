package com.joannahulek.checkout.component.repository;

import com.joannahulek.checkout.component.CountableProduct;
import com.joannahulek.checkout.component.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StoreRepository {

    private final static List<CountableProduct> sampleStrage = new ArrayList<CountableProduct>();

    static {
        sampleStrage.add(new CountableProduct(new Product("Apple", new BigDecimal("2.9")), 3));
        sampleStrage.add(new CountableProduct(new Product("Banana", new BigDecimal("3.2")), 2));
    }

    public List<CountableProduct> getStorage() {
        return sampleStrage;
    }
}