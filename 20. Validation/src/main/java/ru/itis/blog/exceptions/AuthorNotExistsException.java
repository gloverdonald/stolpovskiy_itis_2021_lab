package ru.itis.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AuthorNotExistsException extends RuntimeException {
    public AuthorNotExistsException(Long authorId) {
        super("Author with id <" + authorId + "> is not exists");
    }
}
