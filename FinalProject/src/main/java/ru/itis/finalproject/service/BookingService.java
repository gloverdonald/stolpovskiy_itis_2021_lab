package ru.itis.finalproject.service;

import ru.itis.finalproject.dto.request.BookingRequest;
import ru.itis.finalproject.dto.response.BookingResponse;

import java.util.List;

public interface BookingService {

    Long save(BookingRequest bookingRequest);

    BookingResponse get(Long id);

    List<BookingResponse> getAll();
}