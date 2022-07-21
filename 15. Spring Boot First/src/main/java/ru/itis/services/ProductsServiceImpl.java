package ru.itis.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.ProductDto;
import ru.itis.model.Product;
import ru.itis.repositories.ProductsRepository;

import java.util.List;

import static ru.itis.dto.ProductDto.from;

@RequiredArgsConstructor
@Service
public class ProductsServiceImpl implements ProductService {
    private final ProductsRepository productsRepository;

    @Override
    public List<ProductDto> getAll() {
        return from(productsRepository.findAll());
    }

    @Override
    public ProductDto add(ProductDto productDto) {
        Product product = Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .weight(productDto.getWeight())
                .build();
        return from(productsRepository.save(product));
    }

    @Override
    public void delete(Long id) {
        productsRepository.deleteById(id);
    }
}
