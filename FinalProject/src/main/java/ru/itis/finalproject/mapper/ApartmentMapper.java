package ru.itis.finalproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import ru.itis.finalproject.dto.request.ApartmentRequest;
import ru.itis.finalproject.dto.request.ApartmentWithAddressRequest;
import ru.itis.finalproject.dto.response.ApartmentResponse;
import ru.itis.finalproject.models.ApartmentEntity;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {AddressMapper.class, UserMapper.class})
public interface ApartmentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    ApartmentEntity toApartment(ApartmentRequest apartmentRequest);

    ApartmentResponse toResponse(ApartmentEntity apartmentEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    ApartmentEntity toApartment(ApartmentWithAddressRequest apartmentWithAddressRequest);

    List<ApartmentResponse> toResponses(List<ApartmentEntity> apartmentWithAddressRequests);
}