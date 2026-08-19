package com.byfood.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record OrderRequest(
        @NotBlank(message = "customerName is required")
        @Size(max = 120)
        String customerName,

        @NotBlank(message = "customerPhone is required")
        @Size(max = 30)
        String customerPhone,

        @NotBlank(message = "customerAddress is required")
        @Size(max = 255)
        String customerAddress,

        @NotEmpty(message = "items must not be empty")
        List<@Valid OrderItemRequest> items
) {
}