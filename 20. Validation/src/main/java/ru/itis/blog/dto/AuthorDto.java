package ru.itis.blog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.blog.models.Author;
import ru.itis.blog.validation.annotations.NotSameNames;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NotSameNames(names = {"firstName", "lastName"}, message = "{names} are same")
@Schema(description = "Автор")
public class AuthorDto {

    private Long id;

    @NotBlank(message = "Имя не может быть пустым")
    @Size(min = 2, max = 10, message = "минимальный размер {min}, максимальный размер {max}")
    @Schema(description = "Имя")
    private String firstName;

    @NotBlank(message = "Фамилия не может быть пустой")
    @Size(min = 2, max = 10, message = "минимальный размер {min}, максимальный размер {max}")
    @Schema(description = "Фамилия")
    private String lastName;

    public static AuthorDto from(Author author) {
        return AuthorDto.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .build();
    }

    public static List<AuthorDto> from(List<Author> authors) {
        return authors.stream().map(AuthorDto::from).collect(Collectors.toList());
    }
}
