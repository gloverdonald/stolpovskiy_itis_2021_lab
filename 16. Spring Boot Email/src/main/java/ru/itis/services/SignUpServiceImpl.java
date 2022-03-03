package ru.itis.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dto.SignUpDto;
import ru.itis.models.Account;
import ru.itis.repositories.AccountsRepository;
import ru.itis.util.EmailUtil;

import java.util.*;

@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {

    private final AccountsRepository accountsRepository;

    private final EmailUtil emailUtil;

    @Transactional
    @Override
    public void signUp(SignUpDto accountForm) {
        Account account = Account.builder()
                .firstName(accountForm.getFirstName())
                .lastName(accountForm.getLastName())
                .email(accountForm.getEmail())
                .state(Account.State.NOT_CONFIRMED)
                .confirmCode(UUID.randomUUID().toString())
                .password(accountForm.getPassword())
                .build();
        accountsRepository.save(account);
        Map<String, String> data = Map.of("confirmCode", account.getConfirmCode(),
                "firstName", account.getFirstName(),
                "lastName", account.getLastName());
        emailUtil.sendMail(account.getEmail(), "Confirm Your Email ", "/emails/confirm_mail.ftlh", data);
    }

    @Override
    public boolean confirm(String code) {
        Optional<Account> optionalAccount = Optional.ofNullable(accountsRepository.findAccountByConfirmCode(code));
        if (optionalAccount.isPresent()) {
            optionalAccount.get().setState(Account.State.CONFIRMED);
            accountsRepository.save(optionalAccount.get());
        }
        return optionalAccount.isPresent();
    }
}
