package ru.itis.blog.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.itis.blog.dto.AuthorPostsPage;
import ru.itis.blog.dto.PostRequest;
import ru.itis.blog.dto.PostResponse;
import ru.itis.blog.exceptions.AuthorNotExistsException;
import ru.itis.blog.exceptions.AuthorNotFoundException;
import ru.itis.blog.exceptions.PostNotFoundException;
import ru.itis.blog.models.Author;
import ru.itis.blog.models.Post;
import ru.itis.blog.repositories.AuthorsRepository;
import ru.itis.blog.repositories.PostsRepository;
import ru.itis.blog.services.PostService;

import java.time.LocalDateTime;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostsRepository postsRepository;
    private final AuthorsRepository authorsRepository;

    @Value("${blog.default-page-size}")
    private int defaultPageSize;

    private final Post.State published = Post.State.PUBLISHED;

    @Override
    public AuthorPostsPage getAuthorPosts(Long authorId, int page, String sortParam) {
        Author author = authorsRepository.findById(authorId)
                .orElseThrow((Supplier<RuntimeException>) () -> new AuthorNotExistsException(authorId));

        PageRequest pageRequest = PageRequest.of(page, defaultPageSize, Sort.by(sortParam));
        Page<Post> postPage = postsRepository.findAllByAuthorAndState(author, published, pageRequest);

        return AuthorPostsPage.builder()
                .posts(PostResponse.from(postPage.getContent()))
                .totalPages(postPage.getTotalPages())
                .build();
    }

    @Override
    public PostResponse addAuthorPost(Long authorId, PostRequest postDto) {
        Author author = authorsRepository.findById(authorId)
                .orElseThrow((Supplier<RuntimeException>) () -> new AuthorNotExistsException(authorId));

        Post post = Post.builder()
                .title(postDto.getTitle())
                .text(postDto.getText())
                .createdAt(LocalDateTime.now())
                .state(Post.State.PUBLISHED)
                .author(author)
                .build();
        return PostResponse.from(postsRepository.save(post));
    }

    @Override
    public PostResponse updatePost(Long postId, PostRequest newData) {
        Post post = postsRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        post.setTitle(newData.getTitle());
        post.setText(newData.getText());

        return PostResponse.from(postsRepository.save(post));
    }

    @Override
    public PostResponse deletePost(Long id) {
        Post post = postsRepository.findById(id).orElseThrow(PostNotFoundException::new);
        post.setState(Post.State.DELETED);
        return PostResponse.from(postsRepository.save(post));
    }
}
