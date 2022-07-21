package ru.itis.finalproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review")
public class ReviewEntity extends AbstractEntity {
    @Column(nullable = false)
    private Integer rating;

    private String message;

    @OneToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;

    @OneToOne
    @JoinColumn(name = "apartment_id")
    private ApartmentEntity apartment;
}
