package org.example.onlinegameapi.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TokensResponse {
    private String accessToken;
    private String refreshToken;
}
