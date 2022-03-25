package ru.itis.service;

import ru.itis.dto.SignUpDto;

public interface SignUpService {
    Long signUp(SignUpDto signUpDto);
}
