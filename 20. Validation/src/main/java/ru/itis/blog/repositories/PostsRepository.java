package ru.itis.blog.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.blog.models.Author;
import ru.itis.blog.models.Post;

public interface PostsRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByAuthorAndState(Author author, Post.State state, Pageable pageable);
}
