package ru.itis.finalproject.service;

import ru.itis.finalproject.dto.request.LoginRequest;
import ru.itis.finalproject.dto.request.RegistrationRequest;
import ru.itis.finalproject.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    Long create(RegistrationRequest userRequest);

    UserResponse login(LoginRequest loginRequest);

    UserResponse getById(Long id);

    List<UserResponse> getAll();
}
