package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
