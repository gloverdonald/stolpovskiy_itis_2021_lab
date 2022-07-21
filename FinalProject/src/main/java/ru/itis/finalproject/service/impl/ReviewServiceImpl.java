package ru.itis.finalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.finalproject.dto.request.ReviewRequest;
import ru.itis.finalproject.dto.response.ReviewResponse;
import ru.itis.finalproject.exceptions.ApartmentNotFoundException;
import ru.itis.finalproject.exceptions.ReviewNotFoundException;
import ru.itis.finalproject.exceptions.UserNotFoundException;
import ru.itis.finalproject.mapper.ReviewMapper;
import ru.itis.finalproject.models.ApartmentEntity;
import ru.itis.finalproject.models.ReviewEntity;
import ru.itis.finalproject.models.UserEntity;
import ru.itis.finalproject.repository.ApartmentRepository;
import ru.itis.finalproject.repository.ReviewRepository;
import ru.itis.finalproject.repository.UserRepository;
import ru.itis.finalproject.service.ReviewService;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final UserRepository userRepository;
    private final ApartmentRepository apartmentRepository;

    @Override
    public Long save(ReviewRequest reviewRequest) {
        UserEntity userEntity = userRepository.findById(reviewRequest.getAuthorId())
                .orElseThrow(UserNotFoundException::new);
        ApartmentEntity apartmentEntity = apartmentRepository.findById(reviewRequest.getApartmentId())
                .orElseThrow(ApartmentNotFoundException::new);
        ReviewEntity reviewEntity = reviewMapper.toReview(reviewRequest);
        reviewEntity.setAuthor(userEntity);
        reviewEntity.setApartment(apartmentEntity);
        return reviewRepository.save(reviewEntity).getId();
    }

    @Override
    public ReviewResponse get(Long id) {
        return reviewMapper.toResponse(reviewRepository.findById(id).orElseThrow(ReviewNotFoundException::new));
    }
}
