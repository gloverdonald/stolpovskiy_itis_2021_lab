package ru.itis.blog.services;

import ru.itis.blog.dto.AuthorPostsPage;
import ru.itis.blog.dto.PostRequest;
import ru.itis.blog.dto.PostResponse;

public interface PostService {
    AuthorPostsPage getAuthorPosts(Long authorId, int page, String sortParam);

    PostResponse addAuthorPost(Long authorId, PostRequest postDto);

    PostResponse updatePost(Long postId, PostRequest newData);

    PostResponse deletePost(Long postId);
}
