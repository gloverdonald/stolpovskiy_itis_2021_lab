package ru.itis.blog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.blog.models.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Пост")
public class PostResponse {
    private Long id;
    private String title;
    private String text;
    private LocalDateTime createdAt;
    private Post.State state;

    public static PostResponse from(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .text(post.getText())
                .title(post.getTitle())
                .createdAt(post.getCreatedAt())
                .state(post.getState())
                .build();
    }

    public static List<PostResponse> from(List<Post> posts) {
        return posts.stream().map(PostResponse::from).collect(Collectors.toList());
    }
}
