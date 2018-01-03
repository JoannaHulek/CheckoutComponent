package com.joannahulek.checkout.component.repository;

import com.joannahulek.checkout.component.StorageCountableProduct;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class StoreRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public StoreRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void saveStorage(StorageCountableProduct storageCountableProduct) {
        entityManager.merge(storageCountableProduct);
    }

    public List<StorageCountableProduct> getStorage() {
        CriteriaQuery<StorageCountableProduct> query = entityManager.getCriteriaBuilder().createQuery(StorageCountableProduct.class);
        return entityManager.createQuery(query.select(query.from(StorageCountableProduct.class))).getResultList();
    }
}