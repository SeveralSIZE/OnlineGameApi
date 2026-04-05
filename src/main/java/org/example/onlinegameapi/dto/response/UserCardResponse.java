package org.example.onlinegameapi.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class UserCardResponse {
    private UUID id;
    private CardResponse card;
    private Integer amount;
}
