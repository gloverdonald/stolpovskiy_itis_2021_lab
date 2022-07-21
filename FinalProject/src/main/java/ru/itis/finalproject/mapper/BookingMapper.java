package ru.itis.finalproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.finalproject.dto.request.BookingRequest;
import ru.itis.finalproject.dto.response.BookingResponse;
import ru.itis.finalproject.models.BookingEntity;

@Mapper(componentModel = "spring", uses = {ApartmentMapper.class, UserMapper.class})
public interface BookingMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    BookingEntity toBooking(BookingResponse bookingResponse);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    BookingEntity toBooking(BookingRequest bookingRequest);

    BookingResponse toResponse(BookingEntity bookingEntity);
}
