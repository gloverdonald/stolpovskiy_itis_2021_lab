package ru.itis.blog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.blog.models.Post;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Отправляемый пост")
public class PostRequest {

    @Size(min = 5, max = 20, message = "минимальный размер {min}, максимальный размер {max}")
    @NotBlank(message = "Заголовок не может быть пустым")
    private String title;

    @Size(max = 1000, message = "максимальный размер {max}")
    @NotBlank(message = "Текст не может быть пустым")
    private String text;

    public static PostRequest from(Post post) {
        return PostRequest.builder()
                .text(post.getText())
                .title(post.getTitle())
                .build();
    }

    public static List<PostRequest> from(List<Post> posts) {
        return posts.stream().map(PostRequest::from).collect(Collectors.toList());
    }
}
