package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.model.Product;

public interface ProductsRepository extends JpaRepository<Product, Long> {

}
