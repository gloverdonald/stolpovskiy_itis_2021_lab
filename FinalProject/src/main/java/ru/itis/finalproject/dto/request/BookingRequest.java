package ru.itis.finalproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingRequest {
    private Date dateStart;

    private Date dateEnd;

    private Long apartmentId;

    private Long customerId;
}
