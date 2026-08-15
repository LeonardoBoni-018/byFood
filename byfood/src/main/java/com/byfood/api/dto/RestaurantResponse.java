package com.byfood.api.dto;

public record RestaurantResponse(
        Long id,
        String name,
        String description,
        String address,
        String phone,
        String whatsappNumber,
        String openingHours
) {
}