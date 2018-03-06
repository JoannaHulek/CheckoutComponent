package com.joannahulek.checkout.component.repository;

import com.joannahulek.checkout.component.ProductPrototypes;
import com.joannahulek.checkout.component.model.Product;
import com.joannahulek.checkout.component.model.StorageCountableProduct;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class StorageRepositoryTest {

    @InjectMocks
    private
    StoreRepository storeRepository;
    @Mock(answer = Answers.RETURNS_MOCKS)
    private EntityManager entityManager;

    @Test
    public void saveStorage() {
        StorageCountableProduct storageCountableProduct = new StorageCountableProduct(
                5, new Product("Milk", new BigDecimal("2.3")));
        storeRepository.saveStorage(storageCountableProduct);
        Mockito.verify(entityManager).merge(storageCountableProduct);
    }


    @Test
    public void getProductAmount() {
        //given
        Product product = ProductPrototypes.createProduct("Apple", new BigDecimal("2.9"));
        StorageCountableProduct storageCountableProduct = new StorageCountableProduct(5, product);
        TypedQuery<StorageCountableProduct> sampleTypedQuery = Mockito.mock(TypedQuery.class);
        given(sampleTypedQuery.getSingleResult()).willReturn(storageCountableProduct);
        given(entityManager.createQuery(any(CriteriaQuery.class))).willReturn(sampleTypedQuery);
        //when
        Integer productAmount = storeRepository.getProductAmount(product);
        //then
        Assertions.assertThat(productAmount).isEqualTo(5);
    }
}