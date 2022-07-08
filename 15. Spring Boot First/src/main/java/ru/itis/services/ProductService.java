package ru.itis.services;

import ru.itis.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();

    ProductDto add(ProductDto productDto);

    void delete(Long id);
}
