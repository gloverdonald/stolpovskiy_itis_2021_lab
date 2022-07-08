package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Account;

public interface AccountsRepository extends JpaRepository<Account, Long> {
    Account findAccountByConfirmCode(String code);
}
