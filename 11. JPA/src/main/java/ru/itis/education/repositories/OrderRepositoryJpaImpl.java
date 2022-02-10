package ru.itis.education.repositories;

import ru.itis.education.models.Orders;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;


public class OrderRepositoryJpaImpl {
    private final EntityManager entityManager;

    public OrderRepositoryJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Orders orders) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(orders);
        transaction.commit();
    }

    public List<Orders> findAll() {
        TypedQuery<Orders> query = entityManager.createQuery("select orders from Orders orders", Orders.class);
        return query.getResultList();
    }
    public List<Orders> findAllByLesson_name(String title) {
        TypedQuery<Orders> query = entityManager.createQuery("select orders from Orders orders where orders.title =: title", Orders.class);
        query.setParameter("title", title);
        return query.getResultList();
    }
}
