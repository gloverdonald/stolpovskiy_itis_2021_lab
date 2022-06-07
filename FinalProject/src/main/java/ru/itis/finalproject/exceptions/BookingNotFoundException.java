package ru.itis.finalproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Booking not found")
public class BookingNotFoundException extends RuntimeException {

}
