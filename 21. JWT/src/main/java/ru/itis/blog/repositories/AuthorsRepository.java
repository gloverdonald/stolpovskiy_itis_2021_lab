package ru.itis.blog.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.blog.models.Author;

import java.util.Optional;

public interface AuthorsRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByEmail(String email);

    Optional<Author> findByToken(String token);

    Page<Author> findAll(Pageable pageable);
}
