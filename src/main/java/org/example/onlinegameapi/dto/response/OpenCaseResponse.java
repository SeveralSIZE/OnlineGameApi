package org.example.onlinegameapi.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OpenCaseResponse {
    private CardResponse card;
    private int remainingCoins;
}
