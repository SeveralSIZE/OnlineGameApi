package org.example.onlinegameapi.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.example.onlinegameapi.enums.Rarity;

import java.util.UUID;

@Builder
@Getter
public class CardResponse {
    private UUID id;
    private String name;
    private String description;
    private Rarity rarity;
    private String iconUrl;
}
