package org.example.onlinegameapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateCaseRequest {
    @NotBlank
    private String name;

    @NotNull
    private Integer price;

    private Integer commonChance;
    private Integer uncommonChance;
    private Integer rareChance;
    private Integer epicChance;
    private Integer legendaryChance;
    private Integer mythicChance;
    private Integer eternalChance;
}
