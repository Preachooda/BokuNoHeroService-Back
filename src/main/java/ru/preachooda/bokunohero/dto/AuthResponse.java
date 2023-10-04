package ru.preachooda.bokunohero.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class AuthResponse {
    private String authenticationToken;
    private String username;
    private String fullName;
    private List<String> roles;
}
