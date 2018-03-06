package com.joannahulek.checkout.component.repository;

import com.joannahulek.checkout.component.model.Product;
import com.joannahulek.checkout.component.model.StorageCountableProduct;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    public Integer getProductAmount(Product product) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<StorageCountableProduct> criteriaQuery = criteriaBuilder.createQuery(StorageCountableProduct.class);
        Root<StorageCountableProduct> root = criteriaQuery.from(StorageCountableProduct.class);

        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("product"), product));
        TypedQuery<StorageCountableProduct> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getSingleResult().getAmount();
    }
}