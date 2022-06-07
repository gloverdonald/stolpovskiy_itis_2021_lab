package ru.itis.finalproject.exceptions;

import org.springframework.http.HttpStatus;

public class EmailAlreadyConfirmedException extends HttpServiceException {

    public EmailAlreadyConfirmedException(HttpStatus httpStatus, String message) {
        super(HttpStatus.FORBIDDEN, "Email is already confirmed");
    }
}
