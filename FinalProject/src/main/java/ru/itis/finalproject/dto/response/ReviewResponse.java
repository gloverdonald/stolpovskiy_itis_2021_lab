package ru.itis.finalproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewResponse {
    private Long id;

    private Integer rating;

    private String message;

    private UserResponse author;

    private ApartmentResponse apartment;
}
