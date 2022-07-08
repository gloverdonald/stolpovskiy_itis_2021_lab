package ru.itis.blog.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Получение страницы авторов с пагинацией")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с авторами",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AuthorsPage.class)
                            )
                    }
            )
    })
    @GetMapping
    public ResponseEntity<AuthorsPage> getAuthors(
            @Parameter(description = "Номер страницы") @RequestParam(value = "page", defaultValue = "0") int page) {
        return ResponseEntity.ok(authorsService.getAuthors(page));
    }


    @Operation(summary = "Добавление автора")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aвтор",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AuthorDto.class)
                            )
                    }
            )
    })
    @PostMapping
    public ResponseEntity<AuthorDto> addAuthor(@RequestBody AuthorDto author) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authorsService.addAuthor(author));
    }


    @Operation(summary = "Обновление автора")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aвтор",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AuthorDto.class)
                            )
                    }
            )
    })
    @PutMapping("/{author-id}")
    public ResponseEntity<AuthorDto> updateAuthor(
            @Parameter(description = "Id автора") @PathVariable("author-id") Long authorId,
            @RequestBody AuthorDto newData) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(authorsService.updateAuthor(authorId, newData));
    }


    @Operation(summary = "Получение постов автора")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с постами",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AuthorPostsPage.class)
                            )
                    }
            )
    })
    @GetMapping("/{author-id}/posts")
    public ResponseEntity<AuthorPostsPage> getAuthorPosts(
            @Parameter(description = "Id автора") @PathVariable("author-id") Long authorId,
            @Parameter(description = "Номер страницы") @RequestParam(value = "page", defaultValue = "0") int page,
            @Parameter(description = "Значение для сортировки") @RequestParam(value = "sort-param", defaultValue = "id") String sortParam) {
        return ResponseEntity.ok(postService.getAuthorPosts(authorId, page, sortParam));
    }


    @Operation(summary = "Добавление поста автору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Новый пост",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PostResponse.class)
                            )
                    }
            )
    })
    @PostMapping("/{author-id}/posts")
    public ResponseEntity<PostResponse> addAuthorPost(
            @Parameter(description = "Id автора") @PathVariable("author-id") Long authorId,
            @RequestBody PostRequest postDto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postService.addAuthorPost(authorId, postDto));
    }


    @Operation(summary = "Удаление поста")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Удалённый пост",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PostResponse.class)
                            )
                    }
            )
    })
    @DeleteMapping("/posts/{post-id}")
    public ResponseEntity<PostResponse> addAuthorPost(
            @Parameter(description = "Id автора") @PathVariable("post-id") Long postId
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postService.deletePost(postId));
    }


    @Operation(summary = "Обновление поста")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Обновленный пост",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PostResponse.class)
                            )
                    }
            )
    })
    @PutMapping("/posts/{post-id}")
    public ResponseEntity<PostResponse> updateAuthorPost(
            @Parameter(description = "Id поста") @PathVariable("post-id") Long postId,
            @RequestBody PostRequest updatedPost
    ) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(postService.updatePost(postId, updatedPost));
    }

    @Operation(summary = "Добавление поста в favorites")
    @PostMapping("/{author-id}/favorites/{post-id}")
    public ResponseEntity<?> addPostToFavorites(
            @Parameter(description = "Id автора") @PathVariable("author-id") Long authorId,
            @Parameter(description = "Id поста") @PathVariable("post-id") Long postId) {
        authorsService.addPostToFavorites(authorId, postId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @Operation(summary = "Удаление поста из favorites")
    @DeleteMapping("/{author-id}/favorites/{post-id}")
    public ResponseEntity<?> deletePostToFavorites(
            @Parameter(description = "Id автора") @PathVariable("author-id") Long authorId,
            @Parameter(description = "Id поста") @PathVariable("post-id") Long postId) {
        authorsService.deletePostFromFavorites(authorId, postId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
