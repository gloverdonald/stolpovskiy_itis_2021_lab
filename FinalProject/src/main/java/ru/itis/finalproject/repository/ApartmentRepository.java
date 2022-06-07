package ru.itis.finalproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.finalproject.models.ApartmentEntity;

public interface ApartmentRepository extends JpaRepository<ApartmentEntity, Long> {
    Page<ApartmentEntity> findByAddress_CityIgnoreCase(String city, Pageable pageable);
}