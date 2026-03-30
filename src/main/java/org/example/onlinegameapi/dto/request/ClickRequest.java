package org.example.onlinegameapi.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClickRequest {
    @Size(min = 1, max = 100)
    private int clicks;
}
