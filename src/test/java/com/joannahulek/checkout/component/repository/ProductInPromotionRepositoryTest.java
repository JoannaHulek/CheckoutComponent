package com.joannahulek.checkout.component.repository;

import com.joannahulek.checkout.component.model.Product;
import com.joannahulek.checkout.component.model.ProductInPromotion;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class ProductInPromotionRepositoryTest {

    private EntityManager entityManager;
    private ProductInPromotionRepository productInPromotionRepository;

    @Before
    public void setup() {
        entityManager = Mockito.mock(EntityManager.class);
        productInPromotionRepository = new ProductInPromotionRepository(entityManager);
    }

    @Test
    public void saveProductInPromotion() {
        ProductInPromotion productInPromotion = new ProductInPromotion(new Product("Tomato", new BigDecimal("4.2")), 20, new BigDecimal("0.5"));
        productInPromotionRepository.saveProductInPromotion(productInPromotion);
        Mockito.verify(entityManager).merge(productInPromotion);
    }
}
