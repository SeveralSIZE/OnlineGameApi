package org.example.onlinegameapi.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.example.onlinegameapi.enums.Role;

import java.time.OffsetDateTime;

@Builder
@Getter
public class UserResponse {
    private String userName;
    private OffsetDateTime creationDate;
    private Role role;
    private int coins;
}
