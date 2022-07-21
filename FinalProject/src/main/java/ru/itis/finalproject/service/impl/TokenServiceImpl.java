package ru.itis.finalproject.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.finalproject.dto.request.TokenRefreshRequest;
import ru.itis.finalproject.dto.response.TokensResponse;
import ru.itis.finalproject.dto.response.UserResponse;
import ru.itis.finalproject.exceptions.UserNotFoundException;
import ru.itis.finalproject.models.RefreshTokenEntity;
import ru.itis.finalproject.models.UserEntity;
import ru.itis.finalproject.repository.RefreshTokenRepository;
import ru.itis.finalproject.repository.UserRepository;
import ru.itis.finalproject.service.TokenService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TokenServiceImpl implements TokenService {
    @Value("${expiration.refresh}")
    private Long refreshTokenLifetime;
    @Value("${expiration.access}")
    private Long accessTokenLifetime;
    @Value("${secret}")
    private String secretKey;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public TokensResponse generateTokens(UserResponse userResponse) {
        UserEntity userEntity = userRepository
                .findById(userResponse.getId())
                .orElseThrow(UserNotFoundException::new);

        RefreshTokenEntity refreshToken = generateRefreshToken(userEntity);
        refreshTokenRepository.save(refreshToken);

        return TokensResponse.builder()
                .accessToken("BEARER ".concat(generateAccessToken(userEntity)))
                .refreshToken(refreshToken.getCode())
                .build();
    }

    @Override
    public TokensResponse refreshTokens(TokenRefreshRequest request) {
        RefreshTokenEntity refreshTokenEntity = refreshTokenRepository
                .findByCode(request.getRefreshToken())
                .orElseThrow(UserNotFoundException::new);
        UserEntity userEntity = refreshTokenEntity.getUser();
        refreshTokenEntity.setCode(UUID.randomUUID().toString());
        refreshTokenRepository.save(refreshTokenEntity);
        return TokensResponse.builder()
                .accessToken("BEARER ".concat(generateAccessToken(userEntity)))
                .refreshToken(refreshTokenEntity.getCode())
                .build();
    }

    private RefreshTokenEntity generateRefreshToken(UserEntity userEntity) {
        return RefreshTokenEntity.builder()
                .code(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plus(refreshTokenLifetime, ChronoUnit.MILLIS))
                .user(userEntity)
                .build();
    }

    private String generateAccessToken(UserEntity userEntity) {
        Map<String, Object> claims = Jwts.claims();
        claims.put(Claims.SUBJECT, userEntity.getEmail());
        claims.put("roles", List.of(userEntity.getRole().toString()));
        return Jwts.builder()
                .setSubject(userEntity.getEmail())
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(Instant.now().plusMillis(accessTokenLifetime)))
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }
}
