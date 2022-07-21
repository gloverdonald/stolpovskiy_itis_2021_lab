package ru.itis.finalproject.service;

import ru.itis.finalproject.dto.request.TokenRefreshRequest;
import ru.itis.finalproject.dto.response.TokensResponse;
import ru.itis.finalproject.dto.response.UserResponse;

public interface TokenService {
    TokensResponse generateTokens(UserResponse userResponse);

    TokensResponse refreshTokens(TokenRefreshRequest request);
}
