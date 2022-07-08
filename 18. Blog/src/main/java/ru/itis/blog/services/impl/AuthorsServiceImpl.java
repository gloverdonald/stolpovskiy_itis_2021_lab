package ru.itis.blog.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.itis.blog.dto.AuthorDto;
import ru.itis.blog.dto.AuthorsPage;
import ru.itis.blog.exceptions.AuthorNotFoundException;
import ru.itis.blog.models.Author;
import ru.itis.blog.repositories.AuthorsRepository;
import ru.itis.blog.services.AuthorsService;

import static ru.itis.blog.dto.AuthorDto.from;

@RequiredArgsConstructor
@Service
public class AuthorsServiceImpl implements AuthorsService {

    private final AuthorsRepository authorsRepository;

    @Value("${blog.default-page-size}")
    private int defaultPageSize;

    @Override
    public AuthorsPage getAuthors(int page) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize);
        Page<Author> authorPage = authorsRepository.findAll(pageRequest);
        return AuthorsPage.builder()
                .authors(from(authorPage.getContent()))
                .totalPages(authorPage.getTotalPages())
                .build();
    }

    @Override
    public AuthorDto addAuthor(AuthorDto author) {
        return from(authorsRepository.save(
                Author.builder()
                        .firstName(author.getFirstName())
                        .lastName(author.getLastName())
                        .build()));
    }

    @Override
    public AuthorDto updateAuthor(Long authorId, AuthorDto newData) {
        Author author = authorsRepository.findById(authorId).orElseThrow(AuthorNotFoundException::new);

        author.setFirstName(newData.getFirstName());
        author.setLastName(newData.getLastName());

        return from(authorsRepository.save(author));
    }
}
