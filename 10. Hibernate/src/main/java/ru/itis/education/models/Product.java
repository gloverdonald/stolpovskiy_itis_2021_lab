package ru.itis.education.models;

import lombok.*;

import javax.persistence.Table;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class Product {
    private Long id;

    private String title;

    private Long price;
}
