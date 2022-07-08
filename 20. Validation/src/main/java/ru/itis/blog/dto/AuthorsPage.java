package ru.itis.blog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Страница со списком авторов и общее количество таких страниц")
public class AuthorsPage {

    private List<AuthorDto> authors;

    @Schema(description = "Количество доступных страниц")
    private Integer totalPages;
}
