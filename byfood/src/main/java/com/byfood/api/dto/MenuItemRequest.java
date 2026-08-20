package com.byfood.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record MenuItemRequest(
        @NotBlank(message = "nome é obrigatório")
        @Size(max = 150)
        String name,

        @Size(max = 500)
        String description,

        @NotNull(message = "preço é obrigatório")
        @DecimalMin(value = "0.01", message = "preço deve ser maior que zero")
        BigDecimal price,

        @NotBlank(message = "categoria é obrigatória")
        @Size(max = 80)
        String category,

        @Size(max = 500)
        String imageUrl,

        boolean available
) {
}