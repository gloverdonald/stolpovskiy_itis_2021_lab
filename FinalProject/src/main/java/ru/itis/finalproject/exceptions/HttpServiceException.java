package ru.itis.finalproject.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpServiceException extends RuntimeException {

    private final HttpStatus httpStatus;

    public HttpServiceException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
