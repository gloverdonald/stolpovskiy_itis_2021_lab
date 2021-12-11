package ru.itis.education.models;

import lombok.*;

import javax.persistence.Table;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
}
