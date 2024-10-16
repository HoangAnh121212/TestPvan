package com.example.testlifetext.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {
    @NotNull(message = "MUST_REQUIRED")
    private String username;
    @NotNull(message = "MUST_REQUIRED")
    private String password;
}
