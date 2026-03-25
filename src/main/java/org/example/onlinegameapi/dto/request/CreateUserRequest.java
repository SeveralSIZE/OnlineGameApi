package org.example.onlinegameapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.onlinegameapi.enums.Role;


@Data
@NoArgsConstructor
public class CreateUserRequest {
    @NotBlank
    private String userName;

    @NotBlank
    private String email;

    @NotBlank
    @
    private String password;

    @NotNull
    private Role role;
}
