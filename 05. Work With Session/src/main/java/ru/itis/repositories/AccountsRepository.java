package ru.itis.repositories;

import ru.itis.models.Account;

import java.util.List;
import java.util.Optional;

public interface AccountsRepository {
    List<Account> findAll();

    void save(Account account);

    Optional<Account> findByEmail(String email);
}
