package ru.itis.education.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.itis.education.models.Customer;
import ru.itis.education.models.Product;
import ru.itis.education.models.Orders;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate/hibernate.cfg.xml");

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();


        Query<Product> studentQuery = session.createQuery(
                "select prod from Product prod",
                Product.class);
        List<Product> products = studentQuery.getResultList();
        System.out.println(products);

        Query<Customer> studentQuery2 = session.createQuery(
                "select customer from Customer customer",
                Customer.class);
        List<Customer> customer = studentQuery2.getResultList();
        System.out.println(customer);

        Query<Orders> studentQuery3 = session.createQuery(
                "select orders from Orders orders",
                Orders.class);
        List<Orders> orders = studentQuery3.getResultList();
        System.out.println(orders);

        session.close();
        sessionFactory.close();
    }
}
