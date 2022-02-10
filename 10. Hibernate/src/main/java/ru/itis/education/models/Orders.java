package ru.itis.education.models;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@EqualsAndHashCode
public class Orders {
    private Long id;

    private String title;

    private List<Product> products;

    private Customer customer;
}
