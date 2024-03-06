package ru.preachooda.bokunohero.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AuthResponse {
    private String accessToken;
    private Long userId;
    private Long entityId;
}
