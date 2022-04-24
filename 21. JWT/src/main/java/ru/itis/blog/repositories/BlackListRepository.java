package ru.itis.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListRepository {

    void save(String token);

    boolean exists(String token);
}
