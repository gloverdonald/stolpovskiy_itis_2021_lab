package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dto.SignUpForm;
import ru.itis.models.Account;
import ru.itis.repositories.AccountsRepository;

@Service
public class SignUpServiceImpl implements SignUpService {

    private final AccountsRepository accountsRepository;

    @Autowired
    public SignUpServiceImpl(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @Override
    public void signUp(SignUpForm form) {
        Account account = Account.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .email(form.getEmail())
                .password(form.getPassword())
                .build();

        accountsRepository.save(account);
    }
}
