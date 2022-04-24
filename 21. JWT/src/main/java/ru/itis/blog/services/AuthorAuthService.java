package ru.itis.blog.services;
import ru.itis.blog.dto.AuthorRequest;

public interface AuthorAuthService {

    Long createAuthor(AuthorRequest authorRequest);
}
