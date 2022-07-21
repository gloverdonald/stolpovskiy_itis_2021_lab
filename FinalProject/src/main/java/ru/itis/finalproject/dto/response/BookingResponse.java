package ru.itis.finalproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponse {
    private Long id;

    private Date dateStart;

    private Date dateEnd;

    private ApartmentResponse apartmentResponse;

    private UserResponse customerResponse;
}
