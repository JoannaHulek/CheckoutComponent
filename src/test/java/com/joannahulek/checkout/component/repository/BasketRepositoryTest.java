package com.joannahulek.checkout.component.repository;

import com.joannahulek.checkout.component.Basket;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class BasketRepositoryTest {

    private IdRepository idRepository;
    private Map<String, Basket> sampleBaskets;

    @Before
    public void setup() {
        idRepository = mock(IdRepository.class);
        sampleBaskets = mock(Map.class);

    }

    @Test
    public void returnsBasket() {
        Basket expectedBasket = new Basket(emptyList());
        when(sampleBaskets.get(eq("123"))).thenReturn(expectedBasket);

        BasketRepository basketRepository = new BasketRepository(sampleBaskets, idRepository);
        String existingId = "123";
        Basket existingBasket = basketRepository.getBasket(existingId);
        assertNotNull(existingBasket);
    }

    @Test
    public void addBasket() {
        when(idRepository.createId()).thenReturn("123");
        Basket expectedBasket = new Basket(emptyList());
        BasketRepository basketRepository = new BasketRepository(sampleBaskets, idRepository);
        basketRepository.addBasket(expectedBasket);
        verify(sampleBaskets).put("123", expectedBasket);
    }

}