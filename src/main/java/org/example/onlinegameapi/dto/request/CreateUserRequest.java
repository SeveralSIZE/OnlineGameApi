package org.example.onlinegameapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(min = 8)
    private String password;

    @NotNull
    private Role role;
}
