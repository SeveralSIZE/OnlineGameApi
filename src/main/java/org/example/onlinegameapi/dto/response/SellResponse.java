package org.example.onlinegameapi.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SellResponse {
    private Integer coins;
    private Integer sellPrice;
}
