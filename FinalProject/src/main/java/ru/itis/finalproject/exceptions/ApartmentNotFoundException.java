package ru.itis.finalproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Apartment not found")
public class ApartmentNotFoundException extends RuntimeException {

}
