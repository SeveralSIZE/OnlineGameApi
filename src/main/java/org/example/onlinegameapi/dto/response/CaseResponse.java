package org.example.onlinegameapi.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class CaseResponse {
    private UUID id;
    private String name;
    private Integer price;
}
