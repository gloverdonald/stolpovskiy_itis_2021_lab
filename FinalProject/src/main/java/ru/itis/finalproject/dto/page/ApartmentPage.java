package ru.itis.finalproject.dto.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.finalproject.dto.response.ApartmentResponse;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApartmentPage {
    private List<ApartmentResponse> apartments;
    private Integer totalPages;
}
