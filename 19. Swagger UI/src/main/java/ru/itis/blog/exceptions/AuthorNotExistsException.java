package ru.itis.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 31.03.2022
 * 19. BLOG
 *
 * @author Sidikov Marsel (Akvelon)
 * @version v1.0
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AuthorNotExistsException extends RuntimeException {
    public AuthorNotExistsException(Long authorId) {
        super("Author with id <" + authorId + "> is not exists");
    }
}
