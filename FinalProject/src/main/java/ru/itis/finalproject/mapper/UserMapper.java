package ru.itis.finalproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.finalproject.dto.request.RegistrationRequest;
import ru.itis.finalproject.dto.response.UserResponse;
import ru.itis.finalproject.models.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toResponse(UserEntity userEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "hashPassword", source = "password")
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    UserEntity toUser(RegistrationRequest registrationRequest);
}
