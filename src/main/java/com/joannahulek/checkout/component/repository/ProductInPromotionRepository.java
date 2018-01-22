package com.joannahulek.checkout.component.repository;

import com.joannahulek.checkout.component.model.ProductInPromotion;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.math.BigDecimal;

@Repository
public class ProductInPromotionRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    ProductInPromotionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public ProductInPromotion saveProductInPromotion(ProductInPromotion productInPromotion) {
        return entityManager.merge(productInPromotion);
    }

    @Transactional
    public ProductInPromotion findProductInPromotion(String name, int amount, BigDecimal discountValue) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductInPromotion> criteriaQuery = criteriaBuilder.createQuery(ProductInPromotion.class);

        Root<ProductInPromotion> root = criteriaQuery.from(ProductInPromotion.class);

        criteriaQuery.select(root).where(
                criteriaBuilder.equal(root.get("name"), name),
                criteriaBuilder.equal(root.get("amount"), amount),
                criteriaBuilder.equal(root.get("discountValue"), discountValue));

        TypedQuery<ProductInPromotion> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getSingleResult();
    }
}