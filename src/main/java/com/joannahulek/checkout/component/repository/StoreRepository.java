package com.joannahulek.checkout.component.repository;

import com.joannahulek.checkout.component.CountableProduct;
import com.joannahulek.checkout.component.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository
public class StoreRepository {

    private final static List<CountableProduct> sampleStrage = new ArrayList<>();

    static {
        sampleStrage.add(new CountableProduct(new Product("Apple", new BigDecimal("2.9")), 3));
        sampleStrage.add(new CountableProduct(new Product("Banana", new BigDecimal("3.2")), 2));
    }

    public List<CountableProduct> getStorage() {
        return sampleStrage;
    }

    public List<String> getProductsList() {
        return sampleStrage.stream()
                .map(CountableProduct::getName)
                .collect(toList());
    }
}