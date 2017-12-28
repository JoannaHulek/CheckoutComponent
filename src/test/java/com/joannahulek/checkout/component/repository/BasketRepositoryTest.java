package com.joannahulek.checkout.component.repository;

import com.joannahulek.checkout.component.Basket;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class BasketRepositoryTest {

    private EntityManager entityManager;
    private BasketRepository basketRepository;

    @Before
    public void setup() {
        entityManager = mock(EntityManager.class);
        basketRepository = new BasketRepository(entityManager);

    }

    @Test
    public void returnsBasket() {
        String existingId = "123";
        Mockito.when(entityManager.find(eq(Basket.class), eq(existingId))).thenReturn(new Basket());
        Basket existingBasket = basketRepository.getBasket(existingId);
        assertNotNull(existingBasket);
    }

    @Test
    public void addBasket() {
        Basket expectedBasket = new Basket(emptyList());
        basketRepository.createBasket(expectedBasket);
        verify(entityManager).persist(expectedBasket);
    }

}