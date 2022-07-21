package ru.itis.finalproject.service;

import ru.itis.finalproject.dto.request.LoginRequest;
import ru.itis.finalproject.dto.response.UserResponse;

public interface EmailService {

    UserResponse confirmEmail(String confirmCode);

    void createCode(LoginRequest loginRequest);
}
