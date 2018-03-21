package com.joannahulek.checkout.component;

import com.joannahulek.checkout.component.model.Discount;
import com.joannahulek.checkout.component.model.Product;
import com.joannahulek.checkout.component.model.ProductInPromotion;
import com.joannahulek.checkout.component.model.StorageCountableProduct;
import com.joannahulek.checkout.component.repository.DiscountRepository;
import com.joannahulek.checkout.component.repository.ProductInPromotionRepository;
import com.joannahulek.checkout.component.repository.ProductRepository;
import com.joannahulek.checkout.component.repository.StoreRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private ProductInPromotionRepository productInPromotionRepository;
    @Autowired
    private DiscountRepository discountRepository;

    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            productRepository.saveProduct(new Product("Milk", new BigDecimal("2.3")));
            productRepository.saveProduct(new Product("Apple", new BigDecimal("2.9")));
            productRepository.saveProduct(new Product("Banana", new BigDecimal("3.2")));
            productRepository.saveProduct(new Product("Potato", new BigDecimal("2.2")));
            productRepository.saveProduct(new Product("Tomato", new BigDecimal("4.2")));

            storeRepository.saveStorage(new StorageCountableProduct(5, productRepository.findProduct("Milk")));
            storeRepository.saveStorage(new StorageCountableProduct(3, productRepository.findProduct("Apple")));
            storeRepository.saveStorage(new StorageCountableProduct(38, productRepository.findProduct("Banana")));
            storeRepository.saveStorage(new StorageCountableProduct(2, productRepository.findProduct("Potato")));
            storeRepository.saveStorage(new StorageCountableProduct(7, productRepository.findProduct("Tomato")));

            ProductInPromotion tomato20_05 = productInPromotionRepository.saveProductInPromotion(
                    new ProductInPromotion(productRepository.findProduct("Tomato"), 20, new BigDecimal("0.5")));
            ProductInPromotion apple1_0 = productInPromotionRepository.saveProductInPromotion(
                    new ProductInPromotion(productRepository.findProduct("Apple"), 1, BigDecimal.ONE));
            ProductInPromotion banana20_0 = productInPromotionRepository.saveProductInPromotion(
                    new ProductInPromotion(productRepository.findProduct("Banana"), 20, BigDecimal.ZERO));

            List<ProductInPromotion> promotion1 = new ArrayList<>();
            promotion1.add(tomato20_05);
            Discount discount1 = new Discount(promotion1);
            discountRepository.saveDiscount(discount1);

            List<ProductInPromotion> promotion2 = new ArrayList<>();
            promotion2.add(banana20_0);
            promotion2.add(apple1_0);
            Discount discount2 = new Discount(promotion2);
            discountRepository.saveDiscount(discount2);
        };
    }
}