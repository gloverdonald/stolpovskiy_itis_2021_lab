package ru.itis.finalproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.finalproject.dto.request.ReviewRequest;
import ru.itis.finalproject.dto.response.ReviewResponse;
import ru.itis.finalproject.service.ReviewService;

@RequiredArgsConstructor
@RequestMapping("/review")
@RestController
public class ReviewController {
    public final ReviewService reviewService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody ReviewRequest reviewRequest) {
        return reviewService.save(reviewRequest);
    }

    @GetMapping("/{id}")
    public ReviewResponse get(@PathVariable Long id) {
        return reviewService.get(id);
    }
}