package ru.itis.finalproject.service;

import ru.itis.finalproject.dto.request.ReviewRequest;
import ru.itis.finalproject.dto.response.ReviewResponse;

public interface ReviewService {

    Long save(ReviewRequest reviewRequest);

    ReviewResponse get(Long id);
}
