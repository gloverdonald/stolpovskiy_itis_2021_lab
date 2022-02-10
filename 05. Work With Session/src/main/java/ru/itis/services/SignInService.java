package ru.itis.services;

import ru.itis.dto.AccountDto;
import ru.itis.dto.SignInForm;


public interface SignInService {
    AccountDto signIn(SignInForm signInForm);
}
