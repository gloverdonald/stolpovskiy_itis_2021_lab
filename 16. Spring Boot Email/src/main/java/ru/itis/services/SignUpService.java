package ru.itis.services;

import ru.itis.dto.SignUpDto;

public interface SignUpService {
    void signUp(SignUpDto accountForm);

    boolean confirm(String code);
}
