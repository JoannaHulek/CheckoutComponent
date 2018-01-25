package com.joannahulek.checkout.component.repository;

import com.joannahulek.checkout.component.model.Discount;
import com.joannahulek.checkout.component.model.Product;
import com.joannahulek.checkout.component.model.ProductInPromotion;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DiscountRepositoryTest {

    private EntityManager entityManager;
    private DiscountRepository discountRepository;

    @Before
    public void setup() {
        entityManager = Mockito.mock(EntityManager.class);
        discountRepository = new DiscountRepository(entityManager);
    }

    private List<ProductInPromotion> createSampleDiscount() {
        List<ProductInPromotion> sampleDiscount = new ArrayList<>();
        sampleDiscount.add(new ProductInPromotion(
                new Product("Tomato", new BigDecimal("4.2")),
                20, new BigDecimal("0.5")));
        return sampleDiscount;
    }

    @Test
    public void saveDiscount() {
        Discount existingDiscount = new Discount(createSampleDiscount());
        discountRepository.saveDiscount(existingDiscount);
        Mockito.verify(entityManager).merge(existingDiscount);
    }
}
