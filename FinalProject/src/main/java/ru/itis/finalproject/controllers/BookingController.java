package ru.itis.finalproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.finalproject.dto.request.BookingRequest;
import ru.itis.finalproject.dto.response.BookingResponse;
import ru.itis.finalproject.service.BookingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    private Long create(@RequestBody BookingRequest booking) {
        return bookingService.save(booking);
    }

    @GetMapping("/{id}")
    private BookingResponse get(@PathVariable Long id) {
        return bookingService.get(id);
    }

    @GetMapping("/all")
    private List<BookingResponse> getAll() {
        return bookingService.getAll();
    }
}
