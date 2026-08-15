package com.byfood.api.mapper;

import com.byfood.api.dto.RestaurantResponse;
import com.byfood.api.model.Restaurant;

public final class RestaurantMapper {
    private RestaurantMapper(){}

    public static RestaurantResponse toResponse(Restaurant restaurant) {
        return new RestaurantResponse(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getDescription(),
                restaurant.getAddress(),
                restaurant.getPhone(),
                restaurant.getWhatsappNumber(),
                restaurant.getOpeningHours()
        );
    }
}
