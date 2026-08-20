package com.byfood.api.dto;

import com.byfood.api.model.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record OrderStatusUpdateRequest(
        @NotNull(message = "status é obrigatório")
        OrderStatus status
) {
}