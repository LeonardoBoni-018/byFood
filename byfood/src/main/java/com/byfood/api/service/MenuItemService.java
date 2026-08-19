package com.byfood.api.service;

import com.byfood.api.dto.MenuItemResponse;
import com.byfood.api.mapper.MenuItemMapper;
import com.byfood.api.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository repository){
        this.menuItemRepository = repository;
    }

    public List<MenuItemResponse> getAvailableMenu(){
        return menuItemRepository.findByAvailableTrueOrderByCategoryAscNameAsc().stream().map(MenuItemMapper::toResponse)
                .toList();
    }
}
