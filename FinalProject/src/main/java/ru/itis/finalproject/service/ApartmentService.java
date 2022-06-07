package ru.itis.finalproject.service;

import ru.itis.finalproject.dto.ApartmentSearchDto;
import ru.itis.finalproject.dto.page.ApartmentPage;
import ru.itis.finalproject.dto.request.ApartmentRequest;
import ru.itis.finalproject.dto.request.ApartmentWithAddressRequest;
import ru.itis.finalproject.dto.response.ApartmentResponse;

import java.util.List;

public interface ApartmentService {

    Boolean isAvailable(ApartmentSearchDto searchDto);

    ApartmentResponse save(ApartmentRequest apartmentDto);

    ApartmentResponse get(Long id);

    ApartmentPage getApartmentByCity(Integer page, String param, String city);

    List<ApartmentResponse> getAll();

    ApartmentResponse saveWithAddress(ApartmentWithAddressRequest apartmentWithAddressRequest);
}