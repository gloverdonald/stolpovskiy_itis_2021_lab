package ru.itis.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.blog.models.Post;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRequest {
    private String title;
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
