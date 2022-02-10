package ru.itis.education.app;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.itis.education.models.Customer;
import ru.itis.education.models.Orders;
import ru.itis.education.models.Product;
import ru.itis.education.repositories.OrderRepositoryJpaImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate/hibernate.cfg.xml");

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        EntityManager entityManager = sessionFactory.createEntityManager();
        OrderRepositoryJpaImpl orderRepositoryJpa = new OrderRepositoryJpaImpl(entityManager);


        System.out.println(orderRepositoryJpa.findAll());

        orderRepositoryJpa.save(Orders.builder()
                .title("new oreder")
                .customer(entityManager.find(Customer.class, 1L))
                .build());

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityManager.getTransaction();
        entityTransaction.begin();
        Product prod = entityManager.find(Product.class, 3L);
        Orders ord = entityManager.find(Orders.class, 7L);
        ord.getProducts().add(prod);
        entityManager.persist(ord);
        entityTransaction.commit();
    }
}
