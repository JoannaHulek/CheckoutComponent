package com.joannahulek.checkout.component.repository;

import com.joannahulek.checkout.component.model.Discount;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class DiscountRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public DiscountRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void saveDiscount(Discount discount) {
        entityManager.merge(discount);
    }

    public List<Discount> getAll() {
        CriteriaQuery<Discount> query = entityManager.getCriteriaBuilder().createQuery(Discount.class);
        return entityManager.createQuery(query.select(query.from(Discount.class))).getResultList();
    }
}
