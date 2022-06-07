package ru.itis.finalproject.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.finalproject.dto.enums.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity extends AbstractEntity {
    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    private String email;

    @NotBlank
    @Column(name = "hash_password")
    private String hashPassword;

    @Builder.Default
    private Boolean verified = false;

    private Role role;
}
