package ru.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
