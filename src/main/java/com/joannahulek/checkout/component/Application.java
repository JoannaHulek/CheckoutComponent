package com.joannahulek.checkout.component;

import com.joannahulek.checkout.component.repository.ProductRepository;
import com.joannahulek.checkout.component.repository.StoreRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StoreRepository storeRepository;

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
            storeRepository.saveStorage(new StorageCountableProduct(8, productRepository.findProduct("Banana")));
            storeRepository.saveStorage(new StorageCountableProduct(2, productRepository.findProduct("Potato")));
            storeRepository.saveStorage(new StorageCountableProduct(7, productRepository.findProduct("Tomato")));
        };
    }
}