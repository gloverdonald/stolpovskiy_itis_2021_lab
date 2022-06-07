package ru.itis.finalproject.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.finalproject.dto.request.AddressRequest;
import ru.itis.finalproject.dto.response.AddressResponse;
import ru.itis.finalproject.service.AddressService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "authorization")
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    @Operation(summary = "Добавление адреса")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aдрес",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AddressRequest.class)
                            )
                    }
            )
    })
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    private AddressResponse create(@RequestBody AddressRequest addressRequest) {
        return addressService.save(addressRequest);
    }


    @Operation(summary = "Получение адреса")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Адрес", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AddressResponse.class)
                    )
            }
            )
    })
    @GetMapping("/{id}")
    private AddressResponse get(@Parameter(description = "Id адреса") @PathVariable Long id) {
        return addressService.get(id);
    }

    @Operation(summary = "Получение всех адресов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Адрес", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AddressResponse.class)))
            }
            )
    })
    @GetMapping("/all")
    private List<AddressResponse> getAll() {
        return addressService.getAll();
    }
}
