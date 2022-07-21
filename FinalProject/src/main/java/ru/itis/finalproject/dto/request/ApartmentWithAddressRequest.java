package ru.itis.finalproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApartmentWithAddressRequest {
    @NotNull(message = "Цена не может быть пустой")
    @Min(value = 1, message = "минимальная цена {value}")
    private Long price;

    private Long ownerId;

    private String country;

    private String city;

    private String street;

    private String houseNumber;
}
