package com.byfood.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record MenuItemRequest(
        @NotBlank(message = "name is required")
        @Size(max = 150)
        String name,

        @Size(max = 500)
        String description,

        @NotNull(message = "price is required")
        @DecimalMin(value = "0.01", message = "price must be greater than zero")
        BigDecimal price,

        @NotBlank(message = "category is required")
        @Size(max = 80)
        String category,

        @Size(max = 500)
        String imageUrl,

        boolean available
) {
}