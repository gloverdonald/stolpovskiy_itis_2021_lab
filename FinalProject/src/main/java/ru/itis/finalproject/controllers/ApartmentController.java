package ru.itis.finalproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.finalproject.dto.ApartmentSearchDto;
import ru.itis.finalproject.dto.page.ApartmentPage;
import ru.itis.finalproject.dto.request.ApartmentRequest;
import ru.itis.finalproject.dto.request.ApartmentWithAddressRequest;
import ru.itis.finalproject.dto.response.ApartmentResponse;
import ru.itis.finalproject.service.ApartmentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apartment")
public class ApartmentController {
    private final ApartmentService apartmentService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    private ApartmentResponse create(@Valid @RequestBody ApartmentRequest apartment) {
        return apartmentService.save(apartment);
    }

    @PostMapping("/add-with-address")
    @ResponseStatus(HttpStatus.CREATED)
    private ApartmentResponse createWithAddress(@Valid @RequestBody ApartmentWithAddressRequest apartmentWithAddressRequest) {
        return apartmentService.saveWithAddress(apartmentWithAddressRequest);
    }

    @GetMapping("/{id}")
    private ApartmentResponse get(@PathVariable Long id) {
        return apartmentService.get(id);
    }

    @GetMapping("/availability")
    private Boolean check(@RequestBody ApartmentSearchDto searchDto) {
        return apartmentService.isAvailable(searchDto);
    }

    @GetMapping("/all")
    private List<ApartmentResponse> getAll() {
        return apartmentService.getAll();
    }

    @GetMapping("/all/{city}")
    @ResponseStatus(HttpStatus.OK)
    private ApartmentPage getAllByCity(@PathVariable("city") String city,
                                       @RequestParam(value = "page", defaultValue = "0") int page,
                                       @RequestParam(value = "sort-param", defaultValue = "price") String param) {
        return apartmentService.getApartmentByCity(page, param, city);
    }
}
