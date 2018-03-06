package com.joannahulek.checkout.component.controller;

import com.joannahulek.checkout.component.ProductPrototypes;
import com.joannahulek.checkout.component.model.*;
import com.joannahulek.checkout.component.repository.BasketRepository;
import com.joannahulek.checkout.component.repository.ProductRepository;
import com.joannahulek.checkout.component.repository.StoreRepository;
import com.joannahulek.checkout.component.service.DiscountCalculator;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class BasketControllerTest {

    private BasketRepository basketRepository;
    private ProductRepository productRepository;
    private DiscountCalculator discountCalculator;
    private StoreRepository storeRepository;
    private BasketController basketController;

    @Before
    public void setup() {
        basketRepository = mock(BasketRepository.class);
        productRepository = mock(ProductRepository.class);
        discountCalculator = mock(DiscountCalculator.class);
        storeRepository = mock(StoreRepository.class);
        basketController = new BasketController(basketRepository, productRepository,
                discountCalculator, storeRepository);
        when(storeRepository.getProductAmount(any())).thenReturn(Integer.MAX_VALUE);
    }

    @Test
    public void saveBasket() {
        Basket createdBasket = basketController.saveBasket();
        assertNotNull(createdBasket);
        Mockito.verify(basketRepository, times(1)).saveBasket(createdBasket);
    }

    @Test
    public void closeBasketWithProduct() {
        //given
        Basket existingBasket = new Basket(Collections.singletonList(ProductPrototypes.createCountableProduct(
                "Apple", new BigDecimal("2.9"), 1)));
        String existingBasketID = "123";
        when(discountCalculator.calculateDiscount(existingBasketID)).thenReturn(existingBasket);
        when(basketRepository.getBasket(existingBasketID)).thenReturn(existingBasket);
        //when
        Summary basketSummary = basketController.closeBasket(existingBasketID);
        //then
        assertNotNull(basketSummary);
    }

    @Test
    public void summarizeBasketPrice() {
        List<CountableProduct> productsList = new ArrayList<>();
        productsList.add(ProductPrototypes.createCountableProduct(
                "Apple", new BigDecimal("2.9"), 3));
        productsList.add(ProductPrototypes.createCountableProduct(
                "Banana", new BigDecimal("3.2"), 2));
        String existingID = "11";
        when(discountCalculator.calculateDiscount(existingID)).thenReturn(new Basket(productsList));
        BigDecimal actualPrice = basketController.closeBasket(existingID).getTotalPrice();
        Assertions.assertThat(actualPrice).isEqualByComparingTo("15.1");
    }

    @Test
    public void summarizeBasketPriceWithDiscounts() {
        List<CountableProduct> productsList = new ArrayList<>();
        CountableProduct product = ProductPrototypes.createCountableProduct(
                "Apple", new BigDecimal("2.9"), 5);
        product.setDiscountMultiplier(new BigDecimal("0.2"));
        productsList.add(product);
        String existingID = "145";
        when(discountCalculator.calculateDiscount(existingID)).thenReturn(new Basket(productsList));
        BigDecimal actualPrice = basketController.closeBasket(existingID).getTotalPrice();
        Assertions.assertThat(actualPrice).isEqualByComparingTo("11.6");
    }

    @Test
    public void summarizeBasketAmount() {
        List<CountableProduct> productsList = new ArrayList<>();
        productsList.add(ProductPrototypes.createCountableProduct(
                "Apple", new BigDecimal("2.8"), 5));
        productsList.add(ProductPrototypes.createCountableProduct(
                "Banana", new BigDecimal("3.4"), 1));
        when(discountCalculator.calculateDiscount(eq("123"))).thenReturn(new Basket(productsList));
        int actualAmount = basketController.closeBasket("123").getNumberOfItems();
        Assertions.assertThat(actualAmount).isEqualTo(6);
    }

    @Test
    public void addToBasket() {
        when(basketRepository.getBasket(eq("123")))
                .thenReturn(new Basket(new ArrayList<>()));
        when(productRepository.findProduct("Orange"))
                .thenReturn(new Product("Orange", new BigDecimal("3.8")));
        String basketId = "123";
        CountableItem item = new CountableItem("Orange", 1);
        CountableProduct product = ProductPrototypes.createCountableProduct(
                "Orange", new BigDecimal("3.8"), 1);
        Basket actualBasket = basketController.addToBasket(basketId, item);
        Assert.assertEquals(new Basket(Collections.singletonList(product)), actualBasket);
    }

    @Test(expected = IllegalArgumentException.class)
    public void dontAddToBasketWhenStorageAmountIsLess() {
        Product product = ProductPrototypes.createProduct("Milk", new BigDecimal(3.5));
        when(storeRepository.getProductAmount(product)).thenReturn(3);
        when(productRepository.findProduct("Milk")).thenReturn(product);

        String basketId = "13";
        CountableItem item = new CountableItem("Milk", 5);
        CountableProduct countableProduct = ProductPrototypes.createCountableProduct(
                "Milk", new BigDecimal("3.5"), 5);
        Basket actualBasket = basketController.addToBasket(basketId, item);
    }

    @Test
    public void getBasket() {
        Basket expectedBasket = new Basket();
        when(basketRepository.getBasket(eq("123"))).thenReturn(expectedBasket);
        Basket actualBasket = basketController.getBasket("123");
        Mockito.verify(basketRepository).getBasket("123");
        Assert.assertEquals(expectedBasket, actualBasket);
    }
}