package ru.itis.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.finalproject.models.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}