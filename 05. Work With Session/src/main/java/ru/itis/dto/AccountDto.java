package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.itis.models.Account;

@Data
@AllArgsConstructor
@Builder
public class AccountDto {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;

    private String password;

    public static AccountDto from(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .email(account.getEmail())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .build();
    }
}
