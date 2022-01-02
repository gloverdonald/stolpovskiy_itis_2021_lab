package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.models.Product;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select product from Product product join product.orders orders where orders.id = :orderId")
    List<Product> getAllByOrderId(@Param("orderId") Long orderId);

    List<Product> getAllByTitleLike(String title);
}
