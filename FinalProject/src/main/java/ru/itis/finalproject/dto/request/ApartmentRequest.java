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
public class ApartmentRequest {
    @NotNull(message = "Цена не может быть пустой")
    @Min(value = 1, message = "минимальная цена {value}")
    private Long price;

    private Long addressId;

    private Long ownerId;
}
