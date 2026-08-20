package com.byfood.api.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "usuário é obrigatório")
        String username,

        @NotBlank(message = "senha é obrigatória")
        String password
) {
}