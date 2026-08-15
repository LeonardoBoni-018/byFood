package com.byfood.api.mapper;

import com.byfood.api.dto.MenuItemRequest;
import com.byfood.api.dto.MenuItemResponse;
import com.byfood.api.model.MenuItem;

public final class MenuItemMapper {

    private MenuItemMapper() {
    }

    public static MenuItem toEntity(MenuItemRequest request) {
        return MenuItem.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .category(request.category())
                .imageUrl(request.imageUrl())
                .available(request.available())
                .build();
    }

    public static MenuItemResponse toResponse(MenuItem menuItem) {
        return new MenuItemResponse(
                menuItem.getId(),
                menuItem.getName(),
                menuItem.getDescription(),
                menuItem.getPrice(),
                menuItem.getCategory(),
                menuItem.getImageUrl(),
                menuItem.isAvailable()
        );
    }
}