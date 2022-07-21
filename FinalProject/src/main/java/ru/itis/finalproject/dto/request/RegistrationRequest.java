package ru.itis.finalproject.dto.request;

import lombok.*;
import ru.itis.finalproject.dto.enums.Role;
import ru.itis.finalproject.validation.custom.CustomPassword;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    @NotBlank(message = "Имя не может быть пустым")
    @Size(min = 2, max = 20, message = "минимальный размер {min}, максимальный размер {max}")
    private String firstName;

    @NotBlank(message = "Фамилия не может быть пустой")
    @Size(min = 2, max = 20, message = "минимальный размер {min}, максимальный размер {max}")
    private String lastName;

    private String email;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 6, max = 100, message = "минимальный размер {min}, максимальный размер {max}")
    @CustomPassword
    private String password;

    private Role role;
}
