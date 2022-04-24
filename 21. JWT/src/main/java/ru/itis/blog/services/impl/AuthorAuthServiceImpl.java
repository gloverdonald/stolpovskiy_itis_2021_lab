package ru.itis.blog.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.blog.dto.AuthorRequest;
import ru.itis.blog.models.Author;
import ru.itis.blog.repositories.AuthorsRepository;
import ru.itis.blog.services.AuthorAuthService;

@RequiredArgsConstructor
@Service
public class AuthorAuthServiceImpl implements AuthorAuthService {

    private final AuthorsRepository authorsRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Long createAuthor(AuthorRequest authorRequest) {
        Author author = Author.builder()
                .email(authorRequest.getEmail())
                .password(passwordEncoder.encode(authorRequest.getPassword()))
                .firstName(authorRequest.getFirstName())
                .lastName(authorRequest.getLastName())
                .role(authorRequest.getRole())
                .state(Author.State.CONFIRMED)
                .build();

        return authorsRepository.save(author).getId();
    }
}
