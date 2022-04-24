package ru.itis.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.blog.models.Author;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Author.Role role;

    public static AuthorRequest from(Author author) {
        return AuthorRequest.builder()
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .email(author.getEmail())
                .password(author.getPassword())
                .role(author.getRole())
                .build();
    }

    public static List<AuthorRequest> from(List<Author> authors) {
        return authors.stream().map(AuthorRequest::from).collect(Collectors.toList());
    }
}

