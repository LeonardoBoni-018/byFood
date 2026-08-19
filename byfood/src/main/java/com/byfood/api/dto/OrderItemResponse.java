package com.byfood.api.dto;

import java.math.BigDecimal;

public record OrderItemResponse(
        String itemName,
        BigDecimal unitPrice,
        Integer quantity
) {
}