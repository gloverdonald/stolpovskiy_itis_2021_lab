package ru.itis.finalproject.service;

import ru.itis.finalproject.dto.request.AddressRequest;
import ru.itis.finalproject.dto.response.AddressResponse;

import java.util.List;

public interface AddressService {

    AddressResponse save(AddressRequest addressRequest);

    AddressResponse get(Long id);

    List<AddressResponse> getAll();
}