package ru.itis.blog.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.itis.blog.dto.AuthorRequest;
import ru.itis.blog.services.AuthorAuthService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authors/sign-up")
public class AuthorSignUpController {

    private final AuthorAuthService authorAuthService;

    @PostMapping
    public ResponseEntity<Long> createAuthor(@RequestBody AuthorRequest author) {
        return ResponseEntity.ok(authorAuthService.createAuthor(author));
    }
}
