package ru.itis.blog.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.blog.dto.*;
import ru.itis.blog.services.AuthorsService;
import ru.itis.blog.services.PostService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authors")
public class AuthorsController {

    private final AuthorsService authorsService;
    private final PostService postService;

    @GetMapping
    public ResponseEntity<AuthorsPage> getAuthors(@RequestParam(value = "page", defaultValue = "0") int page) {
        return ResponseEntity.ok(authorsService.getAuthors(page));
    }

    @PostMapping
    public ResponseEntity<AuthorDto> addAuthor(@RequestBody AuthorDto author) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authorsService.addAuthor(author));
    }

    @PutMapping("/{author-id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable("author-id") Long authorId,
                                                  @RequestBody AuthorDto newData) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(authorsService.updateAuthor(authorId, newData));
    }

    @GetMapping("/{author-id}/posts")
    public ResponseEntity<AuthorPostsPage> getAuthorPosts(@PathVariable("author-id") Long authorId,
                                                          @RequestParam(value = "page", defaultValue = "0") int page,
                                                          @RequestParam(value = "sort-param", defaultValue = "id") String sortParam) {
        return ResponseEntity.ok(postService.getAuthorPosts(authorId, page, sortParam));
    }

    @PostMapping("/{author-id}/posts")
    public ResponseEntity<PostResponse> addAuthorPost(@PathVariable("author-id") Long authorId,
                                                      @RequestBody PostRequest postDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postService.addAuthorPost(authorId, postDto));
    }

    @DeleteMapping("/posts/{post-id}")
    public ResponseEntity<PostResponse> addAuthorPost(@PathVariable("post-id") Long postId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postService.deletePost(postId));
    }

    @PutMapping("/posts/{post-id}")
    public ResponseEntity<PostResponse> updateAuthorPost(@PathVariable("post-id") Long postId,
                                                         @RequestBody PostRequest updatedPost) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(postService.updatePost(postId, updatedPost));
    }
}
