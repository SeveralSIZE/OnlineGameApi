package org.example.onlinegameapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.onlinegameapi.enums.Rarity;

@Data
@NoArgsConstructor
public class CreateCardRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private Rarity rarity;

    @NotBlank
    private String iconUrl;
}
