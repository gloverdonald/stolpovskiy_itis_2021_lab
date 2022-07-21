package ru.itis.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.finalproject.models.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
}
