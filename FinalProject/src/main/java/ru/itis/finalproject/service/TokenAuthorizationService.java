package ru.itis.finalproject.service;

import ru.itis.finalproject.dto.response.UserResponse;

public interface TokenAuthorizationService {
    UserResponse getUserInfoByToken(String token);
}
