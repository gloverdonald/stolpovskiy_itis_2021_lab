package ru.itis.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.finalproject.models.RefreshTokenEntity;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {

    Optional<RefreshTokenEntity> findByCode(String code);

    void deleteByCode(String code);
}
