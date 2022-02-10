package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dto.AccountDto;
import ru.itis.dto.SignInForm;
import ru.itis.models.Account;
import ru.itis.repositories.AccountsRepository;

import java.util.Optional;


@Service
public class SignInServiceImpl implements SignInService {

    private final AccountsRepository accountsRepository;

    @Autowired
    public SignInServiceImpl(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @Override
    public AccountDto signIn(SignInForm signInForm) {
        Optional<Account> accountOptional = accountsRepository.findByEmail(signInForm.getEmail());
        Account account = accountOptional.get();
        return AccountDto
                .builder()
                .id(account.getId())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .email(account.getEmail())
                .password(account.getPassword())
                .build();
    }
}
