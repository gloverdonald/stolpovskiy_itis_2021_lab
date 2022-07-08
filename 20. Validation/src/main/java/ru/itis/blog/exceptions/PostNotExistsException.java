package ru.itis.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PostNotExistsException extends RuntimeException {
    public PostNotExistsException(Long postId) {
        super("Post with id <" + postId + "> is not exists");
    }
}
