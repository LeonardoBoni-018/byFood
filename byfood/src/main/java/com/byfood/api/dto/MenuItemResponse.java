package com.byfood.api.dto;

import java.math.BigDecimal;

public record MenuItemResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        String category,
        String imageUrl,
        boolean available
) {
}