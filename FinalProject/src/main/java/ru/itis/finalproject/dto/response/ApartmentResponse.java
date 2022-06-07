package ru.itis.finalproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.finalproject.models.ApartmentEntity;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApartmentResponse {
    private Long id;

    private Long price;

    private AddressResponse address;

    private UserResponse owner;
}
