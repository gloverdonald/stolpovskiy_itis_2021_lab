package ru.itis.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.finalproject.models.ReviewEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

}
