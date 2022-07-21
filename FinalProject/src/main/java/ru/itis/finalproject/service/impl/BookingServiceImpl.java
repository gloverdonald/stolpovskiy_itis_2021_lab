package ru.itis.finalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.finalproject.dto.request.BookingRequest;
import ru.itis.finalproject.dto.response.BookingResponse;
import ru.itis.finalproject.exceptions.ApartmentNotFoundException;
import ru.itis.finalproject.exceptions.BookingNotFoundException;
import ru.itis.finalproject.exceptions.UserNotFoundException;
import ru.itis.finalproject.mapper.BookingMapper;
import ru.itis.finalproject.models.ApartmentEntity;
import ru.itis.finalproject.models.BookingEntity;
import ru.itis.finalproject.models.UserEntity;
import ru.itis.finalproject.repository.ApartmentRepository;
import ru.itis.finalproject.repository.BookingRepository;
import ru.itis.finalproject.repository.UserRepository;
import ru.itis.finalproject.service.BookingService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final ApartmentRepository apartmentRepository;
    private final UserRepository userRepository;

    @Override
    public Long save(BookingRequest bookingRequest) {
        ApartmentEntity apartment = apartmentRepository
                .findById(bookingRequest.getApartmentId()).orElseThrow(ApartmentNotFoundException::new);

        UserEntity user = userRepository
                .findById(bookingRequest.getCustomerId()).orElseThrow(UserNotFoundException::new);

        BookingEntity booking = bookingMapper.toBooking(bookingRequest);
        booking.setCustomer(user);
        booking.setApartment(apartment);
        return bookingRepository.save(booking).getId();
    }

    @Override
    public BookingResponse get(Long id) {
        BookingEntity booking = bookingRepository.findById(id).orElseThrow(BookingNotFoundException::new);
        return bookingMapper.toResponse(booking);
    }

    @Override
    public List<BookingResponse> getAll() {
        return bookingRepository.findAll()
                .stream()
                .map(bookingMapper::toResponse)
                .collect(Collectors.toList());
    }
}
