package ru.itis.finalproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.finalproject.dto.request.AddressRequest;
import ru.itis.finalproject.dto.request.ApartmentWithAddressRequest;
import ru.itis.finalproject.dto.response.AddressResponse;
import ru.itis.finalproject.models.AddressEntity;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    AddressEntity toAddress(AddressRequest addressRequest);

    AddressResponse toResponse(AddressEntity addressEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    AddressEntity toAddress(ApartmentWithAddressRequest apartmentWithAddressRequest);
}
