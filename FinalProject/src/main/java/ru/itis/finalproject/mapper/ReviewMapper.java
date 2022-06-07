package ru.itis.finalproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.finalproject.dto.request.ReviewRequest;
import ru.itis.finalproject.dto.response.ReviewResponse;
import ru.itis.finalproject.models.ReviewEntity;

@Mapper(componentModel = "spring", uses = {UserMapper.class, ApartmentMapper.class})
public interface ReviewMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    ReviewEntity toReview(ReviewRequest request);

    ReviewResponse toResponse(ReviewEntity reviewEntity);
}
